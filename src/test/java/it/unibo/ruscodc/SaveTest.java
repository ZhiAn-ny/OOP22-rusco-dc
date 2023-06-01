package it.unibo.ruscodc;

import it.unibo.ruscodc.controller.SaveManager;
import it.unibo.ruscodc.controller.SaveManagerImpl;
import it.unibo.ruscodc.model.GameModel;
import it.unibo.ruscodc.model.GameModelImpl;
import it.unibo.ruscodc.model.actors.Actor;
import it.unibo.ruscodc.model.actors.skill.Skill;
import it.unibo.ruscodc.model.actors.skill.SkillImpl;
import it.unibo.ruscodc.model.gamecommand.GameCommand;
import it.unibo.ruscodc.utils.GameControl;
import it.unibo.ruscodc.utils.exception.ModelException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;



/**
 * This class tests the SaveManager class.
 *
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SaveTest {
    private final SaveManager manager = new SaveManagerImpl();
    private GameModel model = new GameModelImpl();
    private final String filename = "testSave";

    @Test
    void saveGame() {
        assertDoesNotThrow(() -> manager.saveGame(filename, model));
    }

    @Test
    void loadGame() {
        try {
            GameModel loaded = manager.loadGame(filename);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    private boolean areDifferent(final GameModel a, final GameModel b) {
        List<Actor> initiativeA = a.getActorByInitative();
        List<Actor> initiativeB = b.getActorByInitative();
        if (initiativeA.size() != initiativeB.size()) {
            return true;
        }
        for (int i = 0; i < initiativeA.size(); i++) {
            if (initiativeA.get(i).getPos() != initiativeB.get(i).getPos()) {
                return true;
            }
        }
        return false;
    }

    @Test
    void checkDifference() {
        List<Actor> initiative = model.getActorByInitative();
        Actor tmpActor = initiative.get(0);
        List<GameControl> steps = List.of(GameControl.MOVEUP, GameControl.MOVEDOWN, GameControl.MOVELEFT, GameControl.MOVERIGHT);
        List<GameModel> saved = new ArrayList<>(List.of(model));
        Skill skill = new SkillImpl();
        for (GameControl gc : steps) {
            GameCommand interaction = skill.getAction(gc).get();
            interaction.setActor(tmpActor);
            interaction.setRoom(model.getCurrentRoom());
            try {
                interaction.execute();
            } catch (ModelException e) {
                throw new RuntimeException(e);
            }
            try {
                manager.saveGame(filename, model);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            try {
                saved.add(manager.loadGame(filename));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        for (int i = 0; i < saved.size() - 1; i++) {
            assertTrue(areDifferent(saved.get(i), saved.get(i + 1)));
        }
        assertFalse(areDifferent(saved.get(saved.size() - 1), saved.get(0)));
        assertFalse(areDifferent(saved.get(0), saved.get(2)));
    }
}
