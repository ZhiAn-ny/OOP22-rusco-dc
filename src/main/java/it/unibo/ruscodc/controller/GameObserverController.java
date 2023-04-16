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
    void computeInput(String s);
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
