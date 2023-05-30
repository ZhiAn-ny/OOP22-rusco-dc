package it.unibo.ruscodc.controller;


import it.unibo.ruscodc.utils.GameControl;

/**
 * This is an interface of controller. It computes the input of user.
 * It received the input, and he processes it according to it.
 */
public interface GameObserverController {
    /**
     * Save the game.
     */
    void save();
    /**
     * Pause the game.
     */
    void pause();
    /**
     * Return in to the game.
     */
    void resume();
    /**
     * Enable/disable the automatic save of the game.
     * If it is enable, every time the player change __ the file that save the game will override with a new save
     */
    void changeAutomaticSave();

    /**
     * Compute the input and execute an action.
     * @param input to compute
     */
    void computeInput(GameControl input);
    /**
     * To exit the game.
     */
    void quit();
    /**
     * Initialize the view with the controller.
     */
    void init();
    // /**
    //  * Start the view and the game.
    //  */
    // void start();


    void start(String[] args);
}
