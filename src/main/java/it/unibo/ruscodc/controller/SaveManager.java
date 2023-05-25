package it.unibo.ruscodc.controller;

import it.unibo.ruscodc.model.GameModel;

public interface SaveManager {

    void saveGame(String filename, GameModel toSave) throws Exception;
    GameModel loadGame(String filename) throws Exception;
}
