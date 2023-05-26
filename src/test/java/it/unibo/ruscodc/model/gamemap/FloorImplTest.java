package it.unibo.ruscodc.model.gamemap;

import it.unibo.ruscodc.utils.Direction;
import it.unibo.ruscodc.utils.Pair;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


class FloorImplTest {
    private static final int FLOOR_NUM = 1;

    /**
     * Method under test: default or parameterless constructor of {@link FloorImpl}.
     */
    @Test
    void testConstructor() {
        final FloorImpl floor = new FloorImpl(FLOOR_NUM);
        final int entranceRoomSize = 5;

        assertNotNull(floor.getCurrentRoom());
        assertEquals(new Pair<>(entranceRoomSize, entranceRoomSize), floor.getCurrentRoom().getSize());
        assertEquals(1, floor.getNRoomExplored());
    }

    /**
     * Method under test: {@link FloorImpl#goToRoom(Direction)}.
     */
    @Test
    void testGoToRoom() {
        final Floor floor = new FloorImpl(FLOOR_NUM);
        final Room prev = floor.getCurrentRoom();

        prev.addDoor(Direction.UP);
        floor.goToRoom(Direction.UP);

        assertEquals(2, floor.getNRoomExplored());
        assertNotEquals(prev, floor.getCurrentRoom());
    }

    /**
     * Method under test: {@link FloorImpl#goToRoom(Direction)}.
     */
    @Test
    void testGoToRoomNoDoor() {
        final Floor floor = new FloorImpl(FLOOR_NUM);
        final Room prev = floor.getCurrentRoom();

        floor.goToRoom(Direction.UP);
        assertEquals(1, floor.getNRoomExplored());
        assertEquals(prev, floor.getCurrentRoom());
    }

    /**
     * Method under test: {@link FloorImpl#goToRoom(Direction)}.
     */
    @Test
    void testGoToRoomDirectionUndefined() {
        final Floor floor = new FloorImpl(FLOOR_NUM);
        final Room prev = floor.getCurrentRoom();

        floor.goToRoom(Direction.UNDEFINED);
        assertEquals(1, floor.getNRoomExplored());
        assertEquals(prev, floor.getCurrentRoom());
    }
}

