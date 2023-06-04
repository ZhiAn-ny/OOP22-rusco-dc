package it.unibo.ruscodc.view;

import java.net.URL;
import java.util.Collections;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

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
        this.mainView = Collections.nCopies(1, mainView).get(0);
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
    public void backToMenu() {
        this.mainView.returnToMainMenu();
    }
}
