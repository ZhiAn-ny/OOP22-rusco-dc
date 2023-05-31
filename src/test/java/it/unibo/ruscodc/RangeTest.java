package it.unibo.ruscodc;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

import it.unibo.ruscodc.model.actors.Actor;
import it.unibo.ruscodc.model.actors.monster.MonsterGenerator;
import it.unibo.ruscodc.model.actors.monster.MonsterGeneratorImpl;
import it.unibo.ruscodc.model.gamemap.RectangleRoomImpl;
import it.unibo.ruscodc.model.gamemap.Room;
import it.unibo.ruscodc.model.range.CircleRange;
import it.unibo.ruscodc.model.range.ConeRange;
import it.unibo.ruscodc.model.range.GlobalRange;
import it.unibo.ruscodc.model.range.Range;
import it.unibo.ruscodc.model.range.SingleRange;
import it.unibo.ruscodc.utils.MyIterator;
import it.unibo.ruscodc.utils.Pair;
import it.unibo.ruscodc.utils.Pairs;

/**
 * Test class for Range.
 */
@TestInstance(Lifecycle.PER_CLASS)
final class RangeTest {

    private final static String ERR_M = "ERR";
    private final Range basicR = new SingleRange();
    private final List<Actor> enemys = new ArrayList<>();
    private Pair<Integer, Integer> heroPos;
    private Pair<Integer, Integer> cursorPos;
    private static final int ROOM_SIZE = 21;
    private static final int BASIC_R_SIZE = 2;
    private final Room r = new RectangleRoomImpl(ROOM_SIZE, ROOM_SIZE);

    private final MyIterator<Integer> counter = 
        new MyIterator<>(Stream.iterate(0, i -> i + 1).iterator());

    private final Pair<Integer, Integer> startPos = new Pair<>(10, 10);

    private final MonsterGenerator mg = new MonsterGeneratorImpl();

    private Supplier<Stream<Pair<Integer, Integer>>> enemyPos;



    /**
     * Init some variable before start tests.
     */
    @BeforeAll
    void init() {
        final Pair<Integer, Integer> pos1 = Pairs.computeDownPair(Pairs.computeRightPair(startPos)); 
        final Pair<Integer, Integer> pos2 = Pairs.computeUpPair(Pairs.computeRightPair(startPos));
        final Pair<Integer, Integer> pos3 = Pairs.computeUpPair(Pairs.computeLeftPair(startPos));

        cursorPos = pos1;
        heroPos = startPos;

        final List<Pair<Integer, Integer>> positions = List.of(pos1, pos2, pos3);

        positions.forEach(p -> enemys.add(mg.makeMeleeRat(counter.next() + " " + p.toString(), p)));

        enemyPos = () -> enemys.stream().map(a -> a.getPos());

    }

    /**
     * Test focused to test the "range" concept.
     */
    @Test
    void testCircle() { 
        final Range myRange = new CircleRange(BASIC_R_SIZE, basicR);
        final long counted = enemyPos.get()
            .filter(ep -> myRange.isInRange(heroPos, cursorPos, ep, r)).count();
        assertEquals(3, counted, ERR_M);
    }

    @Test
    void testCircle2() { 
        final Range myRange = new CircleRange(BASIC_R_SIZE, basicR);
        myRange.getRange(heroPos, cursorPos, r).forEach(p -> System.out.println(p.getPos()));
        final Pair<Integer, Integer> farPos = new Pair<>(0, 0);
        enemys.add(mg.makeMeleeRat(counter.next() + " " + farPos.toString(), farPos));
        final long counted = enemyPos.get()
            .filter(ep -> 
            myRange.isInRange(heroPos, cursorPos, ep, r))
            .count();
        assertEquals(3, counted, ERR_M);
    }

    @Test
    void testGlobalRange() {
        final Range gR = new GlobalRange(basicR);
        final long counted = enemyPos.get()
            .filter(ep -> gR.isInRange(heroPos, cursorPos, ep, r)).count();
        assertEquals(3, counted, ERR_M);
    }

    @Test
    void testglobalRange2() { 
        final Range gR = new GlobalRange(basicR);
        final Pair<Integer, Integer> farPos = new Pair<>(0, 0);
        enemys.add(mg.makeMeleeRat(counter.next() + " " + farPos.toString(), farPos));
        final long counted = enemyPos.get()
            .filter(ep -> gR.isInRange(heroPos, cursorPos, ep, r)).count();
        assertEquals(4, counted, ERR_M);
    }

    @Test
    void testConeRange() {
        final Range coneR = new ConeRange(BASIC_R_SIZE, basicR);
        cursorPos = Pairs.computeUpPair(Pairs.computeUpPair(heroPos));
        cursorPos = Pairs.computeLeftPair(Pairs.computeLeftPair(cursorPos));
        final long counted = enemyPos.get()
            .filter(ep -> coneR.isInRange(heroPos, cursorPos, ep, r)).count();
        assertEquals(1, counted, ERR_M);
    }

    @Test
    void testConeRange2() { 
        final Range coneR = new ConeRange(BASIC_R_SIZE, basicR);
        cursorPos = Pairs.computeUpPair(Pairs.computeUpPair(heroPos));
        //cursorPos = Pairs.computeLeftPair(Pairs.computeLeftPair(cursorPos));
        final long counted = enemyPos.get()
            .filter(ep -> coneR.isInRange(heroPos, cursorPos, ep, r)).count();
        assertEquals(2, counted, ERR_M);
    }

}
