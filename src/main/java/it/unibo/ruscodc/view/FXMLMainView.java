package it.unibo.ruscodc.view;

import it.unibo.ruscodc.controller.GameObserverController;
import it.unibo.ruscodc.model.Entity;
import it.unibo.ruscodc.model.gamemap.RectangleRoomImpl;
import it.unibo.ruscodc.model.gamemap.Room;
import it.unibo.ruscodc.model.outputinfo.InfoPayload;
import it.unibo.ruscodc.utils.GameControl;
import it.unibo.ruscodc.utils.Pair;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class FXMLMainView extends Application implements GameView {
    private static final double ASPECT_RATIO = 3/4.;
    private static final double MIN_WIDTH_SCALE = 0.4;

    private GameObserverController controller;
    private MainMenuView gameView;
    private List<Entity> entities;
    private boolean isReady;

    @Override
    public void startView(String[] args) {
        if (this.controller == null) throw new IllegalStateException(
                "Error in ViewJFX: The controller has not been initialized."
                        + " Please initialize the controller before starting the view."
        );

        Platform.startup(() -> {
            // create primary stage
            Stage stage = new Stage();

            try {
                this.start(stage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void init(GameObserverController ctrl) {
        this.controller = ctrl;

    }

    @Override
    public boolean isReady() {
        return this.isReady;
    }

    @Override
    public void printInfo(InfoPayload toPrint) {

    }

    @Override
    public void resetView(List<Entity> toDraw, Pair<Integer, Integer> roomSize) {
        this.entities = toDraw;
        //this.gameView.setRoom(toDraw, roomSize);
    }

    @Override
    public void addEntity(Entity toAdd) {
        if (!this.entities.contains(toAdd)) this.entities.add(toAdd);
    }


    @Override
    public void uploadEntity(Pair<Integer, Integer> toUpload, Entity updated) {
        for (int i = 0; i < this.entities.size(); i++) {
            if (this.entities.get(i).getID() == updated.getID()) {
                if (!this.entities.get(i).getPos().equals(toUpload)) {
                    this.entities.set(i, updated);
                }
            }
        }
    }

    @Override
    public void resetLevel(List<Entity> entities) {
        this.entities.removeIf(e -> e.getID() >= entities.get(0).getID());
        this.entities.addAll(entities);
    }

    @Override
    public void start(Stage stage) throws Exception {
        System.out.println("FXMLMainView start");
        final Scene scene = this.loadGameView();
        this.handleWindowSize(stage, scene);
        this.handleEvents(stage);
        this.handleUserInputs(scene);

        Room r = new RectangleRoomImpl(8, 6);
        this.gameView.setRoom(r.getTilesAsEntity(), r.getSize());

        //stage.setFullScreen(true);
        stage.setTitle("Rusco DC");
        stage.setScene(scene);
        stage.setUserData(this.controller);

        this.isReady = true;
        stage.show();
    }

    private Scene loadGameView() throws IOException {
        final Rectangle2D screenSize = Screen.getPrimary().getVisualBounds();
        final double scale = 2/3.;
        final double width = screenSize.getWidth() * scale;
        final FXMLLoader fxmlLoader = new FXMLLoader(FXMLMainView.class.getResource("main-menu-view.fxml"));
        final Scene scene = new Scene(fxmlLoader.load(), width, width * ASPECT_RATIO);
        this.gameView = (MainMenuView) fxmlLoader.getController();
        return scene;
    }

    private void handleWindowSize(final Stage stage, final Scene scene) {
        final double minWidth = Screen.getPrimary().getVisualBounds().getWidth() * MIN_WIDTH_SCALE;
        stage.setMinWidth(minWidth);
        stage.setMinHeight(minWidth * ASPECT_RATIO);
        stage.setMaxHeight(Screen.getPrimary().getVisualBounds().getHeight());
        stage.minHeightProperty().bind(scene.widthProperty().multiply(ASPECT_RATIO));
        stage.maxHeightProperty().bind(scene.widthProperty().multiply(ASPECT_RATIO));
    }

    private void handleEvents(final Stage stage) {
        stage.setOnCloseRequest(event -> {
            System.exit(0);
        });
    }

    private void handleUserInputs(final Scene scene) {
        scene.setOnKeyPressed((KeyEvent key) -> {
            System.out.println(key.getCode());
            this.controller.computeInput(this.getInput(key));
        });
    }

    private GameControl getInput(KeyEvent e){
        return switch (e.getCode()) {
            case W -> GameControl.MOVEUP;
            case A -> GameControl.MOVELEFT;
            case S -> GameControl.MOVEDOWN;
            case D -> GameControl.MOVERIGHT;
            case I -> GameControl.INVENTORY;
            case P -> GameControl.PAUSE;
            case F -> GameControl.INTERACT;
            case ESCAPE -> GameControl.CANCEL;
            case ENTER -> GameControl.CONFIRM;
            case BACK_SPACE -> GameControl.DELETE;
            case DIGIT1 -> GameControl.BASEATTACK;
            case DIGIT2 -> GameControl.ATTACK1;
            case DIGIT3 -> GameControl.ATTACK2;
            case DIGIT4 -> GameControl.ATTACK3;
            case DIGIT5 -> GameControl.ATTACK4;
            case DIGIT6 -> GameControl.USEQUICK;
            default -> GameControl.DONOTHING;
        };
    }

}