package it.unibo.ruscodc.view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GameOverController implements Initializable {

    @FXML
    private Button menu;
    private FXMLMainView mainView;


    public void init(final FXMLMainView mainView) {
        this.mainView = mainView;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void backToMenu() throws IOException {
        this.mainView.returnToMainMenu();
    }
}
