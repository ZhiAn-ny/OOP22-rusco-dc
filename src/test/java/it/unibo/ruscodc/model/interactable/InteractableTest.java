package it.unibo.ruscodc.model.interactable;

import it.unibo.ruscodc.model.actors.hero.HeroImpl;
import it.unibo.ruscodc.model.actors.hero.Hero;
import it.unibo.ruscodc.model.actors.monster.drop.DropFactory;
import it.unibo.ruscodc.model.actors.monster.drop.DropFactoryImpl;
import it.unibo.ruscodc.model.actors.monster.drop.DropManager;
import it.unibo.ruscodc.model.gamecommand.GameCommand;
import it.unibo.ruscodc.utils.Direction;
import it.unibo.ruscodc.utils.Pair;
import it.unibo.ruscodc.utils.exception.ModelException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(Lifecycle.PER_CLASS)
class InteractableTest {

    private static final DropFactory dropFactory = new DropFactoryImpl();
    private static final DropManager dropManager = dropFactory.createDropForRoom(new Pair<>(100, 100), 4);
    private int bounds;

    @BeforeAll
    void init() {
        bounds = dropManager.generateConsumableDrop().size();
    }

    /**
     * Method under test: {@link Interactable#interact()}
     */
    @Test
    void chestInteract() {
        final Interactable chest = new Chest(new HashSet<>(dropManager.generateConsumableDrop()), new Pair<>(1,1));
        final Hero hero = new HeroImpl("Rusco", new Pair<>(2,2), null, null);
        final GameCommand command = chest.interact();
        command.setActor(hero);
        try {
            command.execute();
        } catch (ModelException e) {
            throw new RuntimeException(e);
        }
        assertEquals(hero.getInventory().getAllItems().size(), bounds);

    }

    /**
     * Method under test: {@link Door#isTransitable()}
     */
    @Test
    void isTransitableDoor() {
        final Interactable door = new Door(new Pair<>(0, 5), Direction.UP);
        assertTrue(door.isTransitable());
    }

    /**
     * Method under test: {@link Stair#isTransitable()}
     */
    @Test
    void isTransitableStair() {
        final Interactable stair = new Stair(new Pair<>(0, 2));
        assertTrue(stair.isTransitable());
    }

    /**
     * Method under test: {@link Drop#isTransitable()}
     */
    @Test
    void isTransitableDrop() {
        final Interactable drop = new Drop(new HashSet<>(dropManager.generateConsumableDrop()), new Pair<>(8,5));
        assertFalse(drop.isTransitable());
    }

    /**
     * Method under test: {@link Chest#isTransitable()}
     */
    @Test
    void isTransitableChest() {
        final Interactable chest = new Chest(new HashSet<>(dropManager.generateConsumableDrop()), new Pair<>(4,6));
        assertFalse(chest.isTransitable());
    }



}
