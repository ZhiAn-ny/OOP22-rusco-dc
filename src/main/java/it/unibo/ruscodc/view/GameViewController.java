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
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

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

    private boolean isToUpdate = false;

    private int lastRenderingLevelGame;
    private final Map<Integer, List<FXMLDrawable>> renderedGame = new HashMap<>();

    private boolean isShowingInv = false;

    private int lastRenderingLevelInv;
    private final Map<Integer, List<FXMLDrawable>> renderedInv = new HashMap<>();
    



    private IntegerProperty unit = new SimpleIntegerProperty();
    private int rows;
    private int cols;
    @FXML
    private GridPane mainGrid;
    @FXML
    private StackPane invPane;
    @FXML
    private GridPane inventoryGrid;
    @FXML
    private Label heroStat;

    @FXML
    private ProgressBar hp;
    @FXML
    private ProgressBar ap;

    @FXML
<<<<<<< HEAD
=======
    private Button inventory;
    @FXML
    private Button menu;

    @FXML
>>>>>>> develop
    private StackPane infoPane;
    @FXML
    private ImageView infoImage;
    @FXML
    private Label infoTitle;
    @FXML
    private Label infoDescription;

    @FXML
    private StackPane mainPane;

<<<<<<< HEAD
    private NumberBinding bindings;


    /**
     * Updates the view with the new entities to print.
     */
    public void update() {
=======
    
>>>>>>> develop

    private void updateGame() {
        if (!isToUpdate){
            return;
        }
        this.mainGrid.getChildren().clear();
<<<<<<< HEAD
        //final NumberBinding binding = this.getBindingFunction();
        //this.mainGrid.getChildren().clear();
=======
        final DoubleBinding binding = this.getBindingFunction();
>>>>>>> develop
        isToUpdate = false;
        
        IntStream.rangeClosed(lastRenderingLevelGame + 1, 10).forEach(i -> renderedGame.remove(i));
        
        renderedGame.entrySet()
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
    }

    private void updateInv() {
        if (!isToUpdate){
            return;
        }
        this.inventoryGrid.getChildren().clear();
        final DoubleBinding binding = this.getBindingFunction();
        isToUpdate = false;
        
        IntStream.rangeClosed(lastRenderingLevelGame + 1, 10).forEach(i -> renderedInv.remove(i));
        
        renderedInv.entrySet()
            .stream()
            .sorted(Comparator.comparingInt(level -> level.getKey()))
            .peek(ll -> System.out.println(ll.getKey()))
            .forEach(level -> {
                level.getValue().forEach(e -> {
                    final ImageView image = e.getRes();
                    image.fitWidthProperty().bind(binding);
                    image.fitHeightProperty().bind(binding);
                    final Pair<Integer, Integer> pos = e.getOnScreenPosition();
                    this.inventoryGrid.add(new Pane(image), pos.getX(), pos.getY());
                });
            });
    }

    /**
     * Updates the view with the new entities to print.
     */
    public void update() {
        if (isShowingInv) {
            updateInv();
        } else {
            updateGame();
        }

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
    public void updateEntities(final List<Entity> entities) {
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
        if (minDepth.isEmpty()) {
            return;
        }

        if (isShowingInv){
            renderedInv.putAll(toUpdate);
            lastRenderingLevelInv = minDepth.get();
        } else {
            renderedGame.putAll(toUpdate);
            lastRenderingLevelGame = minDepth.get();
        }

        isToUpdate = true;
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

    public void uploadStatsToScreen(final String toPrint) {
        this.heroStat.setText(toPrint);
    }

    public void openInv() {
        this.isShowingInv = true;
        this.invPane.toFront();
    }

    public void closeInv() {
        this.isShowingInv = false;
        this.invPane.toBack();
    }

}
