package it.unibo.ruscodc.model.gamemap;

import it.unibo.ruscodc.model.Entity;
import it.unibo.ruscodc.utils.Direction;
import it.unibo.ruscodc.utils.Pair;

import java.util.List;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FloorImplTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>default or parameterless constructor of {@link FloorImpl}
     *   <li>{@link FloorImpl#getCurrentRoom()}
     * </ul>
     */
    @Test
    void testConstructor() {
        final FloorImpl floor = new FloorImpl();

        assertNotNull(floor.getCurrentRoom());
        assertEquals(new Pair<Integer, Integer>(5, 5), floor.getCurrentRoom().getSize());
        assertEquals(1, floor.getNRoomExplored());
    }

    /**
     * Method under test: {@link FloorImpl#goToRoom(Direction)}
     */
    @Test
    void testGoToRoom() {
        final Floor floor = new FloorImpl();
        final Room prev = floor.getCurrentRoom();

        prev.addDoor(Direction.UP);
        floor.goToRoom(Direction.UP);

        assertEquals(2, floor.getNRoomExplored());
        assertNotEquals(prev, floor.getCurrentRoom());
    }

    /**
     * Method under test: {@link FloorImpl#goToRoom(Direction)}
     */
    @Test
    void testGoToRoomNoDoor() {
        final Floor floor = new FloorImpl();
        final Room prev = floor.getCurrentRoom();

        floor.goToRoom(Direction.UP);
        assertEquals(1, floor.getNRoomExplored());
        assertEquals(prev, floor.getCurrentRoom());
    }

    /**
     * Method under test: {@link FloorImpl#goToRoom(Direction)}
     */
    @Test
    void testGoToRoomDirectionUndefined() {
        final Floor floor = new FloorImpl();
        final Room prev = floor.getCurrentRoom();

        floor.goToRoom(Direction.UNDEFINED);
        assertEquals(1, floor.getNRoomExplored());
        assertEquals(prev, floor.getCurrentRoom());
    }
}

