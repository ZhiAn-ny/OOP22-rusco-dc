package it.unibo.ruscodc.model.gamemap;

import it.unibo.ruscodc.model.actors.Actor;
import it.unibo.ruscodc.model.actors.hero.HeroImpl;
import it.unibo.ruscodc.model.actors.monster.MonsterActionFactory;
import it.unibo.ruscodc.model.actors.monster.MonsterActionFactoryImpl;
import it.unibo.ruscodc.model.actors.skill.Skill;
import it.unibo.ruscodc.model.actors.skill.SkillImpl;
import it.unibo.ruscodc.model.actors.stat.StatFactory;
import it.unibo.ruscodc.model.actors.stat.StatFactoryImpl;
import it.unibo.ruscodc.model.actors.stat.StatImpl;
import it.unibo.ruscodc.model.interactable.Chest;
import it.unibo.ruscodc.model.interactable.Interactable;
import it.unibo.ruscodc.utils.GameControl;
import it.unibo.ruscodc.utils.Pair;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.*;

class FloorTrapTileImplTest {
    private static final int DEFAULT_DMG = 5;

    private Actor getActor(Pair<Integer, Integer> pos) {
        final StatFactory stats = new StatFactoryImpl();
        final MonsterActionFactory MAFactory = new MonsterActionFactoryImpl();
        Skill skills = new SkillImpl();
        skills.setAction(GameControl.ATTACK1, MAFactory.basicMeleeAttack());
        skills.setAction(GameControl.ATTACK2, MAFactory.heavyMeleeAttack());
        return new HeroImpl("testHero", pos, skills, stats.ratStat());
    }
    
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
    void testGetEffect() {
        final Pair<Integer, Integer> pos = new Pair<>(2, 3);
        final FloorTrapTileImpl trapTile = new FloorTrapTileImpl(pos);
        final Actor actor = this.getActor(pos);

        int hp = actor.getStatInfo(StatImpl.StatName.HP);
        trapTile.getEffect().applyEffect(actor);
        assertEquals(hp - DEFAULT_DMG, actor.getStatInfo(StatImpl.StatName.HP));
    }

    /**
     * Method under test: {@link FloorTrapTileImpl#interact()}
     */
    @Test
    void testInteract() {
        final Pair<Integer, Integer> pos = new Pair<>(2, 3);
        final FloorTrapTileImpl trapTile = new FloorTrapTileImpl(pos);
        final Actor actor = this.getActor(pos);

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
    void testSetDisableSuccessRate() {
        final Pair<Integer, Integer> pos = new Pair<>(2, 3);
        final Actor actor = this.getActor(pos);
        final FloorTrapTileImpl trapTile = new FloorTrapTileImpl(pos);

        trapTile.setDisableSuccessRate(1);
        trapTile.interact();

        int hp = actor.getStatInfo(StatImpl.StatName.HP);
        trapTile.getEffect().applyEffect(actor);
        assertEquals(hp - DEFAULT_DMG, actor.getStatInfo(StatImpl.StatName.HP));

        hp = actor.getStatInfo(StatImpl.StatName.HP);
        trapTile.getEffect().applyEffect(actor);
        assertEquals(hp - DEFAULT_DMG, actor.getStatInfo(StatImpl.StatName.HP));

        trapTile.setDisableSuccessRate(100);
        trapTile.interact();
        hp = actor.getStatInfo(StatImpl.StatName.HP);
        trapTile.getEffect().applyEffect(actor);
        assertEquals(hp, actor.getStatInfo(StatImpl.StatName.HP));

        // trap already disabled
        trapTile.setDisableSuccessRate(1);
        trapTile.interact();
        trapTile.getEffect().applyEffect(actor);
        assertEquals(hp, actor.getStatInfo(StatImpl.StatName.HP));
    }

    /**
     * Method under test: {@link FloorTrapTileImpl#setDisableSuccessRate(int)}
     */
    @Test
    void testSetDisableSuccessRate2() {
        final Pair<Integer, Integer> pos = new Pair<>(2, 3);
        final Actor actor = this.getActor(pos);
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
    void testSetDamage() {
        final Pair<Integer, Integer> pos = new Pair<>(2, 3);
        final Actor actor = this.getActor(pos);
        FloorTrapTileImpl trapTile = new FloorTrapTileImpl(pos);
        int hp = actor.getStatInfo(StatImpl.StatName.HP);

        // Out of range -> ignored, default set to 100
        trapTile.setDisableSuccessRate(-30);
        trapTile.interact();
        trapTile.getEffect().applyEffect(actor);
        assertEquals(hp, actor.getStatInfo(StatImpl.StatName.HP));

        trapTile = new FloorTrapTileImpl(pos);
        trapTile.setDisableSuccessRate(1);
        trapTile.interact();
        hp = actor.getStatInfo(StatImpl.StatName.HP);
        trapTile.getEffect().applyEffect(actor);
        assertEquals(hp - DEFAULT_DMG, actor.getStatInfo(StatImpl.StatName.HP));

        // Out of range -> ignored
        trapTile.setDisableSuccessRate(110);
        hp = actor.getStatInfo(StatImpl.StatName.HP);
        trapTile.interact();
        trapTile.getEffect().applyEffect(actor);
        assertEquals(hp - DEFAULT_DMG, actor.getStatInfo(StatImpl.StatName.HP));
    }

    /**
     * Method under test: {@link FloorTrapTileImpl#setPostTriggered(Consumer)}
     */
    @Test
    void testSetPostTriggered() {
        final Pair<Integer, Integer> pos = new Pair<>(2, 3);
        final Actor actor = this.getActor(pos);
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

