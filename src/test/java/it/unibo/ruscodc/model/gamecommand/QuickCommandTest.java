package it.unibo.ruscodc.model.gamecommand;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Optional;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import it.unibo.ruscodc.model.actors.hero.Hero;
import it.unibo.ruscodc.model.item.Item;
import it.unibo.ruscodc.model.item.consumable.ConsumableFactoryImpl;
import it.unibo.ruscodc.model.item.consumable.ConsumableFactory;
import it.unibo.ruscodc.utils.outputinfo.InfoPayload;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import it.unibo.ruscodc.model.actors.Actor;
import it.unibo.ruscodc.model.actors.hero.HeroImpl;
import it.unibo.ruscodc.model.actors.hero.HeroSkill;
import it.unibo.ruscodc.model.actors.hero.HeroStat;
import it.unibo.ruscodc.model.actors.stat.StatImpl.StatName;
import it.unibo.ruscodc.model.effect.Effect;
import it.unibo.ruscodc.model.effect.EffectAbs;
import it.unibo.ruscodc.model.gamecommand.iacommand.IAAttack;
import it.unibo.ruscodc.model.gamecommand.playercommand.PlayerAttack;
import it.unibo.ruscodc.model.gamecommand.quickcommand.ChangeFloor;
import it.unibo.ruscodc.model.gamecommand.quickcommand.ChangeRoom;
import it.unibo.ruscodc.model.gamecommand.quickcommand.MoveDownCommand;
import it.unibo.ruscodc.model.gamecommand.quickcommand.MoveLeftCommand;
import it.unibo.ruscodc.model.gamecommand.quickcommand.MoveRightCommand;
import it.unibo.ruscodc.model.gamecommand.quickcommand.MoveUpCommand;
import it.unibo.ruscodc.model.gamecommand.quickcommand.FillInventory;
import it.unibo.ruscodc.model.gamemap.RectangleRoomImpl;
import it.unibo.ruscodc.model.gamemap.Room;
import it.unibo.ruscodc.model.range.Range;
import it.unibo.ruscodc.model.range.SingleRange;
import it.unibo.ruscodc.model.range.SingleSplash;
import it.unibo.ruscodc.utils.GameControl;
import it.unibo.ruscodc.utils.Pair;
import it.unibo.ruscodc.utils.Pairs;
import it.unibo.ruscodc.utils.exception.ChangeFloorException;
import it.unibo.ruscodc.utils.exception.ChangeRoomException;
import it.unibo.ruscodc.utils.exception.ModelException;

/**
 * Test class for Commands.
 */
@TestInstance(Lifecycle.PER_CLASS)
final class QuickCommandTest {

    private static final int SIZE = 21;

    private static final Range B_R = new SingleRange();
    private static final Range B_S = new SingleSplash();
    private static final Effect B_ATT = new EffectAbs(0) {
        /**
         * 
         */
        @Override
        public void applyEffect(final Actor from, final Actor to) {
            final int damage = from.getStatActual(StatName.STR);
            to.modifyActualStat(StatName.HP, -damage);
        }
    }; 


    private static final Pair<Integer, Integer> POS = new Pair<>(SIZE / 2, SIZE / 2);
    private final Room room = new RectangleRoomImpl(SIZE, SIZE);
    private final Supplier<Actor> heroSupp = () -> new HeroImpl("test", POS, new HeroSkill(), new HeroStat());

    private void setCommand(final GameCommand toSet, final Actor toMove) {
        toSet.setActor(toMove);
        toSet.setRoom(room);
    }

    /**
     * On a quick command, you can call methods only from the GameCommand interface.
     */
    @Test
    void checkQuick() {
        final Actor hero = heroSupp.get();
        final GameCommand toTest = new MoveDownCommand();
        setCommand(toTest, hero);
        assertThrows(UnsupportedOperationException.class, () -> toTest.modify(GameControl.MOVEUP));
    }

    /**
     * On a Handlable command you cannot call a method of IA.
     */
    @Test
    void checkComplexHandable() {
        final Actor hero = heroSupp.get();
        final GameCommand toTest = new PlayerAttack(B_R, B_S, B_ATT);
        setCommand(toTest, hero);
        assertThrows(UnsupportedOperationException.class, () -> toTest.setCursorPos(POS));
    }

    /**
     * On a IA command you cannot call a method of Handable.
     */
    @Test
    void checkComplexIA() {
        final Actor hero = heroSupp.get();
        final GameCommand toTest = new IAAttack(B_R, B_S, B_ATT);
        setCommand(toTest, hero);
        assertThrows(UnsupportedOperationException.class, () -> toTest.modify(GameControl.MOVEUP));
    }

    private void checkMov(final GameCommand movementToTest, final Pair<Integer, Integer> resultPos) {
        final Actor hero = heroSupp.get();
        setCommand(movementToTest, hero);

        assertNotEquals(resultPos, hero.getPos(), "ERR: command not yet executed!");

        try {
            movementToTest.execute();
        } catch (final ModelException e) {
            fail("ERR: gamecommand not setted right!");
        }

        assertEquals(resultPos, hero.getPos(), "ERR: wrong movement!");
    }

    /**
     * Test moving up.
     */
    @Test
    void checkMoveUp() {
        checkMov(new MoveUpCommand(), Pairs.computeUpPair(POS));
    }

    /**
     * Test moving down.
     */
    @Test
    void checkMoveDown() {
        checkMov(new MoveDownCommand(), Pairs.computeDownPair(POS));
    }

    /**
     * Test moving left.
     */
    @Test
    void checkMoveLeft() {
        checkMov(new MoveLeftCommand(), Pairs.computeLeftPair(POS));
    }

    /**
     * Test moving right.
     */
    @Test
    void checkMoveRight() {
        checkMov(new MoveRightCommand(), Pairs.computeRightPair(POS));
    }

    private void checkAlterContest(final GameCommand alterToTest) {
        final Actor hero = heroSupp.get();
        setCommand(alterToTest, hero);

        assertThrows(ModelException.class, () -> alterToTest.execute());
    }

    /**
     * Test if the executing of "ChangeRoom" command effectly advise the caller with a ModelExcelpion.
     * and check if that exception is an "ChangeRoomException".
     */
    @Test
    void checkChangeRoom() {
        final GameCommand toTest = new ChangeRoom(null);
        checkAlterContest(toTest);
        assertThrows(ChangeRoomException.class, () -> toTest.execute());
    }

    /**
     * Test if the executing of "ChangeRoom" command effectly advise the caller with a ModelExcelpion.
     * and check if that exception is an "ChangeFloorException".
     */
    @Test
    void checkChangeFloor() {
        final GameCommand toTest = new ChangeFloor();
        checkAlterContest(toTest);
        assertThrows(ChangeFloorException.class, () -> toTest.execute());
    }

    /**
     * Test if the Hero's inventory actual fills.
     * Credit Luca Bandiera.
     */
    @Test
    void checkFillInveroty() {
        final ConsumableFactory itemGen = new ConsumableFactoryImpl();
        final int amount = 7;
        final Set<Item> dropped = Stream.generate(() -> itemGen.createAPotion()).limit(amount).collect(Collectors.toSet());
        final GameCommand fill1 = new FillInventory(dropped);
        final Hero hero = (Hero) heroSupp.get();
        setCommand(fill1, hero);
        Optional<InfoPayload> res;
        try {
            res = fill1.execute();
        } catch (ModelException e) {
            fail("Typically this command doesn't throw ModelException");
            res = Optional.empty();
        }
        assertEquals(Optional.empty(), res, "Items must be added to an empty inventory!");

        while (!hero.getInventory().isFull()) {
            hero.getInventory().addItem(itemGen.createAPotion());
        }

        final GameCommand fill2 = new FillInventory(dropped);
        setCommand(fill2, hero);
        try {
            res = fill2.execute();
        } catch (ModelException e) {
            fail("Typically this command doesn't throw ModelException");
            res = Optional.empty();
        }
        assertNotEquals(Optional.empty(), res, "Items should NOT be added to a full inventory");
    }

}
