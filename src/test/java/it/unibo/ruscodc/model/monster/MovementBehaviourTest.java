package it.unibo.ruscodc.model.monster;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import org.junit.jupiter.api.Test;

import it.unibo.ruscodc.model.actors.hero.Hero;
import it.unibo.ruscodc.model.actors.hero.HeroImpl;
import it.unibo.ruscodc.model.actors.hero.HeroSkill;
import it.unibo.ruscodc.model.actors.hero.HeroStat;
import it.unibo.ruscodc.model.actors.monster.Monster;
import it.unibo.ruscodc.model.actors.monster.MonsterImpl;
import it.unibo.ruscodc.model.actors.monster.MonsterStatFactoryImpl;
import it.unibo.ruscodc.model.actors.monster.behaviour.BehaviourFactory;
import it.unibo.ruscodc.model.actors.monster.behaviour.BehaviourFactoryImpl;
import it.unibo.ruscodc.model.actors.skill.SkillImpl;
import it.unibo.ruscodc.model.gamemap.RectangleRoomImpl;
import it.unibo.ruscodc.model.gamemap.Room;
import it.unibo.ruscodc.utils.Pair;
import it.unibo.ruscodc.utils.exception.ModelException;

/**
 * Test for the movement of different Monsters.
 */
class MovementBehaviourTest {

    private static final int SIZE = 7;
    private static final Pair<Integer, Integer> CENTRE_POS = new Pair<>(3, 3);
    private static final Pair<Integer, Integer> UP_POS = new Pair<>(3, 1);
    private static final Pair<Integer, Integer> DOWN_POS = new Pair<>(3, 5);
    private static final Pair<Integer, Integer> LEFT_POS = new Pair<>(1, 3);
    private static final Pair<Integer, Integer> RIGHT_POS = new Pair<>(5, 3);

    private final MonsterStatFactoryImpl monsterStatFactoryImpl = new MonsterStatFactoryImpl();
    private final BehaviourFactory behaviourFactory = new BehaviourFactoryImpl();

    @Test
    void testAggressiveMoveUP() {
        final Room room = new RectangleRoomImpl(SIZE, SIZE);
        final Monster monster = new MonsterImpl(
            "",
            CENTRE_POS,
            new SkillImpl(),
            this.monsterStatFactoryImpl.ratStat(),
            this.behaviourFactory.makeMeleeAggressive()
        );

        room.addMonster(monster);

        final Hero hero = new HeroImpl("", UP_POS, new HeroSkill(), new HeroStat());

        try {
            monster.behave(room, List.of(hero)).execute();
        } catch (final ModelException e) {
            fail("MoveUP Error", e);
        }

        assertEquals(new Pair<>(3, 2), monster.getPos());
    }

    @Test
    void testAggressiveMoveDOWN() {
        final Room room = new RectangleRoomImpl(SIZE, SIZE);
        final Monster monster = new MonsterImpl(
            "",
            CENTRE_POS,
            new SkillImpl(),
            this.monsterStatFactoryImpl.ratStat(),
            this.behaviourFactory.makeMeleeAggressive()
        );

        room.addMonster(monster);

        final Hero hero = new HeroImpl("", DOWN_POS, new HeroSkill(), new HeroStat());

        try {
            monster.behave(room, List.of(hero)).execute();
        } catch (final ModelException e) {
            fail("MoveDOWN Error", e);
        }

        assertEquals(new Pair<>(3, 4), monster.getPos());
    }

    @Test
    void testAggressiveMoveLEFT() {
        final Room room = new RectangleRoomImpl(SIZE, SIZE);
        final Monster monster = new MonsterImpl(
            "",
            CENTRE_POS,
            new SkillImpl(),
            this.monsterStatFactoryImpl.ratStat(),
            this.behaviourFactory.makeMeleeAggressive()
        );

        room.addMonster(monster);

        final Hero hero = new HeroImpl("", LEFT_POS, new HeroSkill(), new HeroStat());

        try {
            monster.behave(room, List.of(hero)).execute();
        } catch (final ModelException e) {
            fail("MoveLEFT Error", e);
        }

        assertEquals(new Pair<>(2, 3), monster.getPos());
    }

    @Test
    void testAggressiveMoveRIGHT() {
        final Room room = new RectangleRoomImpl(SIZE, SIZE);
        final Monster monster = new MonsterImpl(
            "",
            CENTRE_POS,
            new SkillImpl(),
            this.monsterStatFactoryImpl.ratStat(),
            this.behaviourFactory.makeMeleeAggressive()
        );

        room.addMonster(monster);

        final Hero hero = new HeroImpl("", RIGHT_POS, new HeroSkill(), new HeroStat());

        try {
            monster.behave(room, List.of(hero)).execute();
        } catch (final ModelException e) {
            fail("MoveRIGHT Error", e);
        }

        assertEquals(new Pair<>(4, 3), monster.getPos());
    }

    @Test
    void testRandomMove() {
        final Room room = new RectangleRoomImpl(SIZE, SIZE);
        final Monster monster = new MonsterImpl(
            "",
            CENTRE_POS,
            new SkillImpl(),
            this.monsterStatFactoryImpl.ratStat(),
            this.behaviourFactory.makeMeleeBrainless()
        );

        room.addMonster(monster);

        final Hero hero = new HeroImpl("", UP_POS, new HeroSkill(), new HeroStat());

        final Pair<Integer, Integer> oldPos = monster.getPos();

        try {
            monster.behave(room, List.of(hero)).execute();
        } catch (final ModelException e) {
            fail("MoveRANDOM Error", e);
        }

        assertNotEquals(oldPos, monster.getPos());
    }

    @Test
    void testShyMoveDOWN() {
        final Room room = new RectangleRoomImpl(SIZE, SIZE);
        final Monster monster = new MonsterImpl(
            "",
            CENTRE_POS,
            new SkillImpl(),
            this.monsterStatFactoryImpl.ratStat(),
            this.behaviourFactory.makeRangedShy()
        );

        room.addMonster(monster);

        final Hero hero = new HeroImpl("", UP_POS, new HeroSkill(), new HeroStat());

        try {
            monster.behave(room, List.of(hero)).execute();
        } catch (final ModelException e) {
            fail("MoveDOWN Error", e);
        }

        assertEquals(new Pair<>(3, 4), monster.getPos());
    }

    @Test
    void testShyMoveUP() {
        final Room room = new RectangleRoomImpl(SIZE, SIZE);
        final Monster monster = new MonsterImpl(
            "",
            CENTRE_POS,
            new SkillImpl(),
            this.monsterStatFactoryImpl.opossumStat(),
            this.behaviourFactory.makeRangedShy()
        );

        room.addMonster(monster);

        final Hero hero = new HeroImpl("", DOWN_POS, new HeroSkill(), new HeroStat());

        try {
            monster.behave(room, List.of(hero)).execute();
        } catch (final ModelException e) {
            fail("MoveUP Error", e);
        }

        assertEquals(new Pair<>(3, 2), monster.getPos());
    }

    @Test
    void testShyMoveRIGHT() {
        final Room room = new RectangleRoomImpl(SIZE, SIZE);
        final Monster monster = new MonsterImpl(
            "",
            CENTRE_POS,
            new SkillImpl(),
            this.monsterStatFactoryImpl.opossumStat(),
            this.behaviourFactory.makeRangedShy()
        );

        room.addMonster(monster);

        final Hero hero = new HeroImpl("", LEFT_POS, new HeroSkill(), new HeroStat());

        try {
            monster.behave(room, List.of(hero)).execute();
        } catch (final ModelException e) {
            fail("MoveRIGHT Error", e);
        }

        assertEquals(new Pair<>(4, 3), monster.getPos());
    }

    @Test
    void testShyMoveLEFT() {
        final Room room = new RectangleRoomImpl(SIZE, SIZE);
        final Monster monster = new MonsterImpl(
            "",
            CENTRE_POS,
            new SkillImpl(),
            this.monsterStatFactoryImpl.opossumStat(),
            this.behaviourFactory.makeRangedShy()
        );

        room.addMonster(monster);

        final Hero hero = new HeroImpl("", RIGHT_POS, new HeroSkill(), new HeroStat());

        try {
            monster.behave(room, List.of(hero)).execute();
        } catch (final ModelException e) {
            fail("MoveLEFT Error", e);
        }

        assertEquals(new Pair<>(2, 3), monster.getPos());
    }
}
