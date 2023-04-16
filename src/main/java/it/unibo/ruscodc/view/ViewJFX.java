package it.unibo.ruscodc.view;

import javafx.application.Application;
import javafx.stage.Stage;

public class ViewJFX extends Application implements GameView {

    @Override
    public void start(Stage primaryStage) throws Exception {
        
    }

    @Override
    public void start() {

    }

    @Override
    public void printError(String err) {
        System.err.println("ERROR: " + err);
    }

}