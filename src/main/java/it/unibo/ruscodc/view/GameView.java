package it.unibo.ruscodc.view;

import it.unibo.ruscodc.controller.GameObserverController;

public interface GameView {
    /**
     * Starts the game view creating a new window.
     */
    void startView();

    /**
     * Initializes the view.
     * @param ctrl the controller that will communicate with the view
     */
    void init(GameObserverController ctrl);

    //void uptade();

    /**
     * Prints a message on the errors stream.
     * @param err the message to visualize as error
     */
    void printError(String err);
}