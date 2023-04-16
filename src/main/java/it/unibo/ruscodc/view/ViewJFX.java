package it.unibo.ruscodc.view;

import it.unibo.ruscodc.controller.GameObserverController;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.awt.*;

public class ViewJFX extends Application {
    final private String TITLE = "Junkrisers";
    final private String BASE_BG_COLOR = "#121212";
    final private String iconPath = "file:src/main/resources/racoon-head.png";


    final private Dimension screen;
    /** Width to screen ratio. */
    final private double wtsRatio = 3 / 5.0;
    /** Height to screen ratio. */
    final private double htsRatio = 4 / 5.0;

    private GraphicsContext context;
    private Scene mainScene;
    /** Contains the objects to render on screen. */
    private int scene; // TODO: change data type

    public ViewJFX() {
        System.out.println("view constr.");
        this.screen = Toolkit. getDefaultToolkit(). getScreenSize();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.init(primaryStage);
        this.gameloop(this.scene, this.context);
    }

    private void init(final Stage stage) {
        this.showWindow(stage);
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
    }

    private void gameloop(int scene, GraphicsContext context) {
        AnimationTimer gameloop = new AnimationTimer() {
            public void handle(long nanotime) {
                // TODO: updates view
            }
        };

        gameloop.start();
    }

}