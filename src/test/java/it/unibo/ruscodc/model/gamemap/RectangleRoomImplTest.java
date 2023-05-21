package it.unibo.ruscodc.model.gamemap;

import it.unibo.ruscodc.model.Entity;
import it.unibo.ruscodc.model.actors.monster.Monster;
import it.unibo.ruscodc.model.actors.monster.MonsterImpl;
import it.unibo.ruscodc.model.actors.monster.behaviour.BehaviourImpl;
import it.unibo.ruscodc.model.actors.skill.SkillImpl;
import it.unibo.ruscodc.model.actors.stat.StatImpl;
import it.unibo.ruscodc.model.interactable.Chest;
import it.unibo.ruscodc.model.interactable.Interactable;
import it.unibo.ruscodc.utils.Direction;
import it.unibo.ruscodc.utils.Pair;

import java.security.InvalidParameterException;
import java.util.List;

import java.util.Set;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RectangleRoomImplTest {
    /**
     * Method under test: {@link RectangleRoomImpl#RectangleRoomImpl(int, int)}
     */
    @Test
    void testConstructor_InvalidSize() {
        assertThrows(InvalidParameterException.class, () -> new RectangleRoomImpl(1, 1));
        assertThrows(InvalidParameterException.class, () -> new RectangleRoomImpl(3, 1));
    }

    /**
     * Method under test: {@link RectangleRoomImpl#RectangleRoomImpl(int, int)}
     */
    @Test
    void testConstructor_ValidSize() {
        RectangleRoomImpl room = new RectangleRoomImpl(3, 3);

        assertEquals(3, room.getSize().getY().intValue());
        assertEquals(3, room.getSize().getX().intValue());
        assertEquals(25, room.getTilesAsEntity().size());
        assertTrue(room.getMonsters().isEmpty());
        assertTrue(room.getObjectsInRoom().isEmpty());
    }

    /**
     * Method under test: {@link RectangleRoomImpl#isInRoom(Pair)}
     */
    @Test
    void testIsInRoom() {
        RectangleRoomImpl rectangleRoomImpl = new RectangleRoomImpl(3, 3);
        assertTrue(rectangleRoomImpl.isInRoom(new Pair<>(2, 3)));
        assertTrue(rectangleRoomImpl.isInRoom(new Pair<>(0, 0)));
    }

    /**
     * Method under test: {@link RectangleRoomImpl#isInRoom(Pair)}
     */
    @Test
    void testIsInRoom2() {
        RectangleRoomImpl rectangleRoomImpl = new RectangleRoomImpl(3, 3);
        assertFalse(rectangleRoomImpl.isInRoom(new Pair<>(5, 3)));
    }

    /**
     * Method under test: {@link RectangleRoomImpl#addMonster(Monster)}
     */
    @Test
    void testAddMonster_EmptySlot() {
        RectangleRoomImpl rectangleRoomImpl = new RectangleRoomImpl(3, 3);
        Pair<Integer, Integer> currentPos = new Pair<>(2, 3);

        SkillImpl skills = new SkillImpl();
        StatImpl stats = new StatImpl();
        BehaviourImpl behaviour = new BehaviourImpl(null, null);
        assertTrue(rectangleRoomImpl
                .addMonster(new MonsterImpl("Name", currentPos, skills, stats,behaviour)));
        assertEquals(1, rectangleRoomImpl.getMonsters().size());
    }

    /**
     * Method under test: {@link RectangleRoomImpl#addMonster(Monster)}
     */
    @Test
    void testAddMonster_AlreadyPresent() {
        RectangleRoomImpl rectangleRoomImpl = new RectangleRoomImpl(3, 3);
        Pair<Integer, Integer> currentPos = new Pair<>(2, 3);

        SkillImpl skills = new SkillImpl();
        StatImpl stats = new StatImpl();
        BehaviourImpl behaviour = new BehaviourImpl(null, null);
        rectangleRoomImpl.addMonster(new MonsterImpl(
                "Name", currentPos, skills, stats, behaviour));

        assertFalse(rectangleRoomImpl.addMonster(new MonsterImpl(
                "Name", currentPos, skills, stats, behaviour)));
    }

    /**
     * Method under test: {@link RectangleRoomImpl#addMonster(Monster)}
     */
    @Test
    void testAddMonster_OccupiedByItem() {
        fail("Are items an obstacle to movement?");
        RectangleRoomImpl rectangleRoomImpl = new RectangleRoomImpl(3, 3);
        Pair<Integer, Integer> pos = new Pair<>(2, 3);

        rectangleRoomImpl.put(pos, new Chest(Set.of(), pos));

        SkillImpl skills = new SkillImpl();
        StatImpl stats = new StatImpl();
        BehaviourImpl behaviour = new BehaviourImpl(null, null);
        assertTrue(rectangleRoomImpl.addMonster(new MonsterImpl(
                "Name", pos, skills, stats, behaviour)));
        assertEquals(1, rectangleRoomImpl.getMonsters().size());
    }

    /**
     * Method under test: {@link RectangleRoomImpl#addMonster(Monster)}
     */
    @Test
    void testAddMonster_OutsideRoom() {
        RectangleRoomImpl rectangleRoomImpl = new RectangleRoomImpl(3, 3);
        Pair<Integer, Integer> pos = new Pair<>(-2, 3);

        SkillImpl skills = new SkillImpl();
        StatImpl stats = new StatImpl();
        BehaviourImpl behaviour = new BehaviourImpl(null, null);
        assertFalse(rectangleRoomImpl.addMonster(new MonsterImpl(
                "Name", pos, skills, stats, behaviour)));
    }

    /**
     * Method under test: {@link RectangleRoomImpl#put(Pair, Interactable)}
     */
    @Test
    void testPut_EmptyFloor() {
        RectangleRoomImpl rectangleRoomImpl = new RectangleRoomImpl(3, 3);
        Pair<Integer, Integer> pos = new Pair<>(2, 3);

        assertTrue(rectangleRoomImpl.put(pos, new Chest(Set.of(), pos)));
    }

    /**
     * Method under test: {@link RectangleRoomImpl#put(Pair, Interactable)}
     */
    @Test
    void testPut_OccupiedFloor() {
        RectangleRoomImpl rectangleRoomImpl = new RectangleRoomImpl(3, 3);
        Pair<Integer, Integer> pos = new Pair<>(2, 3);

        rectangleRoomImpl.put(pos, new Chest(Set.of(), pos));
        assertFalse(rectangleRoomImpl.put(pos, new Chest(Set.of(), pos)));
    }

    /**
     * Method under test: {@link RectangleRoomImpl#put(Pair, Interactable)}
     */
    @Test
    void testPut_OutsideRoom() {
        RectangleRoomImpl rectangleRoomImpl = new RectangleRoomImpl(3, 3);
        Pair<Integer, Integer> pos = new Pair<>(7, 3);

        assertFalse(rectangleRoomImpl.put(pos, new Chest(Set.of(), pos)));
    }

    /**
     * Method under test: {@link RectangleRoomImpl#put(Pair, Interactable)}
     */
    @Test
    void testPut_Wall() {
        RectangleRoomImpl rectangleRoomImpl = new RectangleRoomImpl(3, 3);
        Pair<Integer, Integer> pos = new Pair<>(0, 0);

        assertFalse(rectangleRoomImpl.put(pos, new Chest(Set.of(), pos)));
    }

    /**
     * Method under test: {@link RectangleRoomImpl#get(Pair)}
     */
    @Test
    void testGet_Valid() {
        RectangleRoomImpl rectangleRoomImpl = new RectangleRoomImpl(3, 3);
        assertTrue(rectangleRoomImpl.get(new Pair<>(2, 3)).isPresent());
    }

    /**
     * Method under test: {@link RectangleRoomImpl#get(Pair)}
     */
    @Test
    void testGet_OutsideRoom() {
        RectangleRoomImpl rectangleRoomImpl = new RectangleRoomImpl(3, 3);
        assertFalse(rectangleRoomImpl.get(new Pair<>(7, 3)).isPresent());
        assertFalse(rectangleRoomImpl.get(new Pair<>(2, 7)).isPresent());
        assertFalse(rectangleRoomImpl.get(new Pair<>(-8, -7)).isPresent());
    }

    /**
     * Method under test: {@link RectangleRoomImpl#isAccessible(Pair)}
     */
    @Test
    void testIsAccessible() {
        RectangleRoomImpl rectangleRoomImpl = new RectangleRoomImpl(3, 3);
        assertTrue(rectangleRoomImpl.isAccessible(new Pair<>(2, 3)));
        assertFalse(rectangleRoomImpl.isAccessible(new Pair<>(0, 0)));
    }

    /**
     * Method under test: {@link RectangleRoomImpl#isAccessible(Pair)}
     */
    @Test
    void testIsAccessible_OutsideRoom() {
        RectangleRoomImpl rectangleRoomImpl = new RectangleRoomImpl(3, 3);
        assertFalse(rectangleRoomImpl.isAccessible(new Pair<>(5, 6)));
    }

    /**
     * Method under test: {@link RectangleRoomImpl#getConnectedRoom(Direction)}
     */
    @Test
    void testGetConnectedRoom_Isolated() {
        RectangleRoomImpl room = new RectangleRoomImpl(3, 3);
        assertFalse(room.getConnectedRoom(Direction.LEFT).isPresent());
        assertFalse(room.getConnectedRoom(Direction.UP).isPresent());
        assertFalse(room.getConnectedRoom(Direction.DOWN).isPresent());
        assertFalse(room.getConnectedRoom(Direction.RIGHT).isPresent());
        assertFalse(room.getConnectedRoom(Direction.UNDEFINED).isPresent());
    }

    /**
     * Method under test: {@link RectangleRoomImpl#getConnectedRoom(Direction)}
     */
    @Test
    void testGetConnectedRoom_Connected() {
        RectangleRoomImpl room = new RectangleRoomImpl(3, 3);
        RectangleRoomImpl other = new RectangleRoomImpl(4, 5);

        room.addDoor(Direction.UP);
        room.addConnectedRoom(Direction.UP, other);
        assertTrue(room.getConnectedRoom(Direction.UP).isPresent());
    }

    /**
     * Method under test: {@link RectangleRoomImpl#addConnectedRoom(Direction, Room)}
     */
    @Test
    void testAddConnectedRoom() {
        RectangleRoomImpl room = new RectangleRoomImpl(3, 3);
        RectangleRoomImpl other = new RectangleRoomImpl(4, 5);

        room.addDoor(Direction.UP);
        room.addDoor(Direction.DOWN);
        room.addDoor(Direction.LEFT);
        room.addDoor(Direction.RIGHT);

        assertTrue(room.addConnectedRoom(Direction.UP, other));
        assertTrue(room.addConnectedRoom(Direction.DOWN, other));
        assertTrue(room.addConnectedRoom(Direction.LEFT, other));
        assertTrue(room.addConnectedRoom(Direction.RIGHT, other));
    }

    /**
     * Method under test: {@link RectangleRoomImpl#addConnectedRoom(Direction, Room)}
     */
    @Test
    void testAddConnectedRoom_DirectionUndefined() {
        RectangleRoomImpl room = new RectangleRoomImpl(3, 3);
        RectangleRoomImpl other = new RectangleRoomImpl(4, 5);

        room.addDoor(Direction.UNDEFINED);
        assertFalse(room.addConnectedRoom(Direction.UNDEFINED, other));
    }

    /**
     * Method under test: {@link RectangleRoomImpl#addConnectedRoom(Direction, Room)}
     */
    @Test
    void testAddConnectedRoom_NoDoor() {
        RectangleRoomImpl room = new RectangleRoomImpl(3, 3);
        RectangleRoomImpl other = new RectangleRoomImpl(4, 5);

        assertFalse(room.addConnectedRoom(Direction.UP, other));
    }

    /**
     * Method under test: {@link RectangleRoomImpl#addConnectedRoom(Direction, Room)}
     */
    @Test
    void testAddConnectedRoom_AlreadyPresent() {
        RectangleRoomImpl room = new RectangleRoomImpl(3, 3);
        RectangleRoomImpl other = new RectangleRoomImpl(4, 5);

        room.addDoor(Direction.UP);
        room.addConnectedRoom(Direction.UP, other);

        assertFalse(room.addConnectedRoom(Direction.UP, other));
    }

    /**
     * Method under test: {@link RectangleRoomImpl#addDoor(Direction)}
     */
    @Test
    void testAddDoor() {
        RectangleRoomImpl room = new RectangleRoomImpl(3, 3);

        assertTrue(room.addDoor(Direction.UP));
        assertTrue(room.addDoor(Direction.DOWN));
        assertTrue(room.addDoor(Direction.LEFT));
        assertTrue(room.addDoor(Direction.RIGHT));
    }

    /**
     * Method under test: {@link RectangleRoomImpl#addDoor(Direction)}
     */
    @Test
    void testAddDoor_DirectionUndefined() {
        RectangleRoomImpl room = new RectangleRoomImpl(3, 3);
        assertFalse(room.addDoor(Direction.UNDEFINED));
    }

    /**
     * Method under test: {@link RectangleRoomImpl#addDoor(Direction)}
     */
    @Test
    void testAddDoor_AlreadyPresent() {
        RectangleRoomImpl room = new RectangleRoomImpl(3, 3);
        room.addDoor(Direction.UP);
        assertFalse(room.addDoor(Direction.UP));
    }

}

