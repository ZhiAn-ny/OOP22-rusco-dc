package it.unibo.ruscodc.view;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import java.awt.*;


public class CanvasLevel {
    private GraphicsContext context;
    private Dimension screen;
    /** Width to screen ratio. */
    final private double wtsRatio = 3 / 5.0;
    /** Height to screen ratio. */
    final private double htsRatio = 4 / 5.0;
    private Scene scene;
    private Canvas canvas;

    public CanvasLevel(Dimension screen) {
        this.screen = Toolkit.getDefaultToolkit().getScreenSize();
    }

    StackPane stackCanvas = new StackPane();

    Canvas level0 = new Canvas(this.screen.getWidth() * this.wtsRatio, this.screen.getHeight() * this.htsRatio);
    Canvas level1 = new Canvas(this.screen.getWidth() * this.wtsRatio, this.screen.getHeight() * this.htsRatio);
    Canvas level2 = new Canvas(this.screen.getWidth() * this.wtsRatio, this.screen.getHeight() * this.htsRatio);
    Canvas level3 = new Canvas(this.screen.getWidth() * this.wtsRatio, this.screen.getHeight() * this.htsRatio);

    public void addStack(){
        stackCanvas.getChildren().addAll(level0, level1, level2, level3);
        scene = new Scene(stackCanvas);
    }

    public Canvas searchLevel(int level){
        return (Canvas) stackCanvas.getChildren().get(level);
    }

    public void resetLevel(int level){
        searchLevel(level);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0,0,canvas.getWidth(),canvas.getHeight());

    }



}
