package it.unibo.ruscodc.view;

import it.unibo.ruscodc.model.Entity;
import it.unibo.ruscodc.controller.GameControllerImpl;
import it.unibo.ruscodc.controller.GameObserverController;
import it.unibo.ruscodc.utils.Pair;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.beans.binding.Bindings;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class MainMenuView implements Initializable {
    private static final int TILES_RENDERING_LEVEL = 1;

    @FXML
    private GridPane mainGrid;
    IntegerProperty unit = new SimpleIntegerProperty();
    private int rows = 2;
    private int cols = 1;

    @FXML
    private ProgressBar hp;
    @FXML
    private ProgressBar ap;
    @FXML
    private Button inventory;
    @FXML
    private Button menu;


/*
    public MainMenuView(Stage stage){
        this.stage = stage;
        this.gameController = (GameControllerImpl)stage.getUserData();
    }*/


    public void setRoom(final List<Entity> obj1, final Pair<Integer, Integer> size) {
        this.cols = size.getX();
        this.rows = size.getY();

        for (int i = 0; i < rows+2; i++) {
            for (int j = 0; j < cols+2; j++) {
                var image = new ImageView(new Image("it/unibo/ruscodc/map_res/FloorTile/FloorTile.jpg"));
                //var pane = new ImageView(new Image(entity.get().getPath()+ "/Sprite.png"));
                mainGrid.sceneProperty().addListener((observable, oldScene, newScene) -> {
                    if (newScene != null) {
                        unit.bind(Bindings.min(mainGrid.getScene().widthProperty(), mainGrid.getScene().heightProperty()).divide(100));
                    }
                    if (!image.fitWidthProperty().isBound() && newScene != null) {
                        //var binding = Bindings.min(mainGrid.getScene().widthProperty(), mainGrid.getScene().heightProperty().divide(30));
                        var binding = mainGrid.getScene().widthProperty().divide(10);
                        image.fitWidthProperty().bind(binding);
                        image.fitHeightProperty().bind(binding);
                    }
                });

                var binding = mainGrid.getScene().widthProperty().divide(10);
                image.fitWidthProperty().bind(binding);
                image.fitHeightProperty().bind(binding);


                this.mainGrid.add(new Pane(image), i, j);
            }
        }




//        for (int i = 0; i < cols+2; i++){
//            for (int j = 0; j < rows+2; j++){
//                final Optional<Entity> entity = this.getTileAtPosition(obj1, i,j);
//                if (entity.isEmpty()) {
//                    continue;
//                }
//
//                var pane = new ImageView(new Image(entity.get().getPath()+ "/Sprite.png"));
//
//                this.mainGrid.sceneProperty().addListener((observable, oldScene, newScene) -> {
//                    if (newScene != null) {
//                        unit.bind(Bindings.min(mainGrid.getScene().widthProperty(), mainGrid.getScene().heightProperty()).divide(100));
//                    }
//                    if (!pane.fitWidthProperty().isBound() && newScene != null){
//                        pane.fitWidthProperty().bind(Bindings.min(mainGrid.getScene().widthProperty(), mainGrid.getScene().heightProperty()).divide(rows));
//                        pane.fitHeightProperty().bind(Bindings.min(mainGrid.getScene().widthProperty(), mainGrid.getScene().heightProperty()).divide(cols));
//                    }
//                });
//                this.mainGrid.add(new Pane(pane), i, j);
//
//            }
//        }
    }

    private Optional<Entity> getTileAtPosition(final List<Entity> entities, final int x, final int y) {
        return entities.stream().filter(e -> e.getID() == TILES_RENDERING_LEVEL)
                .filter(t -> t.getPos().getX().equals(x) && t.getPos().getY().equals(y))
                .findFirst();
    }

    @FXML
    public void initialize(URL location, ResourceBundle resources) {

    }
}
