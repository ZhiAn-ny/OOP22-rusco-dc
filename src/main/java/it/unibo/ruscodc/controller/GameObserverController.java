package it.unibo.ruscodc.controller;

import java.util.List;

import it.unibo.ruscodc.model.Entity;
import it.unibo.ruscodc.utils.GameControl;

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
    void computeInput(GameControl input);
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
