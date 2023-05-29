package it.unibo.ruscodc.view;

import it.unibo.ruscodc.model.Entity;
import it.unibo.ruscodc.utils.Pair;
import javafx.animation.AnimationTimer;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class MainMenuView implements Initializable {
    private static final int TILES_RENDERING_LEVEL = 1;
    private static final int OBJECTS_RENDERING_LEVEL = 2;
    private static final int ACTORS_RENDERING_LEVEL = 3;
    private List<Entity> toRender;

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


    public void update() {
        this.mainGrid.getChildren().clear();

        this.cols = 20;
        this.rows = 20;

        this.render(TILES_RENDERING_LEVEL);
        this.render(OBJECTS_RENDERING_LEVEL);
        this.render(ACTORS_RENDERING_LEVEL);
    }

    private DoubleBinding getBindingFunction() {
        final int maxDimension = Math.max(this.cols, this.rows);

        DoubleBinding binding = mainGrid.getScene().heightProperty().divide(maxDimension);
        if (mainGrid.getScene().getWidth() < mainGrid.getScene().getHeight()) {
            binding = mainGrid.getScene().widthProperty().divide(maxDimension);
        }

        return binding;
    }

    private void render(final int renderingLevel) {
        final DoubleBinding binding = this.getBindingFunction();
        final List<Entity> toRender = this.toRender.stream().filter(e -> e.getID() == renderingLevel).toList();

        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                final int x = i;
                final int y = j;
                Optional<Entity> entity =  toRender.stream()
                        .filter(e -> e.getPos().getX() == x && e.getPos().getY() == y)
                        .findFirst();
                if (entity.isEmpty()) {
                    continue;
                }

                final ImageView image = new ImageView(new Image(entity.get().getPath() + "/Sprite.png"));
                image.fitWidthProperty().bind(binding);
                image.fitHeightProperty().bind(binding);
                this.mainGrid.add(new Pane(image), i, j);
            }
        }
    }

    /**
     * Sets up the actions done in the view's gameloop and starts it.
     */
    private void gameloop() {
        AnimationTimer gameloop = new AnimationTimer() {
            public void handle(long nanotime) {
                update();
            }
        };
        gameloop.start();
    }

    private Optional<Entity> getTileAtPosition(final List<Entity> entities, final int x, final int y) {
        return entities.stream().filter(e -> e.getID() == TILES_RENDERING_LEVEL)
                .filter(t -> t.getPos().getX().equals(x) && t.getPos().getY().equals(y))
                .findFirst();
    }

    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        this.gameloop();
    }

    public void setEntities(final List<Entity> entities) {
        this.toRender = entities;
    }

    public void setRoomSize(final Pair<Integer, Integer> roomSize) {
        this.cols = roomSize.getX() + 2;
        this.rows = roomSize.getY() + 2;
    }
}