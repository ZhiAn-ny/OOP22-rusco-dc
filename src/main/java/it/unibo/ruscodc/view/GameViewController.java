package it.unibo.ruscodc.view;

import it.unibo.ruscodc.model.Entity;
import it.unibo.ruscodc.utils.Pair;
import javafx.animation.AnimationTimer;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;


/**
 * This class is used to print the new positions of the already existing entities in the view,
 * or to manage the new entities that must be printed.
 */
public class GameViewController implements Initializable {
    private static final int TILES_RENDERING_LEVEL = 1;
    private static final int OBJECTS_RENDERING_LEVEL = 2;
    private static final int ACTORS_RENDERING_LEVEL = 3;
    private List<Entity> toRender;


    @FXML
    private GridPane mainGrid;
    private IntegerProperty unit = new SimpleIntegerProperty();
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
    @FXML
    private StackPane infoPane;
    @FXML
    private ImageView infoImage;
    @FXML
    private Label infoTitle;
    @FXML
    private Label infoDescription;
    @FXML
    private StackPane mainPane;

    /**
     * Updates the view with the new entities to print.
     */
    public void update() {
        this.mainGrid.getChildren().clear();


        //Entity ref = toRender.stream().max(Comparator.comparingInt(e -> e.getID())).get();

        //Stream.iterate(0, i -> i < (ref.getID()+1), i -> i+1).forEach(i -> this.render(i));

        this.render(TILES_RENDERING_LEVEL);
        this.render(OBJECTS_RENDERING_LEVEL);
        this.render(ACTORS_RENDERING_LEVEL);
        this.render(4);
        this.render(5);
        this.render(6);
    }

    /**
     * Set the size of the grid.
     * @return
     */
    private DoubleBinding getBindingFunction() {
        final int maxDimension = Math.max(this.cols, this.rows);

        DoubleBinding binding = mainGrid.getScene().heightProperty().divide(maxDimension);
        if (mainGrid.getScene().getWidth() < mainGrid.getScene().getHeight()) {
            binding = mainGrid.getScene().widthProperty().divide(maxDimension);
        }

        return binding;
    }

    /**
     * Print the specified level.
     * @param renderingLevel level to render.
     */
    private void render(final int renderingLevel) {
        final DoubleBinding binding = this.getBindingFunction();
        final List<Entity> toRender = this.toRender.stream().filter(e -> e.getID() == renderingLevel).toList();

        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                final int x = j;
                final int y = i;
                Optional<Entity> entity =  toRender.stream()
                        .filter(e -> e.getPos().getX() == x && e.getPos().getY() == y)
                        .findFirst();
                if (entity.isEmpty()) {
                    continue;
                }

                final ImageView image = new ImageView(new Image(entity.get().getPath() + "/Sprite.png"));
                image.fitWidthProperty().bind(binding);
                image.fitHeightProperty().bind(binding);
                this.mainGrid.add(new Pane(image), j, i);
            }
        }
    }

    /**
     * Sets up the actions done in the view's gameloop and starts it.
     */
    private void gameloop() {
        AnimationTimer gameloop = new AnimationTimer() {
            public void handle(final long nanotime) {
                update();
            }
        };
        gameloop.start();
    }

    /**
     * Return the tile located at the specified position.
     * @param entities list of entities
     * @param x
     * @param y
     * @return
     */
    private Optional<Entity> getTileAtPosition(final List<Entity> entities, final int x, final int y) {
        return entities.stream().filter(e -> e.getID() == TILES_RENDERING_LEVEL)
                .filter(t -> t.getPos().getX().equals(x) && t.getPos().getY().equals(y))
                .findFirst();
    }

    /**
     *
     * @param location
     * The location used to resolve relative paths for the root object, or
     * {@code null} if the location is not known.
     *
     * @param resources
     * The resources used to localize the root object, or {@code null} if
     * the root object was not localized.
     */
    @FXML
    public void initialize(final URL location, final ResourceBundle resources) {
        this.gameloop();
    }

    /**
     * Set the entities that need to upload to the view.
     * @param entities list of entities to upload
     */
    public void setEntities(final List<Entity> entities) {
        this.toRender = entities;
    }

    /**
     * Set the size of the room.
     * @param roomSize size of the room
     */
    public void setRoomSize(final Pair<Integer, Integer> roomSize) {
        this.cols = roomSize.getX() + 2;
        this.rows = roomSize.getY() + 2;
    }


    /**
     *
     * @param image
     * @param title
     * @param desc
     */
    public void dummy(final Image image, final String title, final String desc) {
        infoPane.setMaxWidth(mainPane.getWidth() / 2);
        infoPane.setMaxHeight(mainPane.getHeight() / 2);
        infoPane.toFront();
        infoImage.setImage(image);
        infoImage.setFitWidth(infoPane.getWidth() * 0.05);
        infoTitle.setText(title);
        infoTitle.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
        infoDescription.setText(desc);

    }

    /**
     *
     */
    public void dedummy() {
        infoPane.toBack();
    }

    /**
     *
     */
    @FXML
    public void showInvetoryPane() {
        //errorPane.toBack();
    }

}
