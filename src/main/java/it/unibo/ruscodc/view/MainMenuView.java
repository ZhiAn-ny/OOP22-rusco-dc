package it.unibo.ruscodc.view;

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
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class MainMenuView implements Initializable {

    @FXML
    private GridPane mainGrid;
    @FXML
    private ProgressBar hp;
    @FXML
    private ProgressBar ap;
    @FXML
    private Button inventory;
    @FXML
    private Button menu;

    IntegerProperty unit = new SimpleIntegerProperty();
    private int rows = 8;
    private int cols = 8;
    private Stage stage;
    private GameObserverController gameController;
    public MainMenuView(Stage stage){
        this.stage = stage;
        this.gameController = (GameControllerImpl)stage.getUserData();
    }

    public void addioFichi(){
        System.out.println("addioFichi");
    }
    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        for (int i=0; i < rows; i++){
            for (int j=0; j<cols; j++){
                var pane = new ImageView(new Image("file:src/main/resources/it/unibo/ruscodc/map_res/FloorTile/FloorTile.jpg"));
                this.mainGrid.sceneProperty().addListener((observable, oldScene, newScene) -> {
                    if (newScene != null){
                        unit.bind(Bindings.min(mainGrid.getScene().widthProperty(), mainGrid.getScene().heightProperty()).divide(100));
                    }
                    if (!pane.fitWidthProperty().isBound() && newScene != null){
                        pane.fitWidthProperty().bind(Bindings.min(mainGrid.getScene().widthProperty(), mainGrid.getScene().heightProperty()).divide(cols));
                        pane.fitHeightProperty().bind(Bindings.min(mainGrid.getScene().widthProperty(), mainGrid.getScene().heightProperty()).divide(rows));
                    }
                });
                this.mainGrid.add(new Pane(pane), i, j);

            }
        }
    }


    public void setGrid(Pair<Integer, Integer> roomSize) {

    }
}
