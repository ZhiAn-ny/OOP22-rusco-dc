package it.unibo.ruscodc;

import it.unibo.ruscodc.controller.SaveManager;
import it.unibo.ruscodc.controller.SaveManagerImpl;
import it.unibo.ruscodc.model.GameModel;
import it.unibo.ruscodc.model.GameModelImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SaveTest {
    private final SaveManager manager = new SaveManagerImpl();
    private GameModel model = new GameModelImpl();
    private final String filename = "testSave";

    @Test
    void saveGame(){
        assertDoesNotThrow(()->manager.saveGame(filename, model));
    }

    @Test
    void loadGame(){
        try {
            GameModel loaded = manager.loadGame(filename);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }
}
