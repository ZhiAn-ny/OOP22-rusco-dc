package it.unibo.ruscodc.view;


import java.net.URL;
import java.util.Collections;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

/**
 * This class manage the game over screen.
 */
public class GameOverController implements Initializable {

    private static final String LOST = "GAME OVER";
    private static final String WIN = "GAME WIN";
    private static final int TITLE_SIZE = 30;

    @FXML
    private Button menu;

    @FXML
    private Label titleOver;

    private FXMLMainView mainView;

    /**
     * Set the title of game over.
     * @param isLost true if the game is win, false otherwise
     */
    public void setTitle(final boolean isLost) {
        final String toSet = isLost ? LOST : WIN;
        titleOver.setText(toSet);
        titleOver.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, TITLE_SIZE));
    }

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
     */
    @FXML
    public void backToMenu() {
        this.mainView.returnToMainMenu();
    }
}
