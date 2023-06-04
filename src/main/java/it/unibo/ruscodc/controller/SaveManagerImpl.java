package it.unibo.ruscodc.controller;

import com.google.gson.Gson;
import it.unibo.ruscodc.model.GameModel;

import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SaveManagerImpl implements SaveManager{

    private GameModel gameModel;
    private static final Gson gson = new Gson();

    @Override
    public void saveGame(final String filename, final GameModel toSave) throws Exception {
        String json = gson.toJson(toSave);
        try (FileWriter writer = new FileWriter(filename)) {
            writer.write(json);
        }
        System.out.println("Game saved");
    }

    @Override
    public GameModel loadGame(final String filename) throws Exception {
        gameModel = gson.fromJson(
                new String(Files.readAllBytes(Paths.get(filename))),
                GameModel.class
        );
        System.out.println("Game loaded");
        return gameModel;
    }
}
