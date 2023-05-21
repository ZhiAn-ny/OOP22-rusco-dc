package it.unibo.ruscodc.model.gamemap;

import it.unibo.ruscodc.model.actors.Actor;
import it.unibo.ruscodc.model.actors.hero.HeroImpl;
import it.unibo.ruscodc.model.actors.monster.MonsterGeneratorImpl;
import it.unibo.ruscodc.model.actors.monster.MonsterImpl;
import it.unibo.ruscodc.model.actors.monster.behaviour.BehaviourImpl;
import it.unibo.ruscodc.model.actors.skill.SkillImpl;
import it.unibo.ruscodc.model.actors.stat.StatImpl;
import it.unibo.ruscodc.model.interactable.Chest;
import it.unibo.ruscodc.model.interactable.Interactable;
import it.unibo.ruscodc.utils.Pair;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class FloorTileImplTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link FloorTileImpl#FloorTileImpl(Pair, boolean)}
     *   <li>{@link FloorTileImpl#getID()}
     *   <li>{@link FloorTileImpl#getPath()}
     * </ul>
     */
    @Test
    void testConstructor() {
        final Pair<Integer, Integer> pos = new Pair<>(2, 3);
        FloorTileImpl floorTile = new FloorTileImpl(pos, true);

        assertEquals(pos, floorTile.getPos());
        assertTrue(floorTile.isAccessible());
        assertFalse(floorTile.isTrap());

        assertEquals("floor", floorTile.getID());
        assertEquals("file:src/main/resources/it/unibo/ruscodc/map_res/FloorTile", floorTile.getPath());

        floorTile = new FloorTileImpl(pos, false);
        assertFalse(floorTile.isAccessible());
        assertFalse(floorTile.isTrap());
    }

    /**
     * Tests object placement and displacement.
     * Methods under test:
     *
     * <ul>
     *   <li>{@link FloorTileImpl#put(Interactable)}
     *   <li>{@link FloorTileImpl#get()}
     *   <li>{@link FloorTileImpl#empty()}
     * </ul>
     */
    @Test
    void testObjectPlacement() {
        final Pair<Integer, Integer> pos = new Pair<>(2, 3);
        final FloorTileImpl floorTile = new FloorTileImpl(pos, true);
        final Interactable obj = new Chest(Set.of(), pos);

        assertFalse(floorTile.get().isPresent());
        assertFalse(floorTile.empty().isPresent());

        assertTrue(floorTile.put(obj));
        assertTrue(floorTile.get().isPresent());
        assertTrue(floorTile.empty().isPresent());

        assertFalse(floorTile.get().isPresent());
        assertFalse(floorTile.empty().isPresent());
    }

    /**
     * Tests object placement over occupied Tile.
     * Methods under test:
     *
     * <ul>
     *   <li>{@link FloorTileImpl#put(Interactable)}
     *   <li>{@link FloorTileImpl#get()}
     *   <li>{@link FloorTileImpl#empty()}
     * </ul>
     */
    @Test
    void testObjectPlacement2() {
        final Pair<Integer, Integer> pos = new Pair<>(2, 3);
        final FloorTileImpl floorTile = new FloorTileImpl(pos, true);
        final Interactable obj = new Chest(Set.of(), pos);

        assertTrue(floorTile.put(obj));
        assertTrue(floorTile.get().isPresent());

        assertFalse(floorTile.put(obj));
        assertTrue(floorTile.get().isPresent());

        assertTrue(floorTile.empty().isPresent());
        assertTrue(floorTile.put(obj));
    }

    /**
     * Methods under test: {@link FloorTileImpl#getEffect()}
     */
    @Test
    @Disabled
    void testGetEffect() {
        final Pair<Integer, Integer> pos = new Pair<>(2, 3);
        final FloorTileImpl floorTile = new FloorTileImpl(pos, true);
        final Actor actor = new HeroImpl("testHero", pos, new SkillImpl(), new StatImpl());

        // A normal floor tile should not produce damage
        int hp = actor.getStatInfo(StatImpl.StatName.HP);
        floorTile.getEffect().applyEffect(actor);
        assertEquals(hp, actor.getStatInfo(StatImpl.StatName.HP));
    }

    /**
     * Methods under test: {@link FloorTileImpl#getEffect()}
     */
    @Test
    @Disabled
    void testGetEffect2() {
        final Pair<Integer, Integer> pos = new Pair<>(2, 3);
        final FloorTileImpl floorTile = new FloorTileImpl(pos, true);
        final Actor actor = new MonsterGeneratorImpl().makeMeleeRat("test", pos);

        // A normal floor tile should not produce damage
        int hp = actor.getStatInfo(StatImpl.StatName.HP);
        floorTile.getEffect().applyEffect(actor);
        assertEquals(hp, actor.getStatInfo(StatImpl.StatName.HP));
    }
}

