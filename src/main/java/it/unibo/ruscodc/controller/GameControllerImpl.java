package it.unibo.ruscodc.controller;

import it.unibo.ruscodc.model.Entity;
import it.unibo.ruscodc.model.GameModel;
import it.unibo.ruscodc.model.GameModelImpl;
import it.unibo.ruscodc.model.actors.Actor;
import it.unibo.ruscodc.model.actors.hero.Hero;
import it.unibo.ruscodc.model.actors.monster.Monster;
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
 */
public class GameControllerImpl implements GameObserverController {

    private List<Actor> initiative = new ArrayList<>();
    private Optional<HandlebleGameCommand> playerSituation = Optional.empty();
    private final GameView view;
    private final GameModel model;

    /**
     * Create the controller of the game
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

    private List<Entity> entityToUpload(){
        List<Entity> tmp = new ArrayList<>(this.model.getCurrentRoom().getTilesAsEntity());
        tmp.add((Entity) initiative.get(0));
        return tmp;
    }

    /**
     * 
     */
    @Override
    public void quit() {
        System.exit(0);
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
    public void start() {
        this.view.startView();
        initNewTurn();
        while (!this.view.isReady()) {
            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.view.setEntityToDraw(entityToUpload());
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
