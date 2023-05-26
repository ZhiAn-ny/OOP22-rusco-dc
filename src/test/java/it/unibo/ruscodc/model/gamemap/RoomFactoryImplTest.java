package it.unibo.ruscodc.model.gamemap;

import it.unibo.ruscodc.utils.Direction;
import it.unibo.ruscodc.utils.Pair;

import java.security.InvalidParameterException;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;


class RoomFactoryImplTest {
    private final RoomFactory roomFactory = new RoomFactoryImpl();

    /**
     * Method under test: {@link RoomFactoryImpl#randomRoomNoTraps()}.
     */
    @Test
    void testRandomRoom() {
        final Room room = this.roomFactory.randomRoomNoTraps();

        assertTrue(room.getObjectsInRoom().isEmpty());
        assertTrue(room.getMonsters().isEmpty());
        assertTrue(room.getSize().getX() >= 3);
        assertTrue(room.getSize().getY() >= 3);
        for (int i = 0; i < Direction.values().length; i++) {
            assertTrue(room.getConnectedRoom(Direction.values()[i]).isEmpty());
        }
    }

    /**
     * Method under test: {@link RoomFactoryImpl#randomRoomWithTraps()}.
     */
    @Test
    void testRandomRoomWithTraps() {
        final Room room = this.roomFactory.randomRoomWithTraps();

        assertFalse(room.getObjectsInRoom().isEmpty());
        assertTrue(room.getMonsters().isEmpty());
        assertTrue(room.getSize().getX() >= 3);
        assertTrue(room.getSize().getY() >= 3);
        for (int i = 0; i < Direction.values().length; i++) {
            assertTrue(room.getConnectedRoom(Direction.values()[i]).isEmpty());
        }
    }

    /**
     * Method under test: {@link RoomFactoryImpl#emptySquareRoom(int)}.
     */
    @Test
    void testSquareRoom() {
        final int sideLength = 3;
        final Room room = this.roomFactory.emptySquareRoom(sideLength);
        final int expectedTilesNum = (int) Math.pow(sideLength + 2, 2);

        assertTrue(room.getObjectsInRoom().isEmpty());
        assertTrue(room.getMonsters().isEmpty());
        assertEquals(expectedTilesNum, room.getTilesAsEntity().size());
        assertEquals(new Pair<>(sideLength, sideLength), room.getSize());
        for (int i = 0; i < Direction.values().length; i++) {
            assertTrue(room.getConnectedRoom(Direction.values()[i]).isEmpty());
        }
    }

    /**
     * Method under test: {@link RoomFactoryImpl#emptySquareRoom(int)}.
     */
    @Test
    void testSquareRoomSizeTooSmall() {
        assertThrows(InvalidParameterException.class, () -> this.roomFactory.emptySquareRoom(1));
    }

    /**
     * Method under test: {@link RoomFactoryImpl#emptyRectangleRoom(int, int)}.
     */
    @Test
    void testRectangleRoomSquare() {
        final int wdt = 3;
        final int hgt = 3;
        final Room room = this.roomFactory.emptyRectangleRoom(wdt, hgt);
        final int expectedTilesNum = (wdt + 2) * (hgt + 2);

        assertTrue(room.getObjectsInRoom().isEmpty());
        assertTrue(room.getMonsters().isEmpty());
        assertEquals(expectedTilesNum, room.getTilesAsEntity().size());
        assertEquals(new Pair<>(wdt, hgt), room.getSize());
        for (int i = 0; i < Direction.values().length; i++) {
            assertTrue(room.getConnectedRoom(Direction.values()[i]).isEmpty());
        }
    }

    /**
     * Method under test: {@link RoomFactoryImpl#emptyRectangleRoom(int, int)}.
     */
    @Test
    void testRectangleRoom() {
        final int wdt = 6;
        final int hgt = 3;
        final Room room = this.roomFactory.emptyRectangleRoom(wdt, hgt);
        final int expectedTilesNum = (wdt + 2) * (hgt + 2);

        assertTrue(room.getObjectsInRoom().isEmpty());
        assertTrue(room.getMonsters().isEmpty());
        assertEquals(expectedTilesNum, room.getTilesAsEntity().size());
        assertEquals(new Pair<>(wdt, hgt), room.getSize());
        for (int i = 0; i < Direction.values().length; i++) {
            assertTrue(room.getConnectedRoom(Direction.values()[i]).isEmpty());
        }
    }

    /**
     * Method under test: {@link RoomFactoryImpl#emptyRectangleRoom(int, int)}.
     */
    @Test
    void testRectangleRoomSizeTooSmall() {
        assertThrows(InvalidParameterException.class, () -> this.roomFactory.emptyRectangleRoom(1, 1));
        assertThrows(InvalidParameterException.class, () -> this.roomFactory.emptyRectangleRoom(1, 3));
        assertThrows(InvalidParameterException.class, () -> this.roomFactory.emptyRectangleRoom(3, 1));
    }

}

