package it.unibo.ruscodc.model.gamemap;

import it.unibo.ruscodc.model.Entity;
import it.unibo.ruscodc.model.actors.monster.Monster;
import it.unibo.ruscodc.model.actors.monster.MonsterImpl;
import it.unibo.ruscodc.model.actors.monster.behaviour.BehaviourImpl;
import it.unibo.ruscodc.model.actors.skill.SkillImpl;
import it.unibo.ruscodc.model.actors.stat.StatImpl;
import it.unibo.ruscodc.model.interactable.Chest;
import it.unibo.ruscodc.model.interactable.Door;
import it.unibo.ruscodc.model.interactable.Interactable;
import it.unibo.ruscodc.utils.Direction;
import it.unibo.ruscodc.utils.Pair;

import java.security.InvalidParameterException;

import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RectangleRoomImplTest {
    private static final String TEST_STR = "test";
    private static final int MIN_ROOM_SIDE = 3;

    /**
     * Method under test: {@link RectangleRoomImpl#RectangleRoomImpl(int, int)}.
     */
    @Test
    void testConstructorInvalidSize() {
        assertThrows(InvalidParameterException.class, () -> new RectangleRoomImpl(1, 1));
        assertThrows(InvalidParameterException.class, () -> new RectangleRoomImpl(MIN_ROOM_SIDE, 1));
    }

    /**
     * Method under test: {@link RectangleRoomImpl#RectangleRoomImpl(int, int)}.
     */
    @Test
    void testConstructorValidSize() {
        final RectangleRoomImpl room = new RectangleRoomImpl(MIN_ROOM_SIDE, MIN_ROOM_SIDE);
        final int expectedSize = (int) Math.pow(MIN_ROOM_SIDE + 2, 2);

        assertEquals(MIN_ROOM_SIDE, room.getSize().getY().intValue());
        assertEquals(MIN_ROOM_SIDE, room.getSize().getX().intValue());
        assertEquals(expectedSize, room.getTilesAsEntity().size());
        assertTrue(room.getMonsters().isEmpty());
        assertTrue(room.getObjectsInRoom().isEmpty());
    }

    /**
     * Method under test: {@link RectangleRoomImpl#isInRoom(Pair)}.
     */
    @Test
    void testIsInRoom() {
        final RectangleRoomImpl rectangleRoomImpl = new RectangleRoomImpl(MIN_ROOM_SIDE, MIN_ROOM_SIDE);
        assertTrue(rectangleRoomImpl.isInRoom(new Pair<>(2, 3)));
        assertTrue(rectangleRoomImpl.isInRoom(new Pair<>(0, 0)));
    }

    /**
     * Method under test: {@link RectangleRoomImpl#isInRoom(Pair)}.
     */
    @Test
    void testIsInRoom2() {
        final RectangleRoomImpl rectangleRoomImpl = new RectangleRoomImpl(MIN_ROOM_SIDE, MIN_ROOM_SIDE);
        final Pair<Integer, Integer> testPos = new Pair<>(5, 3);
        assertFalse(rectangleRoomImpl.isInRoom(testPos));
    }

    /**
     * Method under test: {@link RectangleRoomImpl#addMonster(Monster)}.
     */
    @Test
    void testAddMonsterEmptySlot() {
        final RectangleRoomImpl rectangleRoomImpl = new RectangleRoomImpl(MIN_ROOM_SIDE, MIN_ROOM_SIDE);
        final Pair<Integer, Integer> currentPos = new Pair<>(2, 3);
        final SkillImpl skills = new SkillImpl();
        final StatImpl stats = new StatImpl();
        final BehaviourImpl behaviour = new BehaviourImpl(null, null);
        final Monster mst = new MonsterImpl(TEST_STR, currentPos, skills, stats, behaviour);
        assertTrue(rectangleRoomImpl.addMonster(mst));
        assertEquals(1, rectangleRoomImpl.getMonsters().size());
    }

    /**
     * Method under test: {@link RectangleRoomImpl#addMonster(Monster)}.
     */
    @Test
    void testAddMonsterAlreadyPresent() {
        final RectangleRoomImpl rectangleRoomImpl = new RectangleRoomImpl(MIN_ROOM_SIDE, MIN_ROOM_SIDE);
        final Pair<Integer, Integer> currentPos = new Pair<>(2, 3);

        final SkillImpl skills = new SkillImpl();
        final StatImpl stats = new StatImpl();
        final BehaviourImpl behaviour = new BehaviourImpl(null, null);
        rectangleRoomImpl.addMonster(new MonsterImpl(
                TEST_STR, currentPos, skills, stats, behaviour));

        assertFalse(rectangleRoomImpl.addMonster(new MonsterImpl(
                TEST_STR, currentPos, skills, stats, behaviour)));
    }

    /**
     * Method under test: {@link RectangleRoomImpl#addMonster(Monster)}.
     */
    @Test
    void testAddMonsterOccupiedByItem() {
        final RectangleRoomImpl rectangleRoomImpl = new RectangleRoomImpl(MIN_ROOM_SIDE, MIN_ROOM_SIDE);
        final Pair<Integer, Integer> pos = new Pair<>(2, 3);
        final SkillImpl skills = new SkillImpl();
        final StatImpl stats = new StatImpl();
        final BehaviourImpl behaviour = new BehaviourImpl(null, null);

        rectangleRoomImpl.put(pos, new Chest(Set.of(), pos));

        // Items do not represent an obstacle to movement therefore,
        // monsters can be placed on top of an occupied Tile
        assertTrue(rectangleRoomImpl.addMonster(new MonsterImpl(
                TEST_STR, pos, skills, stats, behaviour)));
        assertEquals(1, rectangleRoomImpl.getMonsters().size());
    }

    /**
     * Method under test: {@link RectangleRoomImpl#addMonster(Monster)}.
     */
    @Test
    void testAddMonsterOutsideRoom() {
        final RectangleRoomImpl rectangleRoomImpl = new RectangleRoomImpl(MIN_ROOM_SIDE, MIN_ROOM_SIDE);
        final Pair<Integer, Integer> pos = new Pair<>(-2, 3);

        final SkillImpl skills = new SkillImpl();
        final StatImpl stats = new StatImpl();
        final BehaviourImpl behaviour = new BehaviourImpl(null, null);
        assertFalse(rectangleRoomImpl.addMonster(new MonsterImpl(
                TEST_STR, pos, skills, stats, behaviour)));
    }

    /**
     * Method under test: {@link RectangleRoomImpl#put(Pair, Interactable)}.
     */
    @Test
    void testPutEmptyFloor() {
        final RectangleRoomImpl rectangleRoomImpl = new RectangleRoomImpl(MIN_ROOM_SIDE, MIN_ROOM_SIDE);
        final Pair<Integer, Integer> pos = new Pair<>(2, 3);

        assertTrue(rectangleRoomImpl.put(pos, new Chest(Set.of(), pos)));
    }

    /**
     * Method under test: {@link RectangleRoomImpl#put(Pair, Interactable)}.
     */
    @Test
    void testPutOccupiedFloor() {
        final RectangleRoomImpl rectangleRoomImpl = new RectangleRoomImpl(MIN_ROOM_SIDE, MIN_ROOM_SIDE);
        final Pair<Integer, Integer> pos = new Pair<>(2, 3);

        rectangleRoomImpl.put(pos, new Chest(Set.of(), pos));
        assertFalse(rectangleRoomImpl.put(pos, new Chest(Set.of(), pos)));
    }

    /**
     * Method under test: {@link RectangleRoomImpl#put(Pair, Interactable)}.
     */
    @Test
    void testPutOutsideRoom() {
        final RectangleRoomImpl rectangleRoomImpl = new RectangleRoomImpl(MIN_ROOM_SIDE, MIN_ROOM_SIDE);
        final Pair<Integer, Integer> pos = new Pair<>(7, 3);

        assertFalse(rectangleRoomImpl.put(pos, new Chest(Set.of(), pos)));
    }

    /**
     * Method under test: {@link RectangleRoomImpl#put(Pair, Interactable)}.
     */
    @Test
    void testPutWall() {
        final RectangleRoomImpl rectangleRoomImpl = new RectangleRoomImpl(MIN_ROOM_SIDE, MIN_ROOM_SIDE);
        final Pair<Integer, Integer> pos = new Pair<>(0, 0);

        assertFalse(rectangleRoomImpl.put(pos, new Chest(Set.of(), pos)));
    }

    /**
     * Method under test: {@link RectangleRoomImpl#get(Pair)}.
     */
    @Test
    void testGetValid() {
        final RectangleRoomImpl rectangleRoomImpl = new RectangleRoomImpl(MIN_ROOM_SIDE, MIN_ROOM_SIDE);
        assertTrue(rectangleRoomImpl.get(new Pair<>(2, 3)).isPresent());
    }

    /**
     * Method under test: {@link RectangleRoomImpl#get(Pair)}.
     */
    @Test
    void testGetOutsideRoom() {
        final RectangleRoomImpl rectangleRoomImpl = new RectangleRoomImpl(MIN_ROOM_SIDE, MIN_ROOM_SIDE);
        final int outTest1 = 7;
        final int outTest2 = 8;
        assertFalse(rectangleRoomImpl.get(new Pair<>(outTest1, 3)).isPresent());
        assertFalse(rectangleRoomImpl.get(new Pair<>(2, outTest1)).isPresent());
        assertFalse(rectangleRoomImpl.get(new Pair<>(-outTest2, -outTest1)).isPresent());
    }

    /**
     * Method under test: {@link RectangleRoomImpl#isAccessible(Pair)}.
     */
    @Test
    void testIsAccessible() {
        final RectangleRoomImpl rectangleRoomImpl = new RectangleRoomImpl(MIN_ROOM_SIDE, MIN_ROOM_SIDE);
        assertTrue(rectangleRoomImpl.isAccessible(new Pair<>(2, 3)));
        assertFalse(rectangleRoomImpl.isAccessible(new Pair<>(0, 0)));
    }

    /**
     * Method under test: {@link RectangleRoomImpl#isAccessible(Pair)}.
     */
    @Test
    void testIsAccessibleOutsideRoom() {
        final RectangleRoomImpl rectangleRoomImpl = new RectangleRoomImpl(MIN_ROOM_SIDE, MIN_ROOM_SIDE);
        final Pair<Integer, Integer> testPos = new Pair<>(5, 6);
        assertFalse(rectangleRoomImpl.isAccessible(testPos));
    }

    /**
     * Method under test: {@link RectangleRoomImpl#getConnectedRoom(Direction)}.
     */
    @Test
    void testGetConnectedRoomIsolated() {
        final RectangleRoomImpl room = new RectangleRoomImpl(MIN_ROOM_SIDE, MIN_ROOM_SIDE);
        assertFalse(room.getConnectedRoom(Direction.LEFT).isPresent());
        assertFalse(room.getConnectedRoom(Direction.UP).isPresent());
        assertFalse(room.getConnectedRoom(Direction.DOWN).isPresent());
        assertFalse(room.getConnectedRoom(Direction.RIGHT).isPresent());
        assertFalse(room.getConnectedRoom(Direction.UNDEFINED).isPresent());
    }

    /**
     * Method under test: {@link RectangleRoomImpl#getConnectedRoom(Direction)}.
     */
    @Test
    void testGetConnectedRoomConnected() {
        final RectangleRoomImpl room = new RectangleRoomImpl(MIN_ROOM_SIDE, MIN_ROOM_SIDE);
        final RectangleRoomImpl other = new RectangleRoomImpl(4, 5);

        room.addDoor(Direction.UP);
        room.addConnectedRoom(Direction.UP, other);
        assertTrue(room.getConnectedRoom(Direction.UP).isPresent());
    }

    /**
     * Method under test: {@link RectangleRoomImpl#addConnectedRoom(Direction, Room)}.
     */
    @Test
    void testAddConnectedRoom() {
        final RectangleRoomImpl room = new RectangleRoomImpl(MIN_ROOM_SIDE, MIN_ROOM_SIDE);

        for (int i = 0; i < Direction.values().length; i++) {
            final Room other = new RectangleRoomImpl(4, 5);
            room.addDoor(Direction.values()[i]);
            if (Direction.values()[i] == Direction.UNDEFINED) {
                assertFalse(room.addConnectedRoom(Direction.values()[i], other));
            } else {
                assertTrue(room.addConnectedRoom(Direction.values()[i], other));
                assertTrue(other.getConnectedRoom(Direction.values()[i].getOpposite()).isPresent());
                assertEquals(room, other.getConnectedRoom(Direction.values()[i].getOpposite()).get());
            }
        }
    }

    /**
     * Method under test: {@link RectangleRoomImpl#addConnectedRoom(Direction, Room)}.
     */
    @Test
    void testAddConnectedRoomDirectionUndefined() {
        final RectangleRoomImpl room = new RectangleRoomImpl(MIN_ROOM_SIDE, MIN_ROOM_SIDE);
        final RectangleRoomImpl other = new RectangleRoomImpl(4, 5);

        room.addDoor(Direction.UNDEFINED);
        assertFalse(room.addConnectedRoom(Direction.UNDEFINED, other));
    }

    /**
     * Method under test: {@link RectangleRoomImpl#addConnectedRoom(Direction, Room)}.
     */
    @Test
    void testAddConnectedRoomNoDoor() {
        final RectangleRoomImpl room = new RectangleRoomImpl(MIN_ROOM_SIDE, MIN_ROOM_SIDE);
        final RectangleRoomImpl other = new RectangleRoomImpl(4, 5);

        assertFalse(room.addConnectedRoom(Direction.UP, other));
    }

    /**
     * Method under test: {@link RectangleRoomImpl#addConnectedRoom(Direction, Room)}.
     */
    @Test
    void testAddConnectedRoomAlreadyPresent() {
        final RectangleRoomImpl room = new RectangleRoomImpl(MIN_ROOM_SIDE, MIN_ROOM_SIDE);
        final RectangleRoomImpl other = new RectangleRoomImpl(4, 5);

        room.addDoor(Direction.UP);
        room.addConnectedRoom(Direction.UP, other);

        assertFalse(room.addConnectedRoom(Direction.UP, other));
    }

    /**
     * Method under test: {@link RectangleRoomImpl#addDoor(Direction)}.
     */
    @Test
    void testAddDoor() {
        final RectangleRoomImpl room = new RectangleRoomImpl(MIN_ROOM_SIDE, MIN_ROOM_SIDE);

        assertFalse(room.addDoor(Direction.UNDEFINED));
        assertTrue(room.addDoor(Direction.DOWN));
    }

    /**
     * Method under test: {@link RectangleRoomImpl#addDoor(Direction)}.
     */
    @Test
    void testAddDoorCheckCorners() {
        final int roomSize = 5;
        final int maxIterations = 30;
        for (int d = 0; d < Direction.values().length; d++) {
            final Direction dir = Direction.values()[d];
            if (dir == Direction.UNDEFINED) {
                continue;
            }
            final boolean compareX = dir == Direction.UP || dir == Direction.DOWN;
            for (int i = 0; i < maxIterations; i++) {
                final RectangleRoomImpl room = new RectangleRoomImpl(roomSize, roomSize);
                if (!room.addDoor(dir)) {
                    continue;
                }
                final Optional<Interactable> door = room.getDoorOnSide(dir);
                assertTrue(door.isPresent());
                final int toCompare = compareX ? door.get().getPos().getX() : door.get().getPos().getY();

                assertNotEquals(0, toCompare,
                        "coord: " + toCompare + " for direction " + dir.name());
                assertNotEquals(roomSize + 1, toCompare,
                        "coord: " + toCompare + " for direction " + dir.name());

            }
        }
    }

    /**
     * Method under test: {@link RectangleRoomImpl#addDoor(Direction)}.
     */
    @Test
    void testAddDoorFetchDoor() {
        final RectangleRoomImpl room = new RectangleRoomImpl(MIN_ROOM_SIDE, MIN_ROOM_SIDE);

        room.addDoor(Direction.UP);
        final Tile tile = (Tile) room.getTilesAsEntity().stream()
                .filter(t -> t.getPos().getY() == 0)
                .filter(t -> ((Tile) t).get().isPresent())
                .findFirst().orElse(new FloorTileImpl(new Pair<>(0, 0), true));
        final Entity entity = tile.get().orElse(null);
        assertTrue(entity instanceof Door);
    }

    /**
     * Method under test: {@link RectangleRoomImpl#addDoor(Direction)}.
     */
    @Test
    void testAddDoorDirectionUndefined() {
        final RectangleRoomImpl room = new RectangleRoomImpl(MIN_ROOM_SIDE, MIN_ROOM_SIDE);
        assertFalse(room.addDoor(Direction.UNDEFINED));
    }

    /**
     * Method under test: {@link RectangleRoomImpl#addDoor(Direction)}.
     */
    @Test
    void testAddDoorAlreadyPresent() {
        final RectangleRoomImpl room = new RectangleRoomImpl(MIN_ROOM_SIDE, MIN_ROOM_SIDE);
        room.addDoor(Direction.UP);
        assertFalse(room.addDoor(Direction.UP));
    }

    /**
     * Method under test: {@link RectangleRoomImpl#replaceTile(Pair, Tile)}.
     */
    @Test
    void testReplaceTile() {
        final int nTilesExcept = (int) Math.pow(MIN_ROOM_SIDE + 2, 2);
        final RectangleRoomImpl room = new RectangleRoomImpl(MIN_ROOM_SIDE, MIN_ROOM_SIDE);
        final Pair<Integer, Integer> pos = new Pair<>(1, 3);

        assertTrue(room.replaceTile(pos, new FloorTrapTileImpl(pos)));
        assertEquals(nTilesExcept, room.getTilesAsEntity().size());
        assertFalse(room.getObjectsInRoom().isEmpty());
    }

    /**
     * Method under test: {@link RectangleRoomImpl#replaceTile(Pair, Tile)}.
     */
    @Test
    void testReplaceTileOutsideRoom() {
        final RectangleRoomImpl room = new RectangleRoomImpl(MIN_ROOM_SIDE, MIN_ROOM_SIDE);
        final Pair<Integer, Integer> pos = new Pair<>(6, 3);

        assertFalse(room.replaceTile(pos, new FloorTrapTileImpl(pos)));
    }

    /**
     * Method under test: {@link RectangleRoomImpl#replaceTile(Pair, Tile)}.
     */
    @Test
    void testReplaceTileDifferentPosition() {
        final RectangleRoomImpl room = new RectangleRoomImpl(MIN_ROOM_SIDE, MIN_ROOM_SIDE);

        assertFalse(room.replaceTile(new Pair<>(1, 3), new FloorTrapTileImpl(new Pair<>(2, 3))));
    }

    /**
     * Method under test: {@link RectangleRoomImpl#replaceTile(Pair, Tile)}.
     */
    @Test
    void testReplaceTileNullPosition() {
        final RectangleRoomImpl room = new RectangleRoomImpl(MIN_ROOM_SIDE, MIN_ROOM_SIDE);

        assertThrows(NullPointerException.class,
                () -> room.replaceTile(null, new FloorTrapTileImpl(new Pair<>(2, 3))));
    }

    /**
     * Method under test: {@link RectangleRoomImpl#replaceTile(Pair, Tile)}.
     */
    @Test
    void testReplaceTileNullTile() {
        final RectangleRoomImpl room = new RectangleRoomImpl(MIN_ROOM_SIDE, MIN_ROOM_SIDE);

        assertThrows(NullPointerException.class,
                () -> room.replaceTile(new Pair<>(2, 3), null));
    }

}

