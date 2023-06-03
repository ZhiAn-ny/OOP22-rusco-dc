package it.unibo.ruscodc.view;

import it.unibo.ruscodc.controller.GameObserverController;
import it.unibo.ruscodc.model.Entity;
import it.unibo.ruscodc.model.outputinfo.InfoPayload;
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
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.io.IOException;

/**
 * This class is used to see which entities have changed their position or,
 * which are the new entities that will then be printed on the screen.
 */
public class FXMLMainView extends Application implements GameView {
    final private String iconPath = "file:src/main/resources/it/unibo/ruscodc/view/racoon.png";
    final private String title = "Rusco DC";
    private static final double ASPECT_RATIO = 3 / 4.;
    private static final double MIN_WIDTH_SCALE = 0.4;
    private GameObserverController controller;
    private GameViewController gameView;
    private MainMenuController menuController;
    private GameOverController gameOverController;
    private final List<Entity> printedEntity = new ArrayList<>();
    private boolean isReady;
    private final Optional<Pair<Integer, Integer>> dims = Optional.empty();
    private boolean isPrintingInfo = true;
    private Stage stage;


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
            // create primary stage
            this.stage = new Stage();
            try {
                this.start(stage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /** {@inheritDoc} */
    @Override
    public void init(final GameObserverController ctrl) {
        this.controller = ctrl;

    }

    public void startNewGame() throws IOException {
        final Scene scene = this.loadGameView();
        this.controller.initNewGame();
        //stage.setUserData(this.controller);
        //this.isReady = true;
        stage.setScene(scene);
       // stage.show();
    }


    /** {@inheritDoc} */
    @Override
    public boolean isReady() {
        return this.isReady;
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
    public void resetView(final List<Entity> toDraw, final Pair<Integer, Integer> roomSize) {

        this.printedEntity.clear();
        this.printedEntity.addAll(toDraw);
        this.gameView.updateEntities(toDraw);
        this.gameView.setRoomSize(roomSize);


        // this.printedEntity.clear();
        // this.printedEntity.addAll(toDraw);
        // int effecctiveX = roomSize.getX()+2;
        // int effectiveY = roomSize.getY()+2;
        // Pair<Integer, Integer> effectiveDim = new Pair<>(roomSize.getX()+2, roomSize.getY()+2);
        // dims.map(p -> new Pair<>(roomSize.getX()+2, roomSize.getY()+2));
        // this.gameView.setRoomSize(roomSize);
        // javafx.util.Pair<Integer, Integer> effectiveDim2 = new javafx.util.Pair<>(roomSize.getX()+2, roomSize.getY()+2);

        // Map<Character, Integer> dim = new HashMap<>();

    }

    /** {@inheritDoc} */
    @Override
    public void addEntity(final Entity toAdd) {
        if (!this.printedEntity.contains(toAdd)) {
            this.printedEntity.add(toAdd);
        }
    }

    /** {@inheritDoc} */
    @Override
    public void uploadEntity(final Pair<Integer, Integer> toUpload, final Entity updated) {
        for (int i = 0; i < this.printedEntity.size(); i++) {
            if (this.printedEntity.get(i).getID() == updated.getID()) {
                if (!this.printedEntity.get(i).getPos().equals(toUpload)) {
                    this.printedEntity.set(i, updated);
                }
            }
        }
    }

    /** {@inheritDoc} */
    @Override
    public void resetLevel(final List<Entity> entities) {
        //int minDepth = entities.stream().min(Comparator.comparingInt(e -> e.getID())).get().getID();
        //this.printedEntity.removeIf(e -> e.getID() >= minDepth);
        //this.printedEntity.addAll(entities);
        gameView.updateEntities(entities);
    }

    private void showMainMenu() throws IOException {
        final Scene scene = this.loadMainMenu();
        //this.handleWindowSize(stage, scene);
        this.handleEvents(stage);
        // this.handleUserInputs(scene);


        //stage.setFullScreen(true);
        stage.setTitle(this.title);
        stage.getIcons().add(new Image(this.iconPath));
        stage.setScene(scene);
        stage.setUserData(this.controller);

        this.isReady = true;
        stage.show();
        this.menuController.setLayout();
    }

    /** {@inheritDoc} */
    @Override
    public void start(final Stage stage) throws Exception {
        this.stage = stage;
        showMainMenu();
    }

    public void returnToMainMenu() throws IOException {
        showMainMenu();
    }

    private void uploadView() {

    }

    private Scene loadMainMenu() throws IOException {
        final Rectangle2D screenSize = Screen.getPrimary().getVisualBounds();
        final double scale = 2 / 3.;
        final double width = screenSize.getWidth() * scale;
        final FXMLLoader fxmlLoader = new FXMLLoader(FXMLMainView.class.getResource("menu-iniziale.fxml"));
        //fxmlLoader.setController(new MainMenuController());
        final Scene scene = new Scene(fxmlLoader.load(), width, width * ASPECT_RATIO);
        this.menuController = (MainMenuController) fxmlLoader.getController();
        this.menuController.init(this);
        return scene;
    }

    private Scene loadGameView() throws IOException {
        final FXMLLoader fxmlLoader = new FXMLLoader(FXMLMainView.class.getResource("game-view.fxml"));
        fxmlLoader.setController(new GameViewController());
        final Scene scene = new Scene(fxmlLoader.load());
        //this.handleWindowSize(stage, scene);
        //this.handleEvents(stage);
        this.gameView = (GameViewController) fxmlLoader.getController();
        this.handleUserInputs(scene);
        this.gameView.updateEntities(this.printedEntity);
        this.gameView.update();
        this.gameView.clearInfoPalyodToScreen();

        return scene;
    }

    private Scene loadGameOver() throws IOException {
        final FXMLLoader fxmlLoader = new FXMLLoader(FXMLMainView.class.getResource("game-over.fxml"));
        final Scene scene = new Scene(fxmlLoader.load());
        this.gameOverController = (GameOverController) fxmlLoader.getController();
        this.gameOverController.init(this);
        //gameOverController.backToMenu();
        return scene;
    }

    private void handleWindowSize(final Stage stage, final Scene scene) {
        try {
            final double minWidth = Screen.getPrimary().getVisualBounds().getWidth() * MIN_WIDTH_SCALE;
            stage.setMinWidth(minWidth);
            stage.setMinHeight(minWidth * ASPECT_RATIO);
            stage.setMaxHeight(Screen.getPrimary().getVisualBounds().getHeight());
            stage.minHeightProperty().bind(scene.widthProperty().multiply(ASPECT_RATIO));
            stage.maxHeightProperty().bind(scene.widthProperty().multiply(ASPECT_RATIO));
        } catch (final Exception e) {
            e.printStackTrace();
        }
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
        //System.out.println(e.getText());
        return switch (e.getCode()) {
            case W -> GameControl.MOVEUP;
            case A -> GameControl.MOVELEFT;
            case S -> GameControl.MOVEDOWN;
            case D -> GameControl.MOVERIGHT;
            case I -> GameControl.INVENTORY;
            case P -> GameControl.PAUSE;
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

    @Override
    public void printGameOver() throws IOException {
        System.out.println("Game Over");
        final Scene scene = this.loadGameOver();
        stage.setScene(scene);

    }

}
