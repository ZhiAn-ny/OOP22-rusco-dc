package it.unibo.ruscodc.view;

import it.unibo.ruscodc.controller.GameObserverController;
import it.unibo.ruscodc.model.Entity;
import it.unibo.ruscodc.model.outputinfo.InfoPayload;
import it.unibo.ruscodc.model.outputinfo.InfoPayloadImpl;
import it.unibo.ruscodc.model.outputinfo.Portrait;
import it.unibo.ruscodc.utils.GameControl;
import it.unibo.ruscodc.utils.Pair;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.io.IOException;

/**
 * This class is used to see which entities have changed their position or,
 * which are the new entities that will then be printed on the screen.
 */
@SuppressWarnings("FB_EXIT")
public class FXMLMainView extends Application implements GameView {

    private static final String GLOBAL_ERR_TITLE = "Error in view tecnology";

    private static final String ICONPATH = "file:src/main/resources/it/unibo/ruscodc/view/racoon.png";
    private static final String TITLE = "Rusco DC";
    private static final double ASPECT_RATIO = 3 / 4.;
    private static final double MIN_WIDTH_SCALE = 0.4;
    private GameObserverController controller;

    private GameViewController gameView;
    private MainMenuController menuController;

    private final List<Entity> printedEntity = new ArrayList<>();
    private Stage stage;

    private boolean isPrintingInfo;

    /** {@inheritDoc} */
    @Override
    public void startView() {
        if (this.controller == null) {
            throw new IllegalStateException(
                    "Error in ViewJFX: The controller has not been initialized."
                            + " Please initialize the controller before starting the view."
            );
        }

        Platform.startup(() -> {
            this.stage = new Stage();
            this.start(stage);
        });
    }

    /** {@inheritDoc} */
    @Override
    public void init(final GameObserverController ctrl) {
        this.controller = ctrl;
    }

    /**
     * Init a new game.
     * @param filename the name of the file where the saves will take place
     */
    public void startNewGame(final String filename) {
        Scene scene;
        try {
            scene = this.loadGameView();
        } catch (IOException e) {
            printInfo(new InfoPayloadImpl(
                GLOBAL_ERR_TITLE, 
                "Cannot load main menu view"));
            return;
        }
        this.controller.initNewGame("");
        stage.setScene(scene);
    }

    /** {@inheritDoc} */
    @Override
    public void printInfo(final InfoPayload toPrint) {
        isPrintingInfo = true;
        gameView.printInfoPalyodToScreen(
                new Image(toPrint.getPath() + "/Sprite.png"),
                toPrint.title(),
                toPrint.text());
    }

    /** {@inheritDoc} */
    @Override
    public void resetLevel(final List<Entity> entities) {
        gameView.updateEntities(entities);
    }

    /** {@inheritDoc} */
    @Override
    public void resetView(final List<Entity> toDraw, final Pair<Integer, Integer> roomSize) {
        this.printedEntity.clear();
        this.printedEntity.addAll(toDraw);
        this.gameView.setRoomSize(roomSize);
        this.resetLevel(toDraw);
    }

    private void showMainMenu() {
        Scene scene;
        try {
            scene = this.loadMainMenu();
        } catch (IOException e) {
            printInfo(new InfoPayloadImpl(
                GLOBAL_ERR_TITLE, 
                "Cannot load main menu view"));
            return;
        }

        this.handleEvents(stage);

        stage.setTitle(TITLE);
        stage.getIcons().add(new Image(ICONPATH));
        stage.setScene(scene);
        stage.setUserData(this.controller);

        stage.show();
        this.menuController.setLayout();
    }

    /** {@inheritDoc} */
    @Override
    public void start(final Stage stage) {
        this.stage = Collections.nCopies(1, stage).get(0);
        showMainMenu();
    }

    /**
     * Returns in to the main menu.
     */
    public void returnToMainMenu() {
        showMainMenu();
    }

    private Scene loadMainMenu() throws IOException {
        final Rectangle2D screenSize = Screen.getPrimary().getVisualBounds();
        final double scale = 2 / 3.;
        final double width = screenSize.getWidth() * scale;
        final FXMLLoader fxmlLoader = new FXMLLoader(FXMLMainView.class.getResource("menu-iniziale.fxml"));

        final Scene scene = new Scene(fxmlLoader.load(), width, width * ASPECT_RATIO);
        this.menuController = (MainMenuController) fxmlLoader.getController();
        this.menuController.init(this);
        return scene;
    }

    private Scene loadGameView() throws IOException {
        final Rectangle2D screenSize = Screen.getPrimary().getVisualBounds();
        final double scale = 2 / 3.;
        final double width = screenSize.getWidth() * scale;
        final FXMLLoader fxmlLoader = new FXMLLoader(FXMLMainView.class.getResource("game-view.fxml"));

        final Scene scene = new Scene(fxmlLoader.load(), width, width * ASPECT_RATIO);

        this.gameView = (GameViewController) fxmlLoader.getController();
        this.gameView.init(this);
        this.handleUserInputs(scene);
        //this.gameView.updateEntities(this.printedEntity);
        //this.gameView.update();
        this.gameView.clearInfoPalyodToScreen();
        return scene;
    }

    private Scene loadGameOver() throws IOException {
        final Rectangle2D screenSize = Screen.getPrimary().getVisualBounds();
        final double scale = 2 / 3.;
        final double width = screenSize.getWidth() * scale;
        final FXMLLoader fxmlLoader = new FXMLLoader(FXMLMainView.class.getResource("game-over.fxml"));
        final Scene scene = new Scene(fxmlLoader.load(), width, width * ASPECT_RATIO);
        final GameOverController gameOverController = (GameOverController) fxmlLoader.getController();
        gameOverController.init(this);
        gameOverController.backToMenu();
        return scene;
    }

    private void handleEvents(final Stage stage) {
        stage.setOnCloseRequest(event -> {
            System.exit(0);
        });
    }

    private void handleUserInputs(final Scene scene) {
        scene.setOnKeyPressed((KeyEvent key) -> {
            if (isPrintingInfo) {
                gameView.clearInfoPalyodToScreen();
                isPrintingInfo = false;
            } else {
                System.out.println(key.getText() + " " + this.getInput(key));
                this.controller.computeInput(this.getInput(key));
            }
        });
    }

    private GameControl getInput(final KeyEvent e) {
        return switch (e.getCode()) {
            case W -> GameControl.MOVEUP;
            case A -> GameControl.MOVELEFT;
            case S -> GameControl.MOVEDOWN;
            case D -> GameControl.MOVERIGHT;
            case I -> GameControl.INVENTORY;
            case F -> GameControl.INTERACT;
            case ESCAPE -> GameControl.CANCEL;
            case C -> GameControl.CONFIRM;
            case BACK_SPACE -> GameControl.DELETE;
            case DIGIT1 -> GameControl.BASEATTACK;
            case DIGIT2 -> GameControl.ATTACK1;
            case DIGIT3 -> GameControl.ATTACK2;
            case DIGIT4 -> GameControl.ATTACK3;
            case DIGIT5 -> GameControl.ATTACK4;
            default -> GameControl.DONOTHING;
        };
    }

    /** {@inheritDoc} */
    @Override
    public void uploadPortrait(final Portrait infos) {
        gameView.uploadPortraitToScreen(infos);
    }

    /** {@inheritDoc} */
    @Override
    public void printGameOver() {
        Scene scene;
        try {
            scene = this.loadGameOver();
        } catch (IOException e) {
            this.printInfo(new InfoPayloadImpl(
                GLOBAL_ERR_TITLE,
                "... but you are died!"));
            return;
        }
        stage.setScene(scene);
    }

    /** {@inheritDoc} */
    @Override
    public void openInventory() {
        this.gameView.openInv();
    }

    /** {@inheritDoc} */
    @Override
    public void closeInventory() {
        this.gameView.closeInv();
    }

    /** {@inheritDoc} */
    @Override
    public void printStats(final String heroStats) {
        this.gameView.uploadStatsToScreen(heroStats);
    }

    /**
     * Save the game.
     */
    public void saveGame() {
        this.controller.save();
    }

    /**
     * Choose whether to save the game automatically or not.
     */
    public void changeAutomaticSave() {
        this.controller.changeAutomaticSave();
    }
}
