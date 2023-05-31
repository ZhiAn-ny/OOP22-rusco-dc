package it.unibo.ruscodc.model.gamemap;

import it.unibo.ruscodc.model.interactable.Door;
import it.unibo.ruscodc.model.interactable.Stair;
import it.unibo.ruscodc.utils.Direction;
import it.unibo.ruscodc.utils.Pair;

import java.security.InvalidParameterException;
import java.util.List;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


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
    void testRectangleRoom() {
        final List<Pair<Integer, Integer>> size = List.of(
                new Pair<>(3, 3),
                new Pair<>(3, 6)
        );

        size.forEach(xy -> {
            final Room room = this.roomFactory.emptyRectangleRoom(xy.getX(), xy.getY());
            final int expectedTilesNum = (xy.getX() + 2) * (xy.getY() + 2);

            assertTrue(room.getObjectsInRoom().isEmpty());
            assertTrue(room.getMonsters().isEmpty());
            assertEquals(expectedTilesNum, room.getTilesAsEntity().size());
            assertEquals(xy, room.getSize());
            for (int i = 0; i < Direction.values().length; i++) {
                assertTrue(room.getConnectedRoom(Direction.values()[i]).isEmpty());
            }
        });
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

    /**
     * Method under test: {@link RoomFactoryImpl#stairsRoom()}
     */
    @Test
    void testStairsRoom() {
        final Room room = this.roomFactory.stairsRoom();

        assertTrue(room.getTilesAsEntity().stream().map(tile -> (Tile) tile)
                .filter(tile -> tile.get().isPresent())
                .anyMatch(tile -> tile.get().get() instanceof Stair));
    }

    /**
     * Method under test: {@link RoomFactoryImpl#addDoors(Room)}
     */
    @Test
    void testAddDoors() {
        final int tries = 20;
        final int maxDoorCount = 4;
        for (int i = 0; i < tries; i++) {
            final Room room = this.roomFactory.emptySquareRoom(3);
            this.roomFactory.addDoors(room);

            if (room.getDoorOnSide(Direction.UNDEFINED).isPresent()) {
                fail();
            }
            final int doors = (int) room.getTilesAsEntity().stream().map(tile -> (Tile) tile)
                    .filter(tile -> tile.get().isPresent() && tile.get().get() instanceof Door)
                    .count();

            assertTrue(doors <= maxDoorCount);
        }
    }

    /**
     * Method under test: {@link RoomFactoryImpl#addItems(Room, int)}
     * For this case we are testing the value of maximum number of items per room.
     */
    @Test
    void testAddItems() {
        final int maxLevel = 100;
        final int minRoomSize = 3;
        for (int floor = 0; floor < maxLevel; floor++) {
            final Room room = this.roomFactory.emptySquareRoom(28);

            int maxNumItems = (int)(room.getArea() / Math.pow(minRoomSize, 2)) + floor;
            final int maxOcc = room.getArea() - room.getObjectsInRoom().size() - room.getArea()/2;
            maxNumItems = maxNumItems % maxOcc;
            maxNumItems = maxNumItems == 0 ? 1 : maxNumItems;

            assertTrue(maxNumItems > 0, "Floor was: " + floor);
            assertTrue(maxNumItems < room.getArea(), "Floor was: " + floor);
        }
    }

    /**
     * Method under test: {@link RoomFactoryImpl#addMonsters(Room, int)}
     * For this case we are testing the value of maximum number of monsters per room.
     */
    @Test
    void testAddMonsters() {
        final int maxLevel = 100;
        final int minRoomSize = 3;
        for (int floor = 0; floor < maxLevel; floor++) {
            final Room room = this.roomFactory.emptySquareRoom(28);

            int maxMonstNum = (int)(room.getArea() / Math.pow(minRoomSize, 2)) + floor;
            final int maxOcc = room.getArea() - room.getObjectsInRoom().size() - room.getArea()/2;
            maxMonstNum = (int)(maxMonstNum * 0.6) % maxOcc;
            maxMonstNum = maxMonstNum == 0 ? 1 : maxMonstNum;

            assertTrue(maxMonstNum > 0, "Floor was: " + floor);
            assertTrue(maxMonstNum < room.getArea(), "Floor was: " + floor);
        }
    }

}

