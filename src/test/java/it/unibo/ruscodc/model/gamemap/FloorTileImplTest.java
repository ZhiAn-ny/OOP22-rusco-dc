package it.unibo.ruscodc.model.gamemap;

import it.unibo.ruscodc.model.actors.Actor;
import it.unibo.ruscodc.model.actors.hero.HeroImpl;
import it.unibo.ruscodc.model.actors.monster.MonsterActionFactory;
import it.unibo.ruscodc.model.actors.monster.MonsterActionFactoryImpl;
import it.unibo.ruscodc.model.actors.monster.MonsterStatFactory;
import it.unibo.ruscodc.model.actors.monster.MonsterStatFactoryImpl;
import it.unibo.ruscodc.model.actors.skill.Skill;
import it.unibo.ruscodc.model.actors.skill.SkillImpl;
import it.unibo.ruscodc.model.actors.stat.StatImpl;
import it.unibo.ruscodc.model.interactable.Chest;
import it.unibo.ruscodc.model.interactable.Door;
import it.unibo.ruscodc.model.interactable.Interactable;
import it.unibo.ruscodc.utils.Direction;
import it.unibo.ruscodc.utils.GameControl;
import it.unibo.ruscodc.utils.Pair;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;


class FloorTileImplTest {
    private Actor getActor(final Pair<Integer, Integer> pos) {
        final MonsterStatFactory stats = new MonsterStatFactoryImpl();
        final MonsterActionFactory maf = new MonsterActionFactoryImpl();
        final Skill skills = new SkillImpl();
        skills.setAction(GameControl.ATTACK1, maf.basicMeleeAttack());
        return new HeroImpl("testHero", pos, skills, stats.ratStat());
    }

    /**
     * Method under test: default or parameterless constructor of {@link FloorTileImpl}.
     */
    @Test
    void testConstructor() {
        final Pair<Integer, Integer> pos = new Pair<>(2, 3);
        final FloorTileImpl floorTile = new FloorTileImpl(pos, true);

        assertEquals(pos, floorTile.getPos());
        assertFalse(floorTile.isTrap());
        assertFalse(floorTile.get().isPresent());
        assertEquals(1, floorTile.getID());
        assertEquals("file:src/main/resources/it/unibo/ruscodc/map_res/FloorTile", floorTile.getPath());
    }

    /**
     * Methods under test: {@link FloorTileImpl#FloorTileImpl(Pair, boolean)}.
     */
    @Test
    void testConstructorNullPosition() {
        assertThrows(IllegalArgumentException.class, () -> new FloorTileImpl(null, true));
    }

    /**
     * Method under test: {@link FloorTileImpl#isAccessible()}.
     */
    @Test
    void testIsAcccessible() {
        final Pair<Integer, Integer> pos = new Pair<>(2, 3);
        final FloorTileImpl floorTile = new FloorTileImpl(pos, true);

        assertTrue(floorTile.isAccessible());
    }

    /**
     * Method under test: {@link FloorTileImpl#isAccessible()}.
     */
    @Test
    void testIsAcccessibleWithNotTransitableObject() {
        final Pair<Integer, Integer> pos = new Pair<>(2, 3);
        final FloorTileImpl floorTile = new FloorTileImpl(pos, true);
        final Interactable obstacle = new Chest(Set.of(), pos);
        floorTile.put(obstacle);
        assertFalse(floorTile.isAccessible());
    }

    /**
     * Method under test: {@link FloorTileImpl#isAccessible()}.
     */
    @Test
    void testIsAcccessibleWithTransitableObject() {
        final Pair<Integer, Integer> pos = new Pair<>(2, 3);
        final FloorTileImpl floorTile = new FloorTileImpl(pos, true);

        floorTile.put(new Door(pos, Direction.UP));
        assertTrue(floorTile.isAccessible());
    }

    /**
     * Method under test: {@link FloorTileImpl#put(Interactable)}.
     */
    @Test
    void testPutEmptyTile() {
        final Pair<Integer, Integer> pos = new Pair<>(2, 3);
        final FloorTileImpl accessibleFloor = new FloorTileImpl(pos, true);
        final FloorTileImpl inaccessibleFloor = new FloorTileImpl(pos, false);

        assertTrue(accessibleFloor.put(new Chest(Set.of(), pos)));
        assertTrue(inaccessibleFloor.put(new Chest(Set.of(), pos)));
    }

    /**
     * Method under test: {@link FloorTileImpl#put(Interactable)}.
     */
    @Test
    void testPutTileInTile() {
        final Pair<Integer, Integer> pos = new Pair<>(2, 3);
        final FloorTileImpl floorTile = new FloorTileImpl(pos, true);

        assertFalse(floorTile.put(new FloorTrapTileImpl(pos)));
    }

    /**
     * Method under test: {@link FloorTileImpl#put(Interactable)}.
     */
    @Test
    void testPutOccupiedTile() {
        final Pair<Integer, Integer> pos = new Pair<>(2, 3);
        final FloorTileImpl floorTile = new FloorTileImpl(pos, true);

        floorTile.put(new Chest(Set.of(), pos));
        assertFalse(floorTile.put(new Chest(Set.of(), pos)));
    }

    /**
     * Method under test: {@link FloorTileImpl#get()}.
     */
    @Test
    void testGetOccupiedTile() {
        final Pair<Integer, Integer> pos = new Pair<>(2, 3);
        final FloorTileImpl floorTile = new FloorTileImpl(pos, true);

        floorTile.put(new Chest(Set.of(), pos));
        assertTrue(floorTile.get().isPresent());
    }

    /**
     * Method under test: {@link FloorTileImpl#get()}.
     */
    @Test
    void testGetEmptyTile() {
        final Pair<Integer, Integer> pos = new Pair<>(2, 3);
        final FloorTileImpl floorTile = new FloorTileImpl(pos, true);

        assertFalse(floorTile.get().isPresent());
    }

    /**
     * Method under test: {@link FloorTileImpl#get()}.
     */
    @Test
    void testGetAfterEmpty() {
        final Pair<Integer, Integer> pos = new Pair<>(2, 3);
        final FloorTileImpl floorTile = new FloorTileImpl(pos, true);

        floorTile.put(new Chest(Set.of(), pos));
        floorTile.empty();
        assertFalse(floorTile.get().isPresent());
    }

    /**
     * Method under test: {@link FloorTileImpl#get()}.
     */
    @Test
    void testEmptyEmptyTile() {
        final Pair<Integer, Integer> pos = new Pair<>(2, 3);
        final FloorTileImpl floorTile = new FloorTileImpl(pos, true);

        assertFalse(floorTile.empty().isPresent());
    }

    /**
     * Method under test: {@link FloorTileImpl#get()}.
     */
    @Test
    void testEmptyOccupiedTile() {
        final Pair<Integer, Integer> pos = new Pair<>(2, 3);
        final FloorTileImpl floorTile = new FloorTileImpl(pos, true);

        floorTile.put(new Chest(Set.of(), pos));
        assertTrue(floorTile.empty().isPresent());
    }

    /**
     * Methods under test: {@link FloorTileImpl#getEffect()}.
     */
    @Test
    void testGetEffect() {
        final Pair<Integer, Integer> pos = new Pair<>(2, 3);
        final FloorTileImpl floorTile = new FloorTileImpl(pos, true);
        final Actor actor = this.getActor(pos);
        final int hp = actor.getStatActual(StatImpl.StatName.HP);

        // A normal floor tile should not produce damage
        floorTile.getEffect().applyEffect(actor);
        assertEquals(hp, actor.getStatActual(StatImpl.StatName.HP));
    }
}

