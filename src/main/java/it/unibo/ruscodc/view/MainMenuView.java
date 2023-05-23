package it.unibo.ruscodc.view;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.beans.binding.Bindings;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainMenuView implements Initializable {

    @FXML
    private GridPane mainGrid;
    IntegerProperty unit = new SimpleIntegerProperty();
    private static final int rows = 8;
    private static final int cols = 8;

    public void loadView(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(FXMLView.class.getResource("main-menu-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setMinWidth(Screen.getPrimary().getVisualBounds().getWidth()*0.5);
        stage.setMinHeight(Screen.getPrimary().getVisualBounds().getHeight()*0.5);
        stage.setFullScreen(true);
        stage.setTitle("Rusco DC");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        for (int i=0; i < rows; i++){
            for (int j=0; j<cols; j++){
                var pane = new ImageView(new Image("file:src/main/resources/it/unibo/ruscodc/map_res/FloorTile"));
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
}
