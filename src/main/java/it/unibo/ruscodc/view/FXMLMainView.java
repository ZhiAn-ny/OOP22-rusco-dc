package it.unibo.ruscodc.view;

import it.unibo.ruscodc.controller.GameObserverController;
import it.unibo.ruscodc.model.Entity;
import it.unibo.ruscodc.model.outputinfo.InfoPayload;
import it.unibo.ruscodc.model.outputinfo.Portrait;
import it.unibo.ruscodc.utils.GameControl;
import it.unibo.ruscodc.utils.Pair;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.stage.Screen;
import javafx.stage.Stage;
import java.util.Optional;

import java.io.IOException;
import java.util.*;

public class FXMLMainView extends Application implements GameView {
    private static final double ASPECT_RATIO = 3/4.;
    private static final double MIN_WIDTH_SCALE = 0.4;

    private GameObserverController controller;
    private MainMenuView gameView;
    private final List<Entity> printedEntity = new ArrayList<>();
    private boolean isReady;
    private final Optional<Pair<Integer, Integer>> dims = Optional.empty();


    @Override
    public void startView(String[] args) {
        if (this.controller == null) {
            throw new IllegalStateException(
                    "Error in ViewJFX: The controller has not been initialized."
                            + " Please initialize the controller before starting the view."
            );
        }

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
        this.printedEntity.clear();
        this.printedEntity.addAll(toDraw);

        //int effecctiveX = roomSize.getX()+2;
        //int effectiveY = roomSize.getY()+2;
        //Pair<Integer, Integer> effectiveDim = new Pair<>(roomSize.getX()+2, roomSize.getY()+2);
        //dims.map(p -> new Pair<>(roomSize.getX()+2, roomSize.getY()+2));
        //this.gameView.setRoom(toDraw, roomSize);
        //javafx.util.Pair<Integer, Integer> effectiveDim2 = new javafx.util.Pair<>(roomSize.getX()+2, roomSize.getY()+2);


        //Map<Character, Integer> dim = new HashMap<>();

    }

    @Override
    public void addEntity(Entity toAdd) {
        if (!this.printedEntity.contains(toAdd)) this.printedEntity.add(toAdd);
    }


    @Override
    public void uploadEntity(Pair<Integer, Integer> toUpload, Entity updated) {
        for (int i = 0; i < this.printedEntity.size(); i++) {
            if (this.printedEntity.get(i).getID() == updated.getID()) {
                if (!this.printedEntity.get(i).getPos().equals(toUpload)) {
                    this.printedEntity.set(i, updated);
                }
            }
        }
    }

    @Override
    public void resetLevel(List<Entity> entities) {
        this.printedEntity.removeIf(e -> e.getID() >= entities.get(0).getID());
        this.printedEntity.addAll(entities);
    }

    @Override
    public void start(Stage stage) throws Exception {
        System.out.println("FXMLMainView start");
        final Scene scene = this.loadGameView();
        this.handleWindowSize(stage, scene);
        this.handleEvents(stage);
        this.handleUserInputs(scene);


        //stage.setFullScreen(true);
        stage.setTitle("Rusco DC");
        stage.setScene(scene);
        stage.setUserData(this.controller);

        this.isReady = true;
        stage.show();
    }

    private void uploadView() {

    }

    private Scene loadGameView() throws IOException {
        final Rectangle2D screenSize = Screen.getPrimary().getVisualBounds();
        final double scale = 2/3.;
        final double width = screenSize.getWidth() * scale;
        final FXMLLoader fxmlLoader = new FXMLLoader(FXMLMainView.class.getResource("main-menu-view.fxml"));
        final Scene scene = new Scene(fxmlLoader.load(), width, width * ASPECT_RATIO);
        this.gameView = (MainMenuView) fxmlLoader.getController();

        this.gameView.setEntities(this.printedEntity);
        this.gameView.update();


        //this.gameView.setRoomSize();
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
            System.out.println(this.getInput(key));
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
            default -> GameControl.DONOTHING;
        };
    }

    @Override
    public void uploadPortrait(Portrait infos) {
        // TODO Auto-generated method stub
        
    }

}