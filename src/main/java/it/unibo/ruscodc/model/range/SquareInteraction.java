package it.unibo.ruscodc.model.range;

import java.util.function.Predicate;
import java.util.stream.Stream;

import it.unibo.ruscodc.model.gamemap.Room;
import it.unibo.ruscodc.utils.Pair;
import it.unibo.ruscodc.utils.Pairs;

public class SquareInteraction extends DecoratedRange {

    private final static int SIZE = 1;

    public SquareInteraction(Range start) {
        super(start);
    }

    @Override
    protected Stream<Stream<Pair<Integer, Integer>>> uploadShapeDelta(Pair<Integer, Integer> from,
            Pair<Integer, Integer> to) {
        return Pairs.computeSquare(from, SIZE, false);
    }

    @Override
    protected Predicate<Pair<Integer, Integer>> filterToApply (final Room where) {
        return p -> where.isInRoom(p);
    }
    
}
