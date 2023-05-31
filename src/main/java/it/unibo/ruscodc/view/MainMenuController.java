package it.unibo.ruscodc.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainMenuController implements Initializable {
    @FXML
    private Button nuovaPartita;
    @FXML
    private Button caricaPartita;
    @FXML
    private Button impostazioni;

    @Override
    public void initialize(URL location, ResourceBundle resources){
        nuovaPartita.setOnAction(event -> {
            try {
                startNewGame(event);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        caricaPartita.setOnAction(event -> {
            try {
                loadGame(event);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        impostazioni.setOnAction(event -> settings(event));

    }

    public void startNewGame(final ActionEvent event) throws IOException {
        final Parent root = FXMLLoader.load(getClass().getResource("game-view.fxml"));
        final Scene scene = new Scene(root);
        final Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void loadGame(final ActionEvent event) throws IOException {


    }

    private void settings(ActionEvent event) {
    }


}
