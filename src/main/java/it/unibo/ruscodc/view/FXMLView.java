package it.unibo.ruscodc.view;

import it.unibo.ruscodc.controller.GameObserverController;
import it.unibo.ruscodc.model.Entity;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.List;

public class FXMLView extends Application implements GameView {

    @Override
    public void startView() {

    }

    @Override
    public void init(GameObserverController ctrl) {

    }

    @Override
    public boolean isReady() {
        return false;
    }

    @Override
    public void printError(String err) {

    }

    @Override
    public void setEntityToDraw(List<Entity> toDraw) {

    }

    @Override
    public void start(Stage primaryStage) throws Exception {

    }
}
