package it.unibo.ruscodc.view;

public interface GameView {
    /**
     * Starts the game view creating a new window.
     */
    void startView();

    //void setObserver();

    //void uptade();

    /**
     * Prints a message on the errors stream.
     * @param err the message to visualize as error
     */
    void printError(String err);
}