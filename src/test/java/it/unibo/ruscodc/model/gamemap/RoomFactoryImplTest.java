package it.unibo.ruscodc.model.gamemap;

import it.unibo.ruscodc.utils.Direction;
import it.unibo.ruscodc.utils.Pair;

import java.security.InvalidParameterException;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoomFactoryImplTest {
    private final RoomFactory rfac = new RoomFactoryImpl();

    /**
     * Method under test: {@link RoomFactoryImpl#randomRoomNoTraps()}
     */
    @Test
    void testRandomRoom() {
        final Room room = this.rfac.randomRoomNoTraps();

        assertTrue(room.getObjectsInRoom().isEmpty());
        assertTrue(room.getMonsters().isEmpty());
        assertTrue(room.getSize().getX() >= 3);
        assertTrue(room.getSize().getY() >= 3);
        for (int i = 0; i < Direction.values().length; i++) {
            assertTrue(room.getConnectedRoom(Direction.values()[i]).isEmpty());
        }
    }

    /**
     * Method under test: {@link RoomFactoryImpl#randomRoomWithTraps()}
     */
    @Test
    void testRandomRoomWithTraps() {
        final Room room = this.rfac.randomRoomWithTraps();

        assertFalse(room.getObjectsInRoom().isEmpty());
        assertTrue(room.getMonsters().isEmpty());
        assertTrue(room.getSize().getX() >= 3);
        assertTrue(room.getSize().getY() >= 3);
        for (int i = 0; i < Direction.values().length; i++) {
            assertTrue(room.getConnectedRoom(Direction.values()[i]).isEmpty());
        }
    }

    /**
     * Method under test: {@link RoomFactoryImpl#emptySquareRoom(int)}
     */
    @Test
    void testSquareRoom() {
        final int sideLength = 3;
        final Room room = this.rfac.emptySquareRoom(sideLength);

        assertTrue(room.getObjectsInRoom().isEmpty());
        assertTrue(room.getMonsters().isEmpty());
        assertEquals(25, room.getTilesAsEntity().size());
        assertEquals(new Pair<Integer, Integer>(sideLength, sideLength), room.getSize());
        for (int i = 0; i < Direction.values().length; i++) {
            assertTrue(room.getConnectedRoom(Direction.values()[i]).isEmpty());
        }
    }

    /**
     * Method under test: {@link RoomFactoryImpl#emptySquareRoom(int)}
     */
    @Test
    void testSquareRoomSizeTooSmall() {
        assertThrows(InvalidParameterException.class, () -> this.rfac.emptySquareRoom(1));
    }

    /**
     * Method under test: {@link RoomFactoryImpl#emptyRectangleRoom(int, int)}
     */
    @Test
    void testRectangleRoomSquare() {
        final int wdt = 3;
        final int hgt = 3;
        final Room room = this.rfac.emptyRectangleRoom(wdt, hgt);

        assertTrue(room.getObjectsInRoom().isEmpty());
        assertTrue(room.getMonsters().isEmpty());
        assertEquals(25, room.getTilesAsEntity().size());
        assertEquals(new Pair<Integer, Integer>(wdt, hgt), room.getSize());
        for (int i = 0; i < Direction.values().length; i++) {
            assertTrue(room.getConnectedRoom(Direction.values()[i]).isEmpty());
        }
    }

    /**
     * Method under test: {@link RoomFactoryImpl#emptyRectangleRoom(int, int)}
     */
    @Test
    void testRectangleRoom() {
        final int wdt = 6;
        final int hgt = 3;
        final Room room = this.rfac.emptyRectangleRoom(wdt, hgt);

        assertTrue(room.getObjectsInRoom().isEmpty());
        assertTrue(room.getMonsters().isEmpty());
        assertEquals(40, room.getTilesAsEntity().size());
        assertEquals(new Pair<Integer, Integer>(wdt, hgt), room.getSize());
        for (int i = 0; i < Direction.values().length; i++) {
            assertTrue(room.getConnectedRoom(Direction.values()[i]).isEmpty());
        }
    }

    /**
     * Method under test: {@link RoomFactoryImpl#emptyRectangleRoom(int, int)}
     */
    @Test
    void testRectangleRoomSizeTooSmall() {
        assertThrows(InvalidParameterException.class, () -> this.rfac.emptyRectangleRoom(1, 1));
        assertThrows(InvalidParameterException.class, () -> this.rfac.emptyRectangleRoom(1, 3));
        assertThrows(InvalidParameterException.class, () -> this.rfac.emptyRectangleRoom(3, 1));
    }

}

