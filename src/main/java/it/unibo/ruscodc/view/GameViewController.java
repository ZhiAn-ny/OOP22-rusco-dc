package it.unibo.ruscodc.view;

import it.unibo.ruscodc.model.Entity;
import it.unibo.ruscodc.model.outputinfo.Portrait;
import it.unibo.ruscodc.utils.Pair;
import javafx.animation.AnimationTimer;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.binding.NumberBinding;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

import java.net.URL;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


/**
 * This class is used to print the new positions of the already existing entities in the view,
 * or to manage the new entities that must be printed.
 */
public class GameViewController implements Initializable {
    // private static final int TILES_RENDERING_LEVEL = 1;
    // private static final int OBJECTS_RENDERING_LEVEL = 2;
    // private static final int ACTORS_RENDERING_LEVEL = 3;
    // private List<Entity> toRender;

    private boolean isToUpdate = false;
    private int lastRenderingLevel;
    private final Map<Integer, List<Entity>> renderedOLD = new HashMap<>();
    private final Map<Integer, List<FXMLDrawable>> rendered = new HashMap<>();




    private IntegerProperty unit = new SimpleIntegerProperty();
    private int rows;
    private int cols;
    @FXML
    private GridPane mainGrid;
    @FXML
    private ProgressBar hp;
    @FXML
    private ProgressBar ap;
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

    private NumberBinding bindings;


    /**
     * Updates the view with the new entities to print.
     */
    public void update() {

        if (!isToUpdate){
            return;
        }
        this.mainGrid.getChildren().clear();
        //final NumberBinding binding = this.getBindingFunction();
        //this.mainGrid.getChildren().clear();
        isToUpdate = false;
        
        //mainPane.clear
        //rendered.removeAll( (depth, l) -> depth>=lastRenderingLevel);
        //IntStream.rangeClosed(lastRenderingLevel+1, 10).forEach(i -> renderedOLD.remove(i));
        IntStream.rangeClosed(lastRenderingLevel+1, 10).forEach(i -> rendered.remove(i));
        // renderedOLD.entrySet()
        //     .stream()
        //     .sorted(Comparator.comparingInt(level -> level.getKey()))
        //     //.dropWhile(level -> level.getKey() < startRendering)
        //     .peek(ll -> System.out.println(ll.getKey()))
        //     //.peek(level -> level.getValue())
        //     .forEach(level -> {
        //         level.getValue().forEach(e -> {
        //             final ImageView image = new ImageView(new Image(e.getPath() + "/Sprite.png"));
        //             image.fitWidthProperty().bind(binding);
        //             image.fitHeightProperty().bind(binding);
        //             final Pair<Integer, Integer> pos = e.getPos();
        //             this.mainGrid.add(new Pane(image), pos.getX(), pos.getY());
        //         });
        //     });
        rendered.entrySet()
            .stream()
            .sorted(Comparator.comparingInt(level -> level.getKey()))
            //.dropWhile(level -> level.getKey() < startRendering)
            .peek(ll -> System.out.println(ll.getKey()))
            //.peek(level -> level.getValue())
            .forEach(level -> {
                level.getValue().forEach(e -> {
                    final ImageView image = e.getRes();
                    image.fitWidthProperty().bind(this.bindings);
                    image.setPreserveRatio(true);
                    final Pair<Integer, Integer> pos = e.getOnScreenPosition();
                    this.mainGrid.add(new Pane(image), pos.getX(), pos.getY());
                });
            });

        //Entity ref = toRender.stream().max(Comparator.comparingInt(e -> e.getID())).get();

        //Stream.iterate(0, i -> i < (ref.getID()+1), i -> i+1).forEach(i -> this.render(i));

        // this.render(TILES_RENDERING_LEVEL);
        // this.render(OBJECTS_RENDERING_LEVEL);
        // this.render(ACTORS_RENDERING_LEVEL);
        // this.render(4);
        // this.render(5);
        // this.render(6);
    }

    /**
     * Set the size of the grid.
     * @return
     */
    private NumberBinding getBindingFunction() {
        final int maxDimension = Math.max(this.cols, this.rows);
        final int minDimension = Math.min(this.cols, this.rows);

//        DoubleBinding binding = mainGrid.getScene().heightProperty().divide(maxDimension);
//        if (mainGrid.getScene().getWidth() < mainGrid.getScene().getHeight()) {
//            binding = mainGrid.getScene().widthProperty().divide(maxDimension + (cols+2));
//        }

        NumberBinding binding;

        if (this.cols < this.rows) {
            binding = Bindings.min(mainGrid.getScene().widthProperty(), mainGrid.getScene().heightProperty()).divide(maxDimension);
        } else {
            binding = Bindings.min(mainGrid.getScene().widthProperty(), mainGrid.getScene().heightProperty()).divide(minDimension);
        }

        System.out.println(maxDimension + "****************************************************");
        System.out.println("cols: " + this.cols + " rows: " + this.rows);
        return binding;
    }


    /**
     * Print the specified level.
     * @param renderingLevel level to render.
     */
    private void render(final int renderingLevel) {
        // final DoubleBinding binding = this.getBindingFunction();
        // final List<Entity> toRender = this.toRender.stream().filter(e -> e.getID() == renderingLevel).toList();

        // for (int i = 0; i < this.rows; i++) {
        //     for (int j = 0; j < this.cols; j++) {
        //         final int x = j;
        //         final int y = i;
        //         Optional<Entity> entity =  toRender.stream()
        //                 .filter(e -> e.getPos().getX() == x && e.getPos().getY() == y)
        //                 .findFirst();
        //         if (entity.isEmpty()) {
        //             continue;
        //         }

        //         final ImageView image = new ImageView(new Image(entity.get().getPath() + "/Sprite.png"));
        //         image.fitWidthProperty().bind(binding);
        //         image.fitHeightProperty().bind(binding);
        //         this.mainGrid.add(new Pane(image), j, i);
        //     }
        // }
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

    // /**
    //  * Return the tile located at the specified position.
    //  * @param entities list of entities
    //  * @param x
    //  * @param y
    //  * @return
    //  */
    // private Optional<Entity> getTileAtPosition(final List<Entity> entities, final int x, final int y) {
    //     return entities.stream().filter(e -> e.getID() == TILES_RENDERING_LEVEL)
    //             .filter(t -> t.getPos().getX().equals(x) && t.getPos().getY().equals(y))
    //             .findFirst();
    // }

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

    // /**
    //  * Set the entities that need to upload to the view.
    //  * @param entities list of entities to upload
    //  */
    // public void setEntities(final List<Entity> entities) {
    //     this.toRender = entities;
    // }

     /**
     * Set the entities that need to upload to the view.
     * @param entities list of entities to upload
     */
    public void updateEntities(final List<Entity> entities) {
        // final Map<Integer, List<Entity>> toUpdateOLD = 
        //     entities.stream()
        //     .collect(Collectors.toMap(
        //         e -> e.getID(), 
        //         e -> new LinkedList<>(List.of(e)), 
        //         (l1, l2) -> {
        //             l1.addAll(l2);
        //             return new LinkedList<>(l1);
        //             },
        //         () -> new HashMap<>()));

        final Map<Integer, List<FXMLDrawable>> toUpdate = 
            entities.stream()
            .collect(Collectors.toMap(
                e -> e.getID(), 
                e -> new LinkedList<>(
                    List.of(
                        new FXMLDrawable(
                            new ImageView(new Image(e.getPath() + "/Sprite.png")), 
                            e.getPos()))), 
                (l1, l2) -> {
                    l1.addAll(l2);
                    return new LinkedList<>(l1);
                    },
                () -> new HashMap<>()));

        final Optional<Integer> minDepth = toUpdate.keySet().stream().max(Comparator.naturalOrder());
        if (minDepth.isEmpty()){
            return;
        }
        
        // renderedOLD.putAll(toUpdateOLD);
        rendered.putAll(toUpdate);
        //entities.stream().min(Comparator.comparingInt(e -> e.getID())).get().getID();
        lastRenderingLevel = minDepth.get();
        isToUpdate = true;
        //update();
        // this.printedEntity.removeIf(e -> e.getID() >= minDepth);
        // this.printedEntity.addAll(entities);
        // this.rendered = entities;
    }

    /**
     * Set the size of the room.
     * @param roomSize size of the room
     */
    public void setRoomSize(final Pair<Integer, Integer> roomSize) {
        this.cols = roomSize.getX() + 2;
        this.rows = roomSize.getY() + 2;
        this.bindings = getBindingFunction();
    }


    /**
     *
     * @param image
     * @param title
     * @param desc
     */
    public void printInfoPalyodToScreen(final Image image, final String title, final String desc) {
        //infoPane.setMaxWidth(mainPane.getWidth() / 2);
        //infoPane.setMaxHeight(mainPane.getHeight() / 2);
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
    public void clearInfoPalyodToScreen() {
        infoPane.toBack();
    }

    public void uploadPortraitToScreen(final Portrait toPrint) {
        hp.setProgress(toPrint.getHPcoeff());
        ap.setProgress(toPrint.getAPcoeff());
    }

    /**
     *
     */
    @FXML
    public void showInvetoryPane() {
        //errorPane.toBack();
    }

}
