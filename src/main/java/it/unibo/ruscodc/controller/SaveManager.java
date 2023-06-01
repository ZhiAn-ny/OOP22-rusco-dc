package it.unibo.ruscodc.controller;

import it.unibo.ruscodc.model.GameModel;

/**
 * This interface is used to save and load game.
 *
 */
public interface SaveManager {

    void saveGame(String filename, GameModel toSave) throws Exception;
    GameModel loadGame(String filename) throws Exception;
}
