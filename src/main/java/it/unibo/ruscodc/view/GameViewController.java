package it.unibo.ruscodc.view;

import it.unibo.ruscodc.model.Entity;
import it.unibo.ruscodc.model.outputinfo.Portrait;
import it.unibo.ruscodc.utils.Pair;
import javafx.animation.AnimationTimer;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.NumberBinding;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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

import java.io.IOException;
import java.net.URL;
import java.util.Collections;
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

    private static final int FONT_SIZE = 20;
    private static final double SCALE1 = 0.05;


    private boolean isToUpdate;

    private int lastRenderingLevelGame;
    private final Map<Integer, List<DrawableImpl<ImageView>>> renderedGame = new HashMap<>();

    private boolean isShowingInv;

    private int lastRenderingLevelInv;
    private final Map<Integer, List<DrawableImpl<ImageView>>> renderedInv = new HashMap<>();

    private int upperBound;

    private int rows;
    private int cols;
    private FXMLMainView mainView;
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
    private StackPane infoPane;
    @FXML
    private ImageView infoImage;
    @FXML
    private Label infoTitle;
    @FXML
    private Label infoDescription;

    @FXML
    private StackPane mainPane;


    private static NumberBinding bindings;

    /**
     * Common implementation of the first two fuction below.
     * @param toUpload where print the info
     * @param infos info to render
     * @param lastRendering where start clean rendered info
     */
    private void updateDefinedGrid(
            final GridPane toUpload,
            final Map<Integer, List<DrawableImpl<ImageView>>> infos,
            final int lastRendering) {
        if (!isToUpdate) {
            return;
        }
        toUpload.getChildren().clear();

        isToUpdate = false;

        IntStream.rangeClosed(lastRendering + 1, upperBound).forEach(i -> infos.remove(i));

        infos.entrySet()
                .stream()
                .sorted(Comparator.comparingInt(level -> level.getKey()))
                .peek(ll -> System.out.println(ll.getKey()))
                .forEach(level -> {
                    level.getValue().forEach(e -> {
                        final ImageView image = e.getRes();
                        image.fitWidthProperty().bind(bindings);
                        image.setPreserveRatio(true);
                        final Pair<Integer, Integer> pos = e.getOnScreenPosition();
                        toUpload.add(new Pane(image), pos.getX(), pos.getY());
                    });
                });
    }

    /**
     * Updates the view with the new entities to print.
     */
    private void updateGame() {
        updateDefinedGrid(mainGrid, renderedGame, lastRenderingLevelGame);
    }

    private void updateInv() {
        updateDefinedGrid(inventoryGrid, renderedInv, lastRenderingLevelInv);
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
     * Help define the effective size of the grid, in funcion of the dimension of the schreen.
     * @return the "binding" funcion.
     */
    private NumberBinding getBindingFunction() {
        final int maxDimension = Math.max(this.cols, this.rows);
        return Bindings.min(mainGrid.getScene().widthProperty(), mainGrid.getScene().heightProperty()).divide(maxDimension);
    }

    /**
     * Sets up the actions done in the view's gameloop and starts it.
     */
    private void gameloop() {
        final AnimationTimer gameloop = new AnimationTimer() {
            @Override
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
    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        this.invPane.toBack();
        this.gameloop();
    }

    /**
     * Set the entities that need to upload to the view.
     * @param entities list of entities to upload
     */
    public void updateEntities(final List<Entity> entities) {
        final Map<Integer, List<DrawableImpl<ImageView>>> toUpdate =
                entities.stream()
                        .collect(Collectors.toMap(
                                e -> e.getID(),
                                e -> new LinkedList<>(
                                        List.of(
                                                new DrawableImpl<>(
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

        if (isShowingInv) {
            renderedInv.putAll(toUpdate);
            lastRenderingLevelInv = minDepth.get();
        } else {
            renderedGame.putAll(toUpdate);
            lastRenderingLevelGame = minDepth.get();
        }

        if (upperBound < minDepth.get()) {
            upperBound = minDepth.get();
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
        bindings = getBindingFunction();
    }


    /**
     * Show info of a situation to the view.
     * @param image the image that help to understand the situation
     * @param title the title of info
     * @param desc its description
     */
    public void printInfoPalyodToScreen(final Image image, final String title, final String desc) {
        infoPane.toFront();
        infoImage.setImage(image);
        infoImage.setFitWidth(infoPane.getWidth() * SCALE1);
        infoTitle.setText(title);
        infoTitle.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, FONT_SIZE));
        infoDescription.setText(desc);
    }

    /**
     * Clear the info pain, so player can continue to play.
     */
    public void clearInfoPalyodToScreen() {
        infoPane.toBack();
    }

    /**
     * Upload view with some of the stat of actual hero.
     * @param toPrint where take infos
     */
    public void uploadPortraitToScreen(final Portrait toPrint) {
        hp.setProgress(toPrint.getHPcoeff());
        ap.setProgress(toPrint.getAPcoeff());
    }

    /**
     * When the inventory is showed, upload these infos.
     * @param toPrint where take infos
     */
    public void uploadStatsToScreen(final String toPrint) {
        this.heroStat.setText(toPrint);
    }

    /**
     * Show the inventory.
     */
    public void openInv() {
        this.isShowingInv = true;
        this.invPane.toFront();
    }

    /**
     * Close the inventory.
     */
    public void closeInv() {
        this.isShowingInv = false;
        this.invPane.toBack();
    }

    /**
     * Init this class, that need to know who is its view-controller.
     * @param view the view-controller
     */
    public void init(final FXMLMainView view) {
        this.mainView = Collections.nCopies(1, view).get(0);
    }

    /**
     * Redirect the input of "Save Game" to the controller of fxml file.
     */
    @FXML
    public void saveActGame() {
        this.mainView.saveGame();
    }

    /**
     * Return to the MainView.
     * @throws IOException if occur an error during the loading of MainView fxml.
     */
    @FXML
    public void exit() throws IOException {
        this.mainView.returnToMainMenu();
    }

    /**
     * Change the automatic save setting (with an on-off logic: if it was on, now it will be off, and otherwise).
     */
    @FXML
    public void changeAutomaticSave() {
        this.mainView.changeAutomaticSave();
    }

}
