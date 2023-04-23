package it.unibo.ruscodc.controller;

import java.util.List;

import it.unibo.ruscodc.model.Entity;

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
    /**
     * 
     */
    List<Entity> getEntityToDraw();
}
