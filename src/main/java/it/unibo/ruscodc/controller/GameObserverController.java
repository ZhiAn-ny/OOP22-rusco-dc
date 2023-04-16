package it.unibo.ruscodc.controller;

public interface GameObserverController {
    /**
     * 
     */
    void save();
    /**
     * 
     */
    void pause();
    /**
     * 
     */
    void resume();
    /**
     * 
     */
    void changeAutomaticSave();
    /**
     * 
     */
    void computeInput(int input);
    /**
     * 
     */
    void quit();
    /**
     * 
     */
    void init();
    /**
     * 
     */
    void start();

    /**
     *
     */
    void launch();
}
