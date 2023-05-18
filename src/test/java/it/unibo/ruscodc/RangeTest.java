package it.unibo.ruscodc;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import it.unibo.ruscodc.model.gamemap.RectangleRoomImpl;
import it.unibo.ruscodc.model.gamemap.Room;
import it.unibo.ruscodc.model.range.CircleRange;
import it.unibo.ruscodc.model.range.Range;
import it.unibo.ruscodc.model.range.SingleRange;
import it.unibo.ruscodc.utils.Pair;
import it.unibo.ruscodc.utils.Pairs;

/**
 * Test class for Range.
 */
final class RangeTest {
    private Range range1;
    //private Range splash1;
    private final List<Pair<Integer, Integer>> enemyPos = new ArrayList<>();
    private Pair<Integer, Integer> heroPos;
    private Pair<Integer, Integer> cursePos;
    private static final int ROOM_SIZE = 20;
    private final Room r = new RectangleRoomImpl(ROOM_SIZE, ROOM_SIZE);
    private static final  Pair<Integer, Integer> START_POS1 = new Pair<>(4, 4);

    /**
     * Init some variable before start tests.
     */
    @BeforeAll
    private void init() { //NOPMD - this is a method of Test class, it must be private without other method use it!
        range1 = new CircleRange(3, new SingleRange());
        //splash1 = new DirectRowRange(new SingleSplash());
        cursePos = START_POS1;
        heroPos = new Pair<>(3, 3); 

        final Pair<Integer, Integer> pos1 = Pairs.computeDownPair(Pairs.computeLeftPair(START_POS1)); 
        final Pair<Integer, Integer> pos2 = START_POS1; 
        final Pair<Integer, Integer> pos3 = Pairs.computeUpPair(Pairs.computeRightPair(START_POS1));
        enemyPos.addAll(List.of(pos1, pos2, pos3));

    }

    /**
     * Test focused to test the "range" concept.
     */
    @Test
    private void areEnemyInRange() { //NOPMD - this is a method of Test class, it must be private without other method use it!
        final long counted = enemyPos.stream().filter(ep -> !range1.isInRange(heroPos, cursePos, ep, r)).count();
        assertEquals(3, counted, "FAILED");
    }

}
