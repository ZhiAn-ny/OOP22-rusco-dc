package it.unibo.ruscodc.view;

import it.unibo.ruscodc.controller.GameObserverController;
import it.unibo.ruscodc.model.Entity;
import it.unibo.ruscodc.model.gamemap.RectangleRoomImpl;
import it.unibo.ruscodc.model.gamemap.Room;
import it.unibo.ruscodc.model.outputinfo.InfoPayload;
import it.unibo.ruscodc.utils.Pair;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.util.List;

public class FXMLMainView extends Application implements GameView {

    private GameObserverController controller;
    private MainMenuView gameView;
    private List<Entity> entities;
    private double screenUnit;
    private MainMenuView view;


    public FXMLMainView(String... args) {
        FXMLMainView.launch(args);
    }

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
        return this.screenUnit > 0;
    }

    @Override
    public void printInfo(InfoPayload toPrint) {

    }

    @Override
    public void resetView(List<Entity> toDraw, Pair<Integer, Integer> roomSize) {
        this.entities = toDraw;
        this.view.setRoom(toDraw, roomSize);
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
        FXMLLoader fxmlLoader = new FXMLLoader(FXMLMainView.class.getResource("main-menu-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        this.gameView = (MainMenuView) fxmlLoader.getController();

        Room r = new RectangleRoomImpl(3, 3);
        this.gameView.setRoom(r.getTilesAsEntity(), r.getSize());

        stage.setMinWidth(Screen.getPrimary().getVisualBounds().getWidth()*0.5);
        stage.setMinHeight(Screen.getPrimary().getVisualBounds().getHeight()*0.5);
        stage.setFullScreen(true);
        stage.setTitle("Rusco DC");
        stage.setScene(scene);
        stage.setUserData(this.controller);
        stage.show();
        stage.setOnCloseRequest(event -> {
            System.exit(0);
        });
    }

}

