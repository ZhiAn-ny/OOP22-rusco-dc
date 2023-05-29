package it.unibo.ruscodc.controller;

import it.unibo.ruscodc.model.Entity;
import it.unibo.ruscodc.model.GameModel;
import it.unibo.ruscodc.model.GameModelImpl;
import it.unibo.ruscodc.model.actors.Actor;
import it.unibo.ruscodc.model.actors.hero.Hero;
import it.unibo.ruscodc.model.actors.monster.Monster;
import it.unibo.ruscodc.model.gamecommand.GameCommand;
import it.unibo.ruscodc.model.outputinfo.InfoPayload;
import it.unibo.ruscodc.utils.GameControl;
import it.unibo.ruscodc.utils.Pair;
import it.unibo.ruscodc.utils.exception.ChangeFloorException;
import it.unibo.ruscodc.utils.exception.ChangeRoomException;
import it.unibo.ruscodc.utils.exception.ModelException;
import it.unibo.ruscodc.view.FXMLMainView;
import it.unibo.ruscodc.view.GameView;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Class GameControllerImpl.
 * This class receives the input of the user through the view.
 * Send this to the Model for to execute a specific action based on input.
 * In the end upload the view with the changes made.
 */
public class GameControllerImpl implements GameObserverController {

    private List<Actor> initiative = new ArrayList<>();
    private Optional<GameCommand> playerSituation = Optional.empty();
    private final GameView view;
    private final GameModel model;
    private boolean automaticSave = false;

    /**
     * Create the controller of the game
     */
    public GameControllerImpl(String... args) {
        this.view = new FXMLMainView();
        this.model = new GameModelImpl();
    }

    /**
     *
     */
    @Override
    public void init() {
        this.view.init(this);
    }

    @Override
    public void start() {

    }

    /**
     *
     */
    @Override
    public void start(String[] args) {
        this.view.startView(args);
        initNewTurn();
        while (!this.view.isReady()) {
            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        view.resetView(entityToUpload(), model.getCurrentRoom().getSize());
        manageMonsterTurn();
    }

    /**
     *
     */
    @Override
    public void save() {
    }

    /**
     *
     */
    @Override
    public void pause() {
    }

    /**
     *
     */
    @Override
    public void resume() {
    }

    /**
     *
     */
    @Override
    public void changeAutomaticSave() {
    }

    /**
     * Compute the entity to draw when a room change.
     */
    private List<Entity> entityToUpload() {
        List<Entity> tmp = new ArrayList<>(model.getCurrentRoom().getTilesAsEntity());
        tmp.addAll(model.getCurrentRoom().getObjectsInRoom());
        tmp.addAll(initiative);
        return tmp;
    }

    private void changeFloor() {
        model.changeFloor();
        initNewTurn();
        if (automaticSave){
            save();
        }
        view.resetView(entityToUpload(), model.getCurrentRoom().getSize());
    }

    private void changeRoom(final ChangeRoomException r) {
        model.changeRoom(r.getDoorPos());
        initNewTurn();
        view.resetView(entityToUpload(), model.getCurrentRoom().getSize());
    }

    private void passTurn() {
        this.view.resetLevel(this.model.getCurrentRoom().getObjectsInRoom().stream().map(i->(Entity)i).toList());
        this.view.resetLevel(this.model.getActorByInitative().stream()
                .filter(Actor::isAlive)
                .map(a->(Entity)a)
                .toList());
    }

    private boolean executeCommand(GameCommand toExec) {
        final boolean ready = toExec.isReady();
        if (ready) {
            try {
                Pair<Integer, Integer> oldActor = initiative.get(0).getPos();
                Optional<InfoPayload> tmp = toExec.execute();
                if (tmp.isPresent()) {
                    view.printInfo(tmp.get());
                    return ready;
                }
                playerSituation = Optional.empty();
                passTurn();
            } catch (ChangeFloorException f){
                changeFloor();
                playerSituation = Optional.empty();
            } catch (ChangeRoomException r) {
                changeRoom(r);
                playerSituation = Optional.empty();
            } catch (ModelException e) {
                // TODO - gestire eccezioni model
            }
        }
        return ready;
    }

    /**
     * Compute the input of user and execute a specific action according to it.
     * @param input input of the user
     */
    @Override
    public void computeInput(final GameControl input) {

        if (initiative.get(0) instanceof Hero) {
            Hero tmpActor = (Hero) initiative.get(0);
            GameCommand tmpCommand;
            Pair<Integer, Integer> cod = tmpActor.getPos();

            if (playerSituation.isPresent()) {
                tmpCommand = playerSituation.get();

                if (tmpCommand.modify(input)) {
                    if(!executeCommand(tmpCommand)) {
                        Iterator<Entity> itE = playerSituation.get().getEntities();
                        this.view.resetLevel(Stream.iterate(itE.next(), i->itE.hasNext(), i->itE.next()).toList());
                    }
                }

            } else {
                Optional<GameCommand> res = tmpActor.act(input);
                if (res.isEmpty()){
                    return;
                }
                tmpCommand = res.get();
                tmpCommand.setRoom(model.getCurrentRoom());

                if (tmpCommand.isReady()) {
                    executeCommand(tmpCommand);
                    //passTurn();
                } else {
                    playerSituation = Optional.of(tmpCommand);
                }
            }
            manageMonsterTurn();
        }
    }

    /**
     *
     */
    @Override
    public void quit() {
        System.exit(0);
    }



    private void initNewTurn() {
        if (initiative.isEmpty()) {
            initiative.addAll(model.getActorByInitative());
        }
    }

    private List<Actor> getHeros() {
        List<Actor> tmp = new ArrayList<>();
        for (Actor a : this.model.getActorByInitative()) {
            if (a instanceof Hero) {
                tmp.add(a);
            }
        }
        return tmp;
    }

    private void manageMonsterTurn() {
        Monster tmpMonster;
        initNewTurn();
        while (initiative.get(0) instanceof Monster) {
            tmpMonster = (Monster) initiative.get(0);
            executeCommand(tmpMonster.behave(model.getCurrentRoom(), getHeros()));
            initiative.remove(0);
            passTurn();
            initNewTurn();
        }
    }

}
