package it.unibo.ruscodc.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.ResourceBundle;

/**
 * Thiss class manage the main menu.
 * Main menu is a first screen of the game.
 */
public class MainMenuController implements Initializable {

    private FXMLMainView mainView;
    @FXML
    private Button nuovaPartita;

    private static final int SPACE = 20;

    @FXML
    private VBox buttons;


    /**
     * Set the layout of the buttons.
     */
    @FXML
    public void setLayout() {
        this.buttons.spacingProperty().bind(buttons.getScene().getWindow().heightProperty().divide(SPACE));

    }

    /**
     * Initialize the mainview.
     * @param mainView for initialization
     */
    public void init(final FXMLMainView mainView) {
        this.mainView = Collections.nCopies(1, mainView).get(0);
    }


    /** {@inheritDoc} */
    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
    }

    /**
     * Start new game.
     * @param event of the button.
     * @throws Exception when the fxml file is not found.
     */
    @FXML
    public void startNewGame(final ActionEvent event) {
        this.mainView.startNewGame("");
    }

    /**
     * Load game by the name of the file.
     * @param event of the button.
     * @throws IOException when the file is not found.
     */
    @FXML
    public void loadGame(final ActionEvent event) throws IOException {
    }


}
