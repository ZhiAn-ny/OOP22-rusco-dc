package it.unibo.ruscodc.controller;

import it.unibo.ruscodc.model.Entity;
import it.unibo.ruscodc.model.GameModel;
import it.unibo.ruscodc.model.GameModelImpl;
import it.unibo.ruscodc.model.actors.Actor;
import it.unibo.ruscodc.model.actors.Hero;
import it.unibo.ruscodc.model.actors.Monster;
import it.unibo.ruscodc.model.gamecommand.BuilderGameCommand;
import it.unibo.ruscodc.model.gamecommand.MoveBuilder;
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
    private Optional<BuilderGameCommand> actualInstant = Optional.empty();
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


    /**
     * Compute the input of user and execute a specific action according to it.
     * @param input input of the user
     */
    @Override
    public void computeInput(final GameControl input) {
        if (initiative.get(0) instanceof Hero) {
            Hero tmp = (Hero) initiative.get(0);
            if (actualInstant.isPresent()) {
               // actualInstant.get().modify(input);
            } else {
                actualInstant = Optional.of(tmp.act(input));
                actualInstant.get().setRoom(this.model.getCurrentRoom());
            }

            if (actualInstant.get().isReady()) {
                try {
                    actualInstant.get().execute();
                    actualInstant = Optional.empty();

                    view.setEntityToDraw(entityToUpload());
                    initiative.remove(0);
                    manageMonsterTurn();
                } catch (ModelException m) {
                    if (actualInstant.get() instanceof MoveBuilder) {
                        actualInstant = Optional.empty();
                    }
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
        Monster tmp;
        initNewTurn();
        while (initiative.get(0) instanceof Monster) {
            tmp = (Monster) initiative.get(0);
            //tmp.behave();
            initiative.remove(0);
            initNewTurn();
        }
    }

}
