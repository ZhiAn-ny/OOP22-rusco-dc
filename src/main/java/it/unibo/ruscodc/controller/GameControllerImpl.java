package it.unibo.ruscodc.controller;

import it.unibo.ruscodc.model.Entity;
import it.unibo.ruscodc.model.GameModel;
import it.unibo.ruscodc.model.GameModelImpl;
import it.unibo.ruscodc.model.actors.Actor;
import it.unibo.ruscodc.model.actors.hero.Hero;
import it.unibo.ruscodc.model.actors.monster.Monster;
import it.unibo.ruscodc.model.gamecommand.GameCommand;
import it.unibo.ruscodc.utils.GameControl;
import it.unibo.ruscodc.utils.exception.ChangeFloorException;
import it.unibo.ruscodc.utils.exception.ChangeRoomException;
import it.unibo.ruscodc.utils.exception.ModelException;
import it.unibo.ruscodc.utils.outputinfo.InfoPayload;
import it.unibo.ruscodc.view.FXMLMainView;
import it.unibo.ruscodc.view.GameView;

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

    private final List<Actor> initiative = new ArrayList<>();
    private Optional<GameCommand> playerSituation = Optional.empty();
    private final GameView view;
    private GameModel model;
    private boolean automaticSave;

    private boolean isPrintingInv; 

    /**
     * Create the controller of the game.
     */
    public GameControllerImpl() {
        this.view = new FXMLMainView();
    }

    /** {@inheritDoc} */
    @Override
    public void init() {
        this.view.init(this);
    }

    /** {@inheritDoc} */
    @Override
    public void showMainMenu() {
        this.view.startView();
    }

    private void refresh() {
        initNewTurn();
        view.resetView(entityToUpload(), this.model.getCurrentRoom().getSize());
        manageMonsterTurn();
    }

    /** {@inheritDoc} */
    @Override
    public void initNewGame(final String filename) {
        playerSituation = Optional.empty();
        this.initiative.clear();
        this.model = new GameModelImpl();
        updateRuscoInfo();
        refresh();
    }

    @Override
    public void loadGame(final String fileName) {
        // NOT DONE IN THIS RELEASE
        // THIS FUNCION WILL BE UPLOADED FURTHER
    }

    /** {@inheritDoc} */
    @Override
    public void start() {
        this.showMainMenu();
    }

    /** {@inheritDoc} */
    @Override
    public void save() {
        // NOT DONE IN THIS RELEASE
        // THIS FUNCION WILL BE UPLOADED FURTHER
    }

    /** {@inheritDoc} */
    @Override
    public void changeAutomaticSave() {
        automaticSave = !automaticSave;
    }

    /**
     * Compute the entity to draw when a room change.
     * @return the entity to draw
     */
    private List<Entity> entityToUpload() {
        final List<Entity> tmp = new ArrayList<>(model.getCurrentRoom().getTilesAsEntity());
        tmp.addAll(model.getCurrentRoom().getObjectsInRoom());
        tmp.addAll(initiative);
        return tmp;
    }

    private void updateRuscoInfo() {
        this.view.uploadPortrait(this.model.getRuscoInfo());
    }

    private void changeFloor() {
        this.model.changeFloor();
        this.initiative.clear();
        this.initNewTurn();

        if (this.automaticSave) {
            this.save();
        }
        this.refresh();
    }

    private void changeRoom(final ChangeRoomException r) {
        this.model.changeRoom(r.getDoorPos());
        this.initiative.clear();
        this.refresh();
    }

    private void flushView() {
        this.view.resetLevel(this.model.getCurrentRoom().getObjectsInRoom().stream().map(i -> (Entity) i).toList());
        this.view.resetLevel(this.model.getActorByInitative().stream()
                .filter(Actor::isAlive)
                .map(a -> (Entity) a)
                .toList());
    }

    private void printCommand() {
        final List<Entity> infos = playerSituation.get().getEntities();
        this.view.resetLevel(new ArrayList<>(infos));
        if (isPrintingInv) {
            this.view.printStats(this.initiative.get(0).toString());
        }
    }

    private boolean executeCommand(final GameCommand toExec) {
        final boolean ready = toExec.isReady();

        if (ready) {
            try {
                final Optional<InfoPayload> tmp = toExec.execute();

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
                if (this.model.isGameWin()) {
                    this.view.printGameWin();
                }

            } catch (ChangeRoomException r) {
                changeRoom(r);
                playerSituation = Optional.empty();

            } catch (ModelException u) {
                view.closeInventory();
                playerSituation = Optional.empty();
                flushView();
            }
        }
        updateRuscoInfo();
        return ready;
    }

    /** {@inheritDoc} */
    @Override
    public void computeInput(final GameControl input) {
        updateRuscoInfo();
        if (model.isGameOver()) {
            this.view.printGameOver();
            return;
        }
        initNewTurn();
        if (initiative.get(0) instanceof Hero) {
            final Hero tmpActor = (Hero) initiative.get(0);
            final GameCommand tmpCommand;

            if (playerSituation.isPresent()) {
                tmpCommand = playerSituation.get();

                if (tmpCommand.modify(input)) {
                    printCommand();
                }
                executeCommand(tmpCommand);

            } else {
                final Optional<GameCommand> result = tmpActor.act(input);
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
                } else {
                    playerSituation = Optional.of(tmpCommand);
                    printCommand();
                }
            }
        }
        manageMonsterTurn();
    }

    /** {@inheritDoc} */
    @Override
    public void quit() {

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
        final List<Actor> tmp = new ArrayList<>();
        for (final Actor a : this.model.getActorByInitative()) {
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
                flushView();
            } else {
                initiative.remove(0);
                this.model.eliminateMonster(tmpMonster);
            }
            initNewTurn();
        }
    }

}
