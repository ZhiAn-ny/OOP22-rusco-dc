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
import it.unibo.ruscodc.utils.exception.ChangeFloorException;
import it.unibo.ruscodc.utils.exception.ChangeRoomException;
import it.unibo.ruscodc.utils.exception.ModelException;
import it.unibo.ruscodc.utils.exception.Undo;
import it.unibo.ruscodc.view.FXMLMainView;
import it.unibo.ruscodc.view.GameView;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Class GameControllerImpl.
 * This class receives the input of the user through the view.
 * Send this to the Model for to execute a specific action based on input.
 * In the end upload the view with the changes made.
 */
public class GameControllerImpl implements GameObserverController {

    private Set<GameControl> DOUBLE_EX = Set.of(GameControl.CANCEL, GameControl.CONFIRM);

    private List<Actor> initiative = new ArrayList<>();
    private Optional<GameCommand> playerSituation = Optional.empty();
    private final GameView view;
    private final GameModel model;
    private boolean automaticSave = false;

    private boolean isPrintingInv = false;

    /**
     * Create the controller of the game.
     * @param args
     */
    public GameControllerImpl(final String... args) {
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

    /**
     *
     */
    @Override
    public void start(final String[] args) {
        this.view.startView(args);
        initNewTurn();
        while (!this.view.isReady()) {
            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        view.closeInventory();
        view.resetView(entityToUpload(), model.getCurrentRoom().getSize());
        updateRuscoInfo();
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
        automaticSave = !automaticSave;
    }

    /**
     * Compute the entity to draw when a room change.
     * @return the entity to draw
     */
    private List<Entity> entityToUpload() {
        List<Entity> tmp = new ArrayList<>(model.getCurrentRoom().getTilesAsEntity());
        tmp.addAll(model.getCurrentRoom().getObjectsInRoom());
        tmp.addAll(initiative);
        return tmp;
    }

    private void updateRuscoInfo() {
        this.view.uploadPortrait(this.model.getRuscoInfo());
    }

    private void changeFloor() {
        model.changeFloor();
        initiative.clear();
        initNewTurn();
        if (automaticSave) {
            save();
        }
        view.resetView(entityToUpload(), model.getCurrentRoom().getSize());
    }

    private void changeRoom(final ChangeRoomException r) {
        model.changeRoom(r.getDoorPos());
        initiative.clear();
        initNewTurn();
        view.resetView(entityToUpload(), model.getCurrentRoom().getSize());
    }

    private void flushView() {
        this.view.resetLevel(this.model.getCurrentRoom().getObjectsInRoom().stream().map(i -> (Entity) i).toList());
        this.view.resetLevel(this.model.getActorByInitative().stream()
                .filter(Actor::isAlive)
                .map(a -> (Entity) a)
                .toList());
    }

    private void printCommand() {
        Set<Entity> infos = playerSituation.get().getEntities();
        this.view.resetLevel(new ArrayList<>(infos));
        if (isPrintingInv) {
            this.view.printStats(this.initiative.get(0).toString());
        }
    }

    private boolean executeCommand(final GameCommand toExec) {
        final boolean ready = toExec.isReady();
        System.out.println(" ### " + ready);
        if (ready) {
            try {
                //Pair<Integer, Integer> oldActor = initiative.get(0).getPos();
                Optional<InfoPayload> tmp = toExec.execute();

                if (tmp.isPresent()) {
                    view.printInfo(tmp.get());
                    return ready;
                }

                initiative.remove(0);
                playerSituation = Optional.empty();
                if (!this.model.isGameOver()) {
                    flushView();
                }
                isPrintingInv = false;

            } catch (ChangeFloorException f) {
                changeFloor();
                playerSituation = Optional.empty();

            } catch (ChangeRoomException r) {
                changeRoom(r);
                playerSituation = Optional.empty();

            } catch (Undo u) {
                view.closeInventory();
                playerSituation = Optional.empty();
                flushView();

            } catch (ModelException e) {
                // TODO - gestire eccezioni model
            }
        }
        updateRuscoInfo();
        return ready;
    }

    /**
     * Compute the input of user and execute a specific action according to it.
     * @param input input of the user
     */
    @Override
    public void computeInput(final GameControl input) {
        updateRuscoInfo();
        if (model.isGameOver()) {
            view.printGameOver();
            return;
        }
        initNewTurn();
        if (initiative.get(0) instanceof Hero) {
            Hero tmpActor = (Hero) initiative.get(0);
            GameCommand tmpCommand;
            //Pair<Integer, Integer> cod = tmpActor.getPos();

            if (playerSituation.isPresent()) {
                tmpCommand = playerSituation.get();

                if (tmpCommand.modify(input)) {
                    printCommand();
                }
                executeCommand(tmpCommand);

            } else {
                Optional<GameCommand> result = tmpActor.act(input);
                if (result.isEmpty()) {
                    return;
                }
                tmpCommand = result.get();
                tmpCommand.setRoom(model.getCurrentRoom());

                if (input.equals(GameControl.INVENTORY)) {
                    isPrintingInv = true;
                    view.openInventory();
                }

                if (tmpCommand.isReady()) { 
                    executeCommand(tmpCommand);
                    if (isPrintingInv) {
                        playerSituation = Optional.of(tmpCommand);
                    }
                } else {
                    playerSituation = Optional.of(tmpCommand);
                    printCommand();
                }
            }
        }
        manageMonsterTurn();
    }

    /**
     *
     */
    @Override
    public void quit() {
        System.exit(0);
    }

    /**
     * If the initiative list is empty, this method will fill it.
     */
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
            if (tmpMonster.isAlive()) {
                executeCommand(tmpMonster.behave(model.getCurrentRoom(), this.getHeros()));
                System.out.println("A " + initiative.size());
                //initiative.remove(0);
                //System.out.println("B " + initiative.size());
                flushView();
                System.out.println("C " + initiative.size());
            } else {
                initiative.remove(0);
                this.model.getCurrentRoom().getMonsters().remove(tmpMonster);
            }
            initNewTurn();
        }
    }

}
