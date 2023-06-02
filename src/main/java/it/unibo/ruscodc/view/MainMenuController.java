package it.unibo.ruscodc.view;

import it.unibo.ruscodc.controller.GameObserverController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainMenuController implements Initializable {

    private GameObserverController gameController;
    private FXMLMainView mainView;
    @FXML
    private Button nuovaPartita;
    @FXML
    private Button caricaPartita;
    @FXML
    private Button impostazioni;

    @FXML
    private VBox buttons;

    @FXML
    private Label title;

    @FXML
    private void setTitle() {
        title.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);

    }
    @FXML
    private void setButtons() {
    }

    public void init(final FXMLMainView mainView){
        this.mainView = mainView;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources){

    }

    @FXML
    public void startNewGame(final ActionEvent event) throws Exception {
        this.mainView.startNewGame();

    }

    @FXML
    public void loadGame(final ActionEvent event) throws IOException {


    }

    @FXML
    public void settings(final ActionEvent event) {
    }


}
