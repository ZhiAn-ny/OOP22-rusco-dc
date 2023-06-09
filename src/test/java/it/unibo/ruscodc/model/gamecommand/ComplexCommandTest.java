package it.unibo.ruscodc.model.gamecommand;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import it.unibo.ruscodc.model.actors.Actor;
import it.unibo.ruscodc.model.actors.hero.Hero;
import it.unibo.ruscodc.model.actors.hero.HeroSkill;
import it.unibo.ruscodc.model.actors.hero.HeroStat;
import it.unibo.ruscodc.model.actors.monster.Monster;
import it.unibo.ruscodc.model.actors.monster.MonsterGenerator;
import it.unibo.ruscodc.model.actors.monster.MonsterGeneratorImpl;
import it.unibo.ruscodc.model.actors.stat.StatImpl.StatName;
import it.unibo.ruscodc.model.effect.Effect;
import it.unibo.ruscodc.model.effect.EffectAbs;
import it.unibo.ruscodc.model.gamecommand.iacommand.IAAttack;
import it.unibo.ruscodc.model.gamecommand.playercommand.PlayerAttack;
import it.unibo.ruscodc.model.gamemap.RectangleRoomImpl;
import it.unibo.ruscodc.model.gamemap.Room;
import it.unibo.ruscodc.model.range.Range;
import it.unibo.ruscodc.model.range.SingleRange;
import it.unibo.ruscodc.model.range.SingleSplash;
import it.unibo.ruscodc.model.range.SquareRange;
import it.unibo.ruscodc.utils.GameControl;
import it.unibo.ruscodc.utils.Pair;
import it.unibo.ruscodc.utils.Pairs;
import it.unibo.ruscodc.utils.exception.ModelException;
import it.unibo.ruscodc.utils.exception.Undo;
import it.unibo.ruscodc.utils.outputinfo.InfoPayload;
import it.unibo.ruscodc.utils.outputinfo.InfoPayloadImpl;

@TestInstance(Lifecycle.PER_CLASS)
class ComplexCommandTest {

    private static final String ERR_MUST_PROD = "A result must be producted";
    private static final String ERR_GAME_FLOW = "No interuption of normal game flow";
    private static final String ERR_UPDATE_VIEW = "This movement is legal and must update view!";

    private static final int DAMAGE = 4;

    private static final int SIZE = 21;

    private static final Range B_R = new SingleRange();
    private static final Range B_S = new SingleSplash();
    private static final Effect B_ATT = new EffectAbs(0) {
        /**
         * 
         */
        @Override
        public void applyEffect(final Actor from, final Actor to) {
            to.modifyActualStat(StatName.HP, -DAMAGE);
        }
    }; 

    private static final String ERR_TITLE = "ERR TITLE";
    private static final String ERR_DESCR = "ERR DESCR";
    private static final InfoPayload ERR_INFO = new InfoPayloadImpl(ERR_TITLE, ERR_DESCR);


    private static final Pair<Integer, Integer> POS = new Pair<>(SIZE / 2, SIZE / 2);
    private final Supplier<Room> roomSupp = () -> new RectangleRoomImpl(SIZE, SIZE);
    private final Supplier<Actor> heroSupp = () -> new HeroImplTest("test", POS, new TestSkill(), new HeroStat());
    private final List<Actor> monsters = new ArrayList<>();

    private void setCommand(final GameCommand toSet, final Actor toMove, final Room where) {
        toSet.setActor(toMove);
        toSet.setRoom(where);
        monsters.forEach(m -> where.addMonster((Monster) m));
    }

    private void setHandlableCommand(final GameCommand toSet, final Actor toMove, final Room where) {
        setCommand(toSet, toMove, where);
        // need to invoke these command before other cause for their initialization
        toSet.isReady();
        toSet.getEntities();
        // (and logically these command must to be printed on view before player interact with it)
    }

    private void setIACommand(final GameCommand toSet, final Actor toMove, final Room where, final List<Actor> targets) {
        setCommand(toSet, toMove, where);
        toSet.setCursorPos(POS);
        toSet.setTarget(targets);

    }

    @BeforeAll
    void init() {
        final MonsterGenerator mFactory = new MonsterGeneratorImpl();
        final Pair<Integer, Integer> tmp = Pairs.computeUpPair(POS);
        final Pair<Integer, Integer> pos1 = Pairs.computeLeftPair(tmp);
        final Pair<Integer, Integer> pos2 = Pairs.computeRightPair(tmp);
        final Pair<Integer, Integer> pos3 = Pairs.computeRightPair(Pairs.computeDownPair(POS));
        final List<Pair<Integer, Integer>> poss = List.of(pos1, pos2, pos3);


        monsters.addAll(poss.stream().map(p -> mFactory.makeMeleeRat(p)).toList());
    }
    //private final 

    @Test
    void testPlayerAttack() {
        final Actor hero = heroSupp.get();
        final Room room = roomSupp.get();
        final Optional<GameCommand> tmp  = ((Hero) hero).act(GameControl.BASEATTACK);
        assertNotEquals(Optional.empty(), tmp, "BaseAttack of a hero cannot be avoid!");
        final GameCommand baseAttack = tmp.get();
        setHandlableCommand(baseAttack, hero, room);

        final List<GameControl> movement = List.of(GameControl.MOVEUP, GameControl.MOVELEFT);
        final Pair<Integer, Integer> targetPos = Pairs.computeUpPair(Pairs.computeRightPair(POS));

        for (final GameControl input : movement) {
            assertTrue(() -> baseAttack.modify(input), "These movement are legal and must update view!");
        }

        Optional<InfoPayload> res1;
        try {
            res1 = baseAttack.execute();
        } catch (final ModelException e) {
            fail("This execution not alter the flow of the game!");
            res1 = Optional.empty();
        }
        assertEquals(Optional.empty(), res1, "This attack is legal!");
        //assertDoesNotThrow(() -> {}, "Cursor is in range, so attack must be complete with successfull");

        //monsters.forEach(m -> System.out.println(m.getPos() + "  " + m.getStats().getStatActual(StatName.HP)));

        final Optional<Actor> targed = monsters.stream().filter(m -> m.getPos().equals(targetPos)).findFirst();
        assertNotEquals(Optional.empty(), targed, "This monster must be spawned!");
        final Optional<Actor> other = monsters.stream().filter(m -> !m.getPos().equals(targetPos)).findFirst();
        assertNotEquals(Optional.empty(), other, "Spawned more than this monster");

        final int a = targed.get().getStatActual(StatName.HP);
        final int b = other.get().getStatActual(StatName.HP);
        assertEquals(Math.abs(a - b), DAMAGE, "attack not take by enemy");

        assertTrue(baseAttack.modify(GameControl.MOVEUP), "This input is legal, it must upload the view");
        assertTrue(baseAttack.modify(GameControl.MOVEUP), "This input is legal, it must upload the view");
        Optional<InfoPayload> res2;
        try {
            res2 = baseAttack.execute();
        } catch (final ModelException e) {
            fail("This execution not alter the flow of the game!");
            res2 = Optional.empty();
        }
        assertNotEquals(Optional.empty(), res2, "This attack is not legal, cause cursor is out of range!");

    }

    private static class TestSkill extends HeroSkill {

        TestSkill() {
            super();

            super.setAction(GameControl.BASEATTACK, new PlayerAttack(
                new SquareRange(1, B_R), 
                B_S,
                B_ATT));
        }
    }

    @Test
    void testInteract() {
        final Actor hero = heroSupp.get();
        final Room room = roomSupp.get();
        final Optional<GameCommand> tmp  = ((Hero) hero).act(GameControl.INTERACT);
        assertNotEquals(Optional.empty(), tmp, "A hero can interact with room!");
        final GameCommand interact = tmp.get();
        setHandlableCommand(interact, hero, room);

        final List<GameControl> movement = List.of(GameControl.MOVEUP, GameControl.MOVELEFT);
        //final Pair<Integer, Integer> targetPos = Pairs.computeUpPair(Pairs.computeRightPair(POS));

        for (final GameControl input : movement) {
            assertTrue(() -> interact.modify(input), ERR_UPDATE_VIEW);
        }

        Optional<InfoPayload> res; //TODO

        try {
            res = interact.execute();
        } catch (final ModelException e) {
            fail(ERR_GAME_FLOW);
            res = Optional.empty();
        }

        assertNotEquals(Optional.empty(), res, ERR_MUST_PROD);
        assertNotEquals(res.get().getPath(), ERR_INFO.getPath(), "This info must not be a warning");




        assertTrue(() -> interact.modify(GameControl.MOVERIGHT), ERR_UPDATE_VIEW);
        try {
            res = interact.execute();
        } catch (final ModelException e) {
            fail(ERR_GAME_FLOW);
            res = Optional.empty();
        }

        assertNotEquals(Optional.empty(), res, ERR_MUST_PROD);
        assertEquals(res.get().getPath(), ERR_INFO.getPath(), "This info must be a warning");




        assertTrue(() -> interact.modify(GameControl.MOVEUP), ERR_UPDATE_VIEW);
        try {
            res = interact.execute();
        } catch (final ModelException e) {
            fail(ERR_GAME_FLOW);
            res = Optional.empty();
        }

        assertNotEquals(Optional.empty(), res, ERR_MUST_PROD);
        assertEquals(res.get().getPath(), ERR_INFO.getPath(), "This info must be a warning");
    }

    @Test
    void testOpenInventory() {
        final Actor hero = heroSupp.get();
        final Room room = roomSupp.get();
        final Optional<GameCommand> tmp  = ((Hero) hero).act(GameControl.INVENTORY);
        assertNotEquals(Optional.empty(), tmp, "A hero can open his inventory!");

        final int delta = hero.getStatMax(StatName.HP) / 2;
        hero.modifyActualStat(StatName.HP, -delta);
        final int startHp = hero.getStatActual(StatName.HP);

        final GameCommand open = tmp.get();
        setHandlableCommand(open, hero, room);

        final List<GameControl> movement = List.of(GameControl.MOVERIGHT, GameControl.MOVERIGHT);
        //final Pair<Integer, Integer> targetPos = Pairs.computeUpPair(Pairs.computeRightPair(POS));

        for (final GameControl input : movement) {
            assertTrue(() -> open.modify(input), ERR_UPDATE_VIEW);
        }

        Optional<InfoPayload> res; //TODO

        try {
            res = open.execute();
        } catch (final ModelException e) {
            fail(ERR_GAME_FLOW);
            res = Optional.empty();
        }

        assertEquals(Optional.empty(), res, "A result must not be producted");
        //assertNotEquals(res.get().getPath(), ERR_INFO.getPath(), "This info must not be a warning");

        assertFalse(() -> open.modify(GameControl.INVENTORY), "This input is legal but it could not update view!");
        try {
            res = open.execute();
        } catch (final ModelException e) {
            fail(ERR_GAME_FLOW);
            res = Optional.empty();
        }

        assertNotEquals(Optional.empty(), res, ERR_MUST_PROD);
        assertNotEquals(res.get().getPath(), ERR_INFO.getPath(), "This info must not be a warning");

        assertTrue(() -> open.modify(GameControl.CONFIRM), "This input is legal and it could update view!");
        assertTrue(hero.getStatActual(StatName.HP) > startHp, "Hero have drunk an heal potion!");

        //assertNotEquals(Optional.empty(), res, "A result must be producted");
        //assertNotEquals(res.get().getPath(), ERR_INFO.getPath(), "This info must not be a warning");
    }

    @Test
    void testIAAttack() {
        final Actor hero = heroSupp.get();
        final Room room = roomSupp.get();
        final GameCommand myIAAttack = new IAAttack(
            new SquareRange(1, B_R),
            B_S, 
            B_ATT);
        final Optional<Monster> tmp = monsters.stream()
            .peek(m -> setIACommand(myIAAttack, m, room, List.of(hero)))
            .filter(m -> myIAAttack.isTargetInRange(hero))
            .findFirst()
            .map(who -> (Monster) who);

        assertNotEquals(Optional.empty(), tmp, "At least a monster could target the hero!");
        final Monster from = tmp.get();
        assertTrue(from.getStatActual(StatName.AP) >= myIAAttack.getAPCost(), "Attack does not cost any AP");

        Optional<InfoPayload> res; //TODO

        try {
            res = myIAAttack.execute();
        } catch (final ModelException e) {
            fail(ERR_GAME_FLOW);
            res = Optional.empty();
        }

        assertEquals(Optional.empty(), res, "A result must not be producted");

    }


    private void abortCommand(final GameCommand toAbort) {
        final GameControl abort = GameControl.CANCEL;
        assertFalse(toAbort.modify(abort), "After abort, the command could not update view");
        assertThrows(Undo.class, () -> toAbort.execute());
    }

    @Test
    void abortPlayerAttack() {
        final Actor hero = heroSupp.get();
        final Room room = roomSupp.get();
        final Optional<GameCommand> tmp  = ((Hero) hero).act(GameControl.BASEATTACK);
        assertNotEquals(Optional.empty(), tmp, "BaseAttack of a hero cannot be avoid!");
        final GameCommand baseAttack = tmp.get();
        setHandlableCommand(baseAttack, hero, room);
        abortCommand(baseAttack);
    }

    @Test
    void abortInteract() {
        final Actor hero = heroSupp.get();
        final Room room = roomSupp.get();
        final Optional<GameCommand> tmp  = ((Hero) hero).act(GameControl.BASEATTACK);
        assertNotEquals(Optional.empty(), tmp, "A hero can interact with room!");
        final GameCommand interact = tmp.get();
        setHandlableCommand(interact, hero, room);
        abortCommand(interact);
    }

    @Test
    void abortOpenInventory() {
        final Actor hero = heroSupp.get();
        final Room room = roomSupp.get();
        final Optional<GameCommand> tmp  = ((Hero) hero).act(GameControl.INVENTORY);
        assertNotEquals(Optional.empty(), tmp, "A hero can open his inventory!");
        final GameCommand open = tmp.get();
        setHandlableCommand(open, hero, room);
        abortCommand(open);
    }

}


