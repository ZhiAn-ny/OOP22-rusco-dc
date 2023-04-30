package it.unibo.ruscodc.controller;

import it.unibo.ruscodc.model.Entity;
import it.unibo.ruscodc.model.GameModel;
import it.unibo.ruscodc.model.GameModelImpl;
import it.unibo.ruscodc.model.actors.Actor;
import it.unibo.ruscodc.model.actors.Hero;
import it.unibo.ruscodc.model.actors.Monster;
import it.unibo.ruscodc.model.gamecommand.BuilderGameCommand;
import it.unibo.ruscodc.model.gamecommand.ComplexActionBuilder;
import it.unibo.ruscodc.model.gamecommand.GameCommand;
import it.unibo.ruscodc.model.gamecommand.HandlebleGameCommand;
import it.unibo.ruscodc.model.gamecommand.MoveBuilder;
import it.unibo.ruscodc.model.gamecommand.QuickActionBuilder;
import it.unibo.ruscodc.utils.GameControl;
import it.unibo.ruscodc.utils.exception.ModelException;
import it.unibo.ruscodc.view.GameView;
import it.unibo.ruscodc.view.ViewJFX;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Class GameControllerImpl.
 * This class receives the input of the user through the view.
 * Send this to the Model for to execute a specific action based on input.
 * In the end upload the view with the changes made.
 *
 */
public class GameControllerImpl implements GameObserverController {

    private List<Actor> initiative = new ArrayList<>();
    private Optional<HandlebleGameCommand> playerSituation = Optional.empty();
    private final GameView view;
    private final GameModel model;

    /**
     * class constructor.
     */
    public GameControllerImpl() {
        this.view = new ViewJFX();
        this.model = new GameModelImpl();

    }

    @Override
    public void save() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void changeAutomaticSave() {

    }

    private List<Entity> entityToUpload() {
        List<Entity> tmp = model.getCurrentRoom().getTilesAsEntity();
        tmp.add((Entity) initiative.get(0));
        return tmp;
    }

    private boolean executeCommand(GameCommand toExec) {
        final boolean ready = toExec.isReady();
        if (ready) {
            try {
                toExec.execute();
                playerSituation = Optional.empty();
                initiative.remove(0);
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
            HandlebleGameCommand tmpCommand;

            if (playerSituation.isPresent()) {
                
                tmpCommand = playerSituation.get();
                if (tmpCommand.modify(input)) {
                    if (!executeCommand(tmpCommand)) {
                        // TODO - aggiornare la view qui!
                    }
                }

            } else {

                BuilderGameCommand wrapper = tmpActor.act(input);
                //wrapper.setActor(tmpActor);
                wrapper.setRoom(model.getCurrentRoom());
                
                if (wrapper instanceof QuickActionBuilder) {
                    QuickActionBuilder quick = (QuickActionBuilder) wrapper;
                    executeCommand(quick);

                } else {
                    ComplexActionBuilder complex = (ComplexActionBuilder) wrapper;
                    playerSituation = Optional.of(complex.buildForPlayer());
                }
            }
        }
    }


    @Override
    public void quit() {

    }

    /**
     * Initializes view with the controller "to connect them".
     */
    @Override
    public void init() {
        this.view.init(this);
    }

    /**
     * Start the view of application and manges the turn of Actor.
     */
    @Override
    public void start() {
        this.view.startView();
        initNewTurn();
        view.setEntityToDraw(entityToUpload());
        manageMonsterTurn();
    }

    /**
     * todo
     */
    @Override
    public void launch() {
        manageMonsterTurn();
    }

    private void initNewTurn() {
        if (initiative.isEmpty()) {
            initiative.addAll(model.getActorByInitative());
        }

    }
    private void manageMonsterTurn() {
        Monster tmpMonster;
        initNewTurn();
        while (initiative.get(0) instanceof Monster) {
            tmpMonster = (Monster) initiative.get(0);
            executeCommand(tmpMonster.behave(model.getCurrentRoom()));
            // TODO - post implementazione mostro
            //initiative.remove(0);
            initNewTurn();
        }
    }

}
