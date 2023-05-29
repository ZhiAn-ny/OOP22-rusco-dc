package it.unibo.ruscodc.view;

import it.unibo.ruscodc.model.Entity;
import it.unibo.ruscodc.model.gamemap.Tile;
import it.unibo.ruscodc.utils.Pair;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.beans.binding.Bindings;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class MainMenuView implements Initializable {
    private static final int TILES_RENDERING_LEVEL = 1;

    @FXML
    private GridPane mainGrid;
    IntegerProperty unit = new SimpleIntegerProperty();
    private int rows = 2;
    private int cols = 1;

    public void setRoomTMP(List<Entity> obj1, Pair<Integer, Integer> size) {
        this.cols = size.getX();
        this.rows = size.getY();

        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {
                var pane = new ImageView(new Image("file:src/main/resources/it/unibo/ruscodc/map_res/FloorTile/FloorTile.jpg"));
                this.mainGrid.sceneProperty().addListener((observable, oldScene, newScene) -> {
                    if (newScene != null) {
                        unit.bind(Bindings.min(mainGrid.getScene().widthProperty(), mainGrid.getScene().heightProperty()).divide(100));
                    }
                    if (!pane.fitWidthProperty().isBound() && newScene != null){
                        pane.fitWidthProperty().bind(Bindings.min(mainGrid.getScene().widthProperty(), mainGrid.getScene().heightProperty()).divide(rows));
                        pane.fitHeightProperty().bind(Bindings.min(mainGrid.getScene().widthProperty(), mainGrid.getScene().heightProperty()).divide(cols));
                    }
                });
                this.mainGrid.add(new Pane(pane), i, j);
            }
        }
    }

    public void setRoom(final List<Entity> obj1, final Pair<Integer, Integer> size) {
        this.cols = size.getX();
        this.rows = size.getY();

        for (int i = 0; i < cols+2; i++){
            for (int j = 0; j < rows+2; j++){
                final Optional<Entity> entity = this.getTileAtPosition(obj1, i,j);
                if (entity.isEmpty()) {
                    continue;
                }

                var pane = new ImageView(new Image(entity.get().getPath()+ "/Sprite.png"));
                this.mainGrid.sceneProperty().addListener((observable, oldScene, newScene) -> {
                    if (newScene != null) {
                        unit.bind(Bindings.min(mainGrid.getScene().widthProperty(), mainGrid.getScene().heightProperty()).divide(100));
                    }
                    if (!pane.fitWidthProperty().isBound() && newScene != null){
                        pane.fitWidthProperty().bind(Bindings.min(mainGrid.getScene().widthProperty(), mainGrid.getScene().heightProperty()).divide(rows));
                        pane.fitHeightProperty().bind(Bindings.min(mainGrid.getScene().widthProperty(), mainGrid.getScene().heightProperty()).divide(cols));
                    }
                });
                this.mainGrid.add(new Pane(pane), i, j);
            }
        }
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
