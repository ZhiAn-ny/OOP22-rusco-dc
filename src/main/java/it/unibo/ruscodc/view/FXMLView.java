package it.unibo.ruscodc.view;

import it.unibo.ruscodc.controller.GameObserverController;
import it.unibo.ruscodc.launcher.Launcher;
import it.unibo.ruscodc.model.Entity;
import it.unibo.ruscodc.utils.Pair;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.util.List;

public class FXMLView extends Application implements GameView {

    public FXMLView(){
    }

    @Override
    public void startView() {
        try {
            this.start(new Stage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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
            FXMLLoader fxmlLoader = new FXMLLoader(Launcher.class.getResource("main-menu-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 320, 240);
            stage.setMinWidth(Screen.getPrimary().getVisualBounds().getWidth()*0.5);
            stage.setMinHeight(Screen.getPrimary().getVisualBounds().getHeight()*0.5);
            stage.setFullScreen(true);
            stage.setTitle("Hello!");
            stage.setScene(scene);
            stage.show();
    }
}
