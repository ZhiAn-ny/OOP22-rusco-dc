package it.unibo.ruscodc.model.gamemap;

import it.unibo.ruscodc.model.actors.Actor;
import it.unibo.ruscodc.model.actors.hero.HeroImpl;
import it.unibo.ruscodc.model.actors.skill.SkillImpl;
import it.unibo.ruscodc.model.actors.stat.StatImpl;
import it.unibo.ruscodc.model.interactable.Chest;
import it.unibo.ruscodc.model.interactable.Interactable;
import it.unibo.ruscodc.utils.Pair;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.*;

class FloorTrapTileImplTest {
    private static final int DEFAULT_DMG = 5;
    
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link FloorTrapTileImpl#FloorTrapTileImpl(Pair)}
     *   <li>{@link FloorTrapTileImpl#getName()}
     *   <li>{@link FloorTrapTileImpl#isTrap()}
     *   <li>{@link FloorTrapTileImpl#isAccessible()}
     *   <li>{@link FloorTrapTileImpl#getPosition()}
     * </ul>
     */
    @Test
    void testConstructor() {
        Pair<Integer, Integer> pos = new Pair<>(2, 3);
        FloorTrapTileImpl trapTile = new FloorTrapTileImpl(pos);

        assertEquals(pos, trapTile.getPosition());
        assertTrue(trapTile.isAccessible());
        assertTrue(trapTile.isTrap());
        assertEquals("It's a trap!", trapTile.getName());
    }

    /**
     * Methods under test:
     * <ul>
     *     <li>{@link FloorTrapTileImpl#get()}
     *     <li>{@link FloorTrapTileImpl#empty()}
     * </ul>
     */
    @Test
    void testGet() {
        final Pair<Integer, Integer> pos = new Pair<>(2, 3);
        final FloorTrapTileImpl trapTile = new FloorTrapTileImpl(pos);

        assertTrue(trapTile.get().isPresent());
        assertEquals(trapTile, trapTile.get().get());
        assertTrue(trapTile.empty().isPresent());
        assertEquals(trapTile, trapTile.empty().get());
    }

    /**
     * Method under test: {@link FloorTrapTileImpl#put(Interactable)}
     */
    @Test
    void testPut() {
        final Pair<Integer, Integer> pos = new Pair<>(2, 3);
        final FloorTrapTileImpl trapTile = new FloorTrapTileImpl(pos);
        final Interactable obj = new Chest(Set.of(), pos);

        assertFalse(trapTile.put(obj));
        trapTile.empty();
        assertFalse(trapTile.put(obj));
    }

    /**
     * Method under test: {@link FloorTrapTileImpl#getEffect()}
     */
    @Test
    @Disabled
    void testGetEffect() {
        final Pair<Integer, Integer> pos = new Pair<>(2, 3);
        final FloorTrapTileImpl trapTile = new FloorTrapTileImpl(pos);
        final Actor actor = new HeroImpl("testHero", pos, new SkillImpl(), new StatImpl());

        int hp = actor.getStatInfo(StatImpl.StatName.HP);
        trapTile.getEffect().applyEffect(actor);
        assertEquals(hp - DEFAULT_DMG, actor.getStatInfo(StatImpl.StatName.HP));
    }

    /**
     * Method under test: {@link FloorTrapTileImpl#interact()}
     */
    @Test
    @Disabled()
    void testInteract() {
        final Pair<Integer, Integer> pos = new Pair<>(2, 3);
        final FloorTrapTileImpl trapTile = new FloorTrapTileImpl(pos);
        final Actor actor = new HeroImpl("testHero", pos, new SkillImpl(), new StatImpl());

        int hp = actor.getStatInfo(StatImpl.StatName.HP);
        trapTile.getEffect().applyEffect(actor);
        assertEquals(hp - DEFAULT_DMG, actor.getStatInfo(StatImpl.StatName.HP));
        
        trapTile.interact();
        hp = actor.getStatInfo(StatImpl.StatName.HP);
        trapTile.getEffect().applyEffect(actor);
        assertEquals(hp, actor.getStatInfo(StatImpl.StatName.HP));

        trapTile.interact();
        trapTile.getEffect().applyEffect(actor);
        assertEquals(hp, actor.getStatInfo(StatImpl.StatName.HP));
    }

    /**
     * Method under test: {@link FloorTrapTileImpl#setDisableSuccessRate(int)}
     */
    @Test
    @Disabled()
    void testSetDisableSuccessRate() {
        final Pair<Integer, Integer> pos = new Pair<>(2, 3);
        final Actor actor = new HeroImpl("testHero", pos, new SkillImpl(), new StatImpl());
        final FloorTrapTileImpl trapTile = new FloorTrapTileImpl(pos);

        trapTile.setDisableSuccessRate(1);
        trapTile.interact();

        int hp = actor.getStatInfo(StatImpl.StatName.HP);
        trapTile.getEffect().applyEffect(actor);
        assertEquals(hp - DEFAULT_DMG, actor.getStatInfo(StatImpl.StatName.HP));

        trapTile.setDisableSuccessRate(100);
        trapTile.interact();
        hp = actor.getStatInfo(StatImpl.StatName.HP);
        trapTile.getEffect().applyEffect(actor);
        assertEquals(hp, actor.getStatInfo(StatImpl.StatName.HP));

        trapTile.setDisableSuccessRate(1);
        trapTile.interact();
        trapTile.getEffect().applyEffect(actor);
        assertEquals(hp - DEFAULT_DMG, actor.getStatInfo(StatImpl.StatName.HP));
    }

    /**
     * Method under test: {@link FloorTrapTileImpl#setDisableSuccessRate(int)}
     */
    @Test
    @Disabled()
    void testSetDisableSuccessRate2() {
        final Pair<Integer, Integer> pos = new Pair<>(2, 3);
        final Actor actor = new HeroImpl("testHero", pos, new SkillImpl(), new StatImpl());
        final FloorTrapTileImpl trapTile = new FloorTrapTileImpl(pos);
        final int dmg = 100;

        trapTile.setDamage(dmg);
        int hp = actor.getStatInfo(StatImpl.StatName.HP);
        trapTile.getEffect().applyEffect(actor);
        assertEquals(hp - dmg, actor.getStatInfo(StatImpl.StatName.HP));

        // Negative damage used for healing spots
        trapTile.setDamage(-dmg);
        hp = actor.getStatInfo(StatImpl.StatName.HP);
        trapTile.getEffect().applyEffect(actor);
        assertEquals(hp + dmg, actor.getStatInfo(StatImpl.StatName.HP));
    }

    /**
     * Method under test: {@link FloorTrapTileImpl#setDamage(int)}
     */
    @Test
    @Disabled()
    void testSetDamage() {
        final Pair<Integer, Integer> pos = new Pair<>(2, 3);
        final Actor actor = new HeroImpl("testHero", pos, new SkillImpl(), new StatImpl());
        final FloorTrapTileImpl trapTile = new FloorTrapTileImpl(pos);

        trapTile.setDisableSuccessRate(-30);
        trapTile.interact();

        int hp = actor.getStatInfo(StatImpl.StatName.HP);
        trapTile.getEffect().applyEffect(actor);
        assertEquals(hp - DEFAULT_DMG, actor.getStatInfo(StatImpl.StatName.HP));

        trapTile.setDisableSuccessRate(1);
        trapTile.interact();
        hp = actor.getStatInfo(StatImpl.StatName.HP);
        trapTile.getEffect().applyEffect(actor);
        assertEquals(hp, actor.getStatInfo(StatImpl.StatName.HP));

        trapTile.setDisableSuccessRate(110);
        trapTile.interact();
        trapTile.getEffect().applyEffect(actor);
        assertEquals(hp, actor.getStatInfo(StatImpl.StatName.HP));
    }

    /**
     * Method under test: {@link FloorTrapTileImpl#setPostTriggered(Consumer)}
     */
    @Test
    @Disabled()
    void testSetPostTriggered() {
        final Pair<Integer, Integer> pos = new Pair<>(2, 3);
        final Actor actor = new HeroImpl("testHero", pos, new SkillImpl(), new StatImpl());
        final FloorTrapTileImpl trapTile = new FloorTrapTileImpl(pos);

        trapTile.setPostTriggered(FloorTrapTileImpl::interact);

        int hp = actor.getStatInfo(StatImpl.StatName.HP);
        trapTile.getEffect().applyEffect(actor);
        assertEquals(hp - DEFAULT_DMG, actor.getStatInfo(StatImpl.StatName.HP));
        
        hp = actor.getStatInfo(StatImpl.StatName.HP);
        trapTile.getEffect().applyEffect(actor);
        assertEquals(hp, actor.getStatInfo(StatImpl.StatName.HP));
    }
}

