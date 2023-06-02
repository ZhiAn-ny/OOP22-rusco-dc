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

import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;


class FloorTrapTileImplTest {
    private static final int DEFAULT_DMG = 5;

    private Actor getActor(final Pair<Integer, Integer> pos) {
        final StatFactory stats = new StatFactoryImpl();
        final MonsterActionFactory monsterActionFactory = new MonsterActionFactoryImpl();
        final Skill skills = new SkillImpl();
        skills.setAction(GameControl.ATTACK1, monsterActionFactory.basicMeleeAttack());
        skills.setAction(GameControl.ATTACK2, monsterActionFactory.heavyMeleeAttack());
        return new HeroImpl("testHero", pos, skills, stats.ratStat());
    }

    /**
     * Method under test: default or parameterless constructor of {@link FloorTrapTileImpl}.
     */
    @Test
    void testConstructor() {
        final Pair<Integer, Integer> pos = new Pair<>(2, 3);
        final FloorTrapTileImpl trapTile = new FloorTrapTileImpl(pos);

        assertEquals(pos, trapTile.getPosition());
        assertTrue(trapTile.isTrap());
        assertTrue(trapTile.isAccessible());
        assertTrue(trapTile.get().isPresent());
        assertEquals("It's a trap!", trapTile.getName());
        assertEquals("file:src/main/resources/it/unibo/ruscodc/map_res/FloorTile/trap", trapTile.getPath());
    }

    /**
     * Method under test:  {@link FloorTrapTileImpl#isAccessible()}.
     */
    @Test
    void testAccessibility() {
        final FloorTrapTileImpl trapTile = new FloorTrapTileImpl(new Pair<>(2, 3));

        assertTrue(trapTile.isAccessible());
        assertTrue(trapTile.isTransitable());
    }

    /**
     * Methods under test: {@link FloorTileImpl#FloorTileImpl(Pair, boolean)}.
     */
    @Test
    void testConstructorNullPosition() {
        assertThrows(IllegalArgumentException.class, () -> new FloorTrapTileImpl(null));
    }

    /**
     * Methods under test: {@link FloorTrapTileImpl#get()}.
     */
    @Test
    void testGet() {
        final Pair<Integer, Integer> pos = new Pair<>(2, 3);
        final FloorTrapTileImpl trapTile = new FloorTrapTileImpl(pos);

        assertTrue(trapTile.get().isPresent());
        assertEquals(trapTile, trapTile.get().get());
    }

    /**
     * Methods under test: {@link FloorTrapTileImpl#get()}.
     */
    @Test
    void testGetAfterEmpty() {
        final Pair<Integer, Integer> pos = new Pair<>(2, 3);
        final FloorTrapTileImpl trapTile = new FloorTrapTileImpl(pos);

        trapTile.empty();
        assertTrue(trapTile.get().isPresent());
        assertEquals(trapTile, trapTile.get().get());
    }

    /**
     * Method under test: {@link FloorTrapTileImpl#put(Interactable)}.
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
     * Method under test: {@link FloorTrapTileImpl#getEffect()}.
     */
    @Test
    void testGetEffect() {
        final Pair<Integer, Integer> pos = new Pair<>(2, 3);
        final FloorTrapTileImpl trapTile = new FloorTrapTileImpl(pos);
        final Actor actor = this.getActor(pos);
        final int hp = actor.getStatActual(StatImpl.StatName.HP);

        trapTile.getEffect().applyEffect(actor);
        assertEquals(hp - DEFAULT_DMG, actor.getStatActual(StatImpl.StatName.HP));
    }

    /**
     * Method under test: {@link FloorTrapTileImpl#getEffect()}.
     */
    @Test
    void testGetEffectMultipleTimes() {
        final Pair<Integer, Integer> pos = new Pair<>(2, 3);
        final FloorTrapTileImpl trapTile = new FloorTrapTileImpl(pos);
        final Actor actor = this.getActor(pos);

        for (int i = 0; i < 3; i++) {
            final int hp = actor.getStatActual(StatImpl.StatName.HP);
            trapTile.getEffect().applyEffect(actor);
            assertEquals(hp - DEFAULT_DMG, actor.getStatActual(StatImpl.StatName.HP));
        }
    }

    /**
     * Method under test: {@link FloorTrapTileImpl#interact()}.
     */
    @Test
    void testInteract() {
        final Pair<Integer, Integer> pos = new Pair<>(2, 3);
        final FloorTrapTileImpl trapTile = new FloorTrapTileImpl(pos);
        final Actor actor = this.getActor(pos);
        final int hp = actor.getStatActual(StatImpl.StatName.HP);

        trapTile.interact();
        trapTile.getEffect().applyEffect(actor);

        assertEquals(hp, actor.getStatActual(StatImpl.StatName.HP));
    }

    /**
     * Method under test: {@link FloorTrapTileImpl#interact()}.
     */
    @Test
    void testInteractDoubleInteraction() {
        final Pair<Integer, Integer> pos = new Pair<>(2, 3);
        final FloorTrapTileImpl trapTile = new FloorTrapTileImpl(pos);
        final Actor actor = this.getActor(pos);
        final int hp = actor.getStatActual(StatImpl.StatName.HP);

        trapTile.interact();
        trapTile.interact();
        trapTile.getEffect().applyEffect(actor);

        assertEquals(hp, actor.getStatActual(StatImpl.StatName.HP));
    }

    /**
     * Method under test: {@link FloorTrapTileImpl#setDisableSuccessRate(int)}.
     */
    @Test
    void testSetDisableSuccessRateInRange() {
        final Pair<Integer, Integer> pos = new Pair<>(2, 3);
        final Actor actor = this.getActor(pos);
        final FloorTrapTileImpl trapTile = new FloorTrapTileImpl(pos);
        final int hp = actor.getStatActual(StatImpl.StatName.HP);

        trapTile.setDisableSuccessRate(1);
        trapTile.interact();
        trapTile.getEffect().applyEffect(actor);

        assertEquals(hp - DEFAULT_DMG, actor.getStatActual(StatImpl.StatName.HP));
    }

    /**
     * Method under test: {@link FloorTrapTileImpl#setDisableSuccessRate(int)}.
     */
    @Test
    void testSetDisableSuccessRateAfterInteraction() {
        final Pair<Integer, Integer> pos = new Pair<>(2, 3);
        final Actor actor = this.getActor(pos);
        final FloorTrapTileImpl trapTile = new FloorTrapTileImpl(pos);
        final int hp = actor.getStatActual(StatImpl.StatName.HP);

        trapTile.interact();
        trapTile.setDisableSuccessRate(1);
        trapTile.getEffect().applyEffect(actor);

        assertEquals(hp, actor.getStatActual(StatImpl.StatName.HP));
    }

    /**
     * Method under test: {@link FloorTrapTileImpl#setDisableSuccessRate(int)}.
     */
    @Test
    void testSetDisableSuccessRateOutOfRange() {
        final Pair<Integer, Integer> pos = new Pair<>(2, 3);
        final Actor actor = this.getActor(pos);
        final FloorTrapTileImpl trapTile = new FloorTrapTileImpl(pos);
        final int upperOutOfBound = 130;
        final int lowerOutOfBound = -15;
        int hp = actor.getStatActual(StatImpl.StatName.HP);

        trapTile.setDisableSuccessRate(1);
        trapTile.setDisableSuccessRate(upperOutOfBound);
        trapTile.interact();
        trapTile.getEffect().applyEffect(actor);
        assertEquals(hp - DEFAULT_DMG, actor.getStatActual(StatImpl.StatName.HP));

        hp = actor.getStatActual(StatImpl.StatName.HP);
        trapTile.setDisableSuccessRate(100);
        trapTile.setDisableSuccessRate(lowerOutOfBound);
        trapTile.interact();
        trapTile.getEffect().applyEffect(actor);
        assertEquals(hp, actor.getStatActual(StatImpl.StatName.HP));
    }

    /**
     * Method under test: {@link FloorTrapTileImpl#setDamage(int)}.
     */
    @Test
    void testSetDamage() {
        final Pair<Integer, Integer> pos = new Pair<>(2, 3);
        final Actor actor = this.getActor(pos);
        final FloorTrapTileImpl trapTile = new FloorTrapTileImpl(pos);
        final int hp = actor.getStatActual(StatImpl.StatName.HP);
        final int newDmg = 30;

        trapTile.setDamage(newDmg);
        trapTile.getEffect().applyEffect(actor);

        assertEquals(hp - newDmg, actor.getStatActual(StatImpl.StatName.HP));
    }

    /**
     * Method under test: {@link FloorTrapTileImpl#setPostTriggered(Consumer)}.
     */
    @Test
    void testSetPostTriggered() {
        final Pair<Integer, Integer> pos = new Pair<>(2, 3);
        final Actor actor = this.getActor(pos);
        final FloorTrapTileImpl trapTile = new FloorTrapTileImpl(pos);
        int hp = actor.getStatActual(StatImpl.StatName.HP);

        trapTile.setPostTriggered(FloorTrapTileImpl::interact);

        trapTile.getEffect().applyEffect(actor);
        assertEquals(hp - DEFAULT_DMG, actor.getStatActual(StatImpl.StatName.HP));

        hp = actor.getStatActual(StatImpl.StatName.HP);
        trapTile.getEffect().applyEffect(actor);
        assertEquals(hp, actor.getStatActual(StatImpl.StatName.HP));
    }
}

