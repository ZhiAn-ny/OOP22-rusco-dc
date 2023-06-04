package it.unibo.ruscodc.view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * This class manage the game over screen.
 */
public class GameOverController implements Initializable {

    @FXML
    private Button menu;

    private FXMLMainView mainView;


    /**
     * Initialize FXMLMainView.
     * @param mainView for initialization.
     */
    public void init(final FXMLMainView mainView) {
        this.mainView = mainView;
    }


    /** {@inheritDoc} */
    @Override
    public void initialize(final URL location, final ResourceBundle resources) {

    }

    /**
     * Return to the main menu.
     * @throws IOException when the file is not found.
     */
    @FXML
    public void backToMenu() throws IOException {
        this.mainView.returnToMainMenu();
    }
}
