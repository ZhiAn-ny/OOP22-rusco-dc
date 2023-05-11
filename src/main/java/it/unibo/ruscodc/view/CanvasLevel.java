package it.unibo.ruscodc.view;

import javafx.scene.canvas.Canvas;
import java.util.Stack;


public class CanvasLevel {
    private Stack<Canvas> stackCanvas = new Stack<>();


    public Canvas getLastLevel(){
        return stackCanvas.peek();
    }

    public void addStack(Canvas canvasToAdd) {
        stackCanvas.push(canvasToAdd);
    }

    public void deleteLastLevel(){
        stackCanvas.pop();
    }

    public void clearStack(){
        stackCanvas.clear();
    }



}
