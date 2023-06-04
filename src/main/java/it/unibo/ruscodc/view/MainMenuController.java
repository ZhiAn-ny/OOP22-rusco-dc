package it.unibo.ruscodc.view;

import it.unibo.ruscodc.controller.GameObserverController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainMenuController implements Initializable {


    private FXMLMainView mainView;
    @FXML
    private Button nuovaPartita;
    @FXML
    private Button caricaPartita;


    @FXML
    private VBox buttons;

    @FXML
    private TextField nameFile;

    @FXML
    public void setLayout() {
        this.buttons.spacingProperty().bind(buttons.getScene().getWindow().heightProperty().divide(20));

    }

    public void init(final FXMLMainView mainView){
        this.mainView = mainView;
    }



    @Override
    public void initialize(URL location, ResourceBundle resources){
    }

    @FXML
    public void startNewGame(final ActionEvent event) throws Exception {
        this.mainView.startNewGame(nameFile.getText());

    }

    @FXML
    public void loadGame(final ActionEvent event) throws IOException {


    }


}
