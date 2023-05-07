package it.unibo.ruscodc;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import it.unibo.ruscodc.model.gamemap.RectangleRoomImpl;
import it.unibo.ruscodc.model.gamemap.Room;
import it.unibo.ruscodc.model.range.CircleRange;
import it.unibo.ruscodc.model.range.DirectRowRange;
import it.unibo.ruscodc.model.range.Range;
import it.unibo.ruscodc.model.range.SingleRange;
import it.unibo.ruscodc.model.range.SingleSplash;
import it.unibo.ruscodc.utils.Pair;

/**
 * Test class for Range
 */
public class RangeTest {
    
    private Range range1;
    private Range splash1;
    private List<Pair<Integer, Integer>> enemyPos;
    private Pair<Integer, Integer> heroPos;
    private Pair<Integer, Integer> cursePos;
    private Room r = new RectangleRoomImpl(20, 20);
    
    private RangeTest(){
    }

    @BeforeAll
    public void init(){
        range1 = new CircleRange(3, new SingleRange());
        splash1 = new DirectRowRange(new SingleSplash());
        heroPos = new Pair<>(3, 3); 

        Pair<Integer, Integer> pos1 = new Pair<>(5, 3);
        Pair<Integer, Integer> pos2 = new Pair<>(4, 4);
        Pair<Integer, Integer> pos3 = new Pair<>(3, 5);
        enemyPos.addAll(List.of(pos1, pos2, pos3));

    }


    @Test
    public void areEnemyInRange() {
        long counted = enemyPos.stream().filter(ep -> range1.isInRange(heroPos, cursePos, ep, r)).count();
        assertEquals(3, counted, "FAILED");
    }

}
