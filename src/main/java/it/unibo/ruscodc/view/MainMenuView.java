package it.unibo.ruscodc.view;

import it.unibo.ruscodc.model.Entity;
import it.unibo.ruscodc.utils.Pair;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
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
    private int rows;
    private int cols;

    @FXML
    private ProgressBar hp;
    @FXML
    private ProgressBar ap;
    @FXML
    private Button inventory;
    @FXML
    private Button menu;


    public void setRoom(final List<Entity> obj1, final Pair<Integer, Integer> size) {
        this.cols = size.getX() + 2;
        this.rows = size.getY() + 2;
        final int maxDimension = Math.max(this.cols, this.rows);

        DoubleBinding binding = mainGrid.getScene().heightProperty().divide(maxDimension);
        if (mainGrid.getScene().getWidth() < mainGrid.getScene().getHeight()) {
            binding = mainGrid.getScene().widthProperty().divide(maxDimension);
        }

        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                final ImageView image = new ImageView(new Image("it/unibo/ruscodc/map_res/FloorTile/FloorTile.jpg"));

                image.fitWidthProperty().bind(binding);
                image.fitHeightProperty().bind(binding);

                this.mainGrid.add(new Pane(image), i, j);
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