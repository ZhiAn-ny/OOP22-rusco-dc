package it.unibo.ruscodc.view;
import it.unibo.ruscodc.controller.GameObserverController;
import it.unibo.ruscodc.model.Entity;
import it.unibo.ruscodc.utils.GameControl;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ViewJFX extends Application implements GameView {
    final private String TITLE = "Junkrisers";
    final private String BASE_BG_COLOR = "#4e533d";
    final private String iconPath = "file:src/main/resources/it/unibo/ruscodc/hero_res/racoon-head.png";

    final private Dimension screen;
    /** Width to screen ratio. */
    final private double wtsRatio = 3 / 5.0;
    /** Height to screen ratio. */
    final private double htsRatio = 4 / 5.0;
    private double screenUnit;
    private final int maxRoomSize; // TODO: prendere dal controller/model(?)

    private GraphicsContext context;
    private Scene mainScene;

    private GameObserverController controller;
    /** Contains the objects to render on screen. */
    final private CopyOnWriteArrayList<Drawable<GraphicsContext>> scene;

    public ViewJFX() {
        this.screen = Toolkit.getDefaultToolkit().getScreenSize();
        this.scene = new CopyOnWriteArrayList<Drawable<GraphicsContext>>();
        this.maxRoomSize = 20;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.setup(primaryStage);

        this.gameloop(this.context);
    }

    /**
     * Sets up the view's window and the event handles.
     *
     * @param stage  the primary stage for this application, onto which the application scene can be set.
     *               Applications may create other stages, if needed, but they will not be primary stages.
     */
    private void setup(final Stage stage) {
        this.showWindow(stage);
        this.setKeyListeners();
        this.handleEvents(stage);
    }

    /**
     * Display application's window
     *
     * @param stage  the primary stage for this application, onto which the application scene can be set.
     *               Applications may create other stages, if needed, but they will not be primary stages.
     */
    private void showWindow(final Stage stage) {
        BorderPane root = new BorderPane();
        Canvas canvas = new Canvas(this.screen.getWidth() * this.wtsRatio,
                this.screen.getHeight() * this.htsRatio);
        this.context = canvas.getGraphicsContext2D();

        root.setCenter(canvas);

        this.mainScene = new Scene(root);
        this.mainScene.setFill(Color.web(this.BASE_BG_COLOR));

        stage.getIcons().add(new Image(this.iconPath));
        stage.setTitle(this.TITLE);
        stage.setScene(this.mainScene);
        stage.show();
        this.setScreenUnit(stage);
    }

    /**
     * Handles the user's inputs. Every input is transmitted to the controller,
     * which will parse and execute the commands
     */
    private void setKeyListeners() {
        this.mainScene.setOnKeyPressed((KeyEvent key) -> {
            System.out.println(key.getCode());
            this.controller.computeInput(this.getInput(key));
        });
    }

    private void setScreenUnit(Stage stage) {
        int offset = 4;
        double minDim = Math.min(stage.getWidth(), stage.getHeight());
        this.screenUnit = minDim / (this.maxRoomSize + offset);
    }

    private GameControl getInput(KeyEvent e){
        return switch (e.getCode()) {
            case W -> GameControl.MOVEUP;
            case A -> GameControl.MOVELEFT;
            case S -> GameControl.MOVEDOWN;
            case D -> GameControl.MOVERIGHT;
            case I -> GameControl.INVENTORY;
            case P -> GameControl.PAUSE;
            case ESCAPE -> GameControl.CANCEL;
            case ENTER -> GameControl.CONFIRM;
            case F -> GameControl.INTERACT;
            case DIGIT1 -> GameControl.BASEATTACK;
            case DIGIT2 -> GameControl.ATTACK1;
            case DIGIT3 -> GameControl.ATTACK2;
            case DIGIT4 -> GameControl.ATTACK3;
            case DIGIT5 -> GameControl.ATTACK4;
            case DIGIT6 -> GameControl.USEQUICK;
            default -> GameControl.DONOTHING;
        };
    }

    /**
     * Handles the system events.
     *
     * @param stage  the primary stage for this application, onto which the application scene can be set.
     *               Applications may create other stages, if needed, but they will not be primary stages.
     */
    private void handleEvents(Stage stage) {
        stage.setOnCloseRequest(t -> {
            Platform.exit();
            System.exit(0);
        });

        ChangeListener<Number> stageSizeListener = (observable, oldValue, newValue) -> {
            // System.out.println("Height: " + stage.getHeight() + " Width: " + stage.getWidth());
            this.setScreenUnit(stage);
            this.scene.forEach(d -> d.updateScreenUnit(this.screenUnit));
            this.context.clearRect(0, 0, stage.getWidth(), stage.getHeight());
        };
        stage.widthProperty().addListener(stageSizeListener);
        stage.heightProperty().addListener(stageSizeListener);
    }

    /**
     * Sets up the actions done in the view's gameloop and starts it.
     *
     * @param context the graphic context from the application.
     */
    private void gameloop(GraphicsContext context) {
        AnimationTimer gameloop = new AnimationTimer() {
           public void handle(long nanotime) {
               scene.forEach(drw -> drw.render(context));
           }
        };

        gameloop.start();
    }

    @Override
    public void startView() {
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
        return this.screenUnit > 0;
    }

    @Override
    public void printError(String err) {
        System.err.println("ERROR: " + err);
    }

    @Override
    public void setEntityToDraw(List<Entity> toDraw) {
        scene.clear();
        toDraw.stream().map(e-> {
            Drawable<GraphicsContext> drw = new JFXDrawableImpl(e, this.screenUnit);
            drw.setSize(1);
            return drw;
        }).forEach(scene::add);


    }

}
