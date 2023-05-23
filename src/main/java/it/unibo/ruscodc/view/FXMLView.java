package it.unibo.ruscodc.view;

import it.unibo.ruscodc.controller.GameObserverController;
import it.unibo.ruscodc.model.Entity;
import it.unibo.ruscodc.utils.Pair;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.util.List;

public class FXMLView extends Application implements GameView {

    private GameObserverController controller;
    private MainMenuView view;

    public FXMLView(){

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
        this.view = new MainMenuView();

    }

    @Override
    public boolean isReady() {
        return false;
    }

    @Override
    public void printError(String err) {

    }


    @Override
    public void resetView(List<Entity> toDraw, Pair<Integer, Integer> roomSize) {

    }

    @Override
    public void addEntity(Entity toAdd) {

    }

    @Override
    public void removeEntity(Entity toRemove) {

    }

    @Override
    public void uploadEntity(Entity toUpload) {

    }

    @Override
    public void start(Stage stage) throws Exception {
           view.loadView(stage);
    }



}

