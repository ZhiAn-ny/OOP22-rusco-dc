package it.unibo.ruscodc.model.range;

import java.util.function.Predicate;
import java.util.stream.Stream;

import it.unibo.ruscodc.model.gamemap.Room;
import it.unibo.ruscodc.utils.Pair;
import it.unibo.ruscodc.utils.Pairs;

/**
 * TODO - documentazione!.
 */
public class SquareInteraction extends DecoratedRange {

    private static final int SIZE = 1;

    /**
     * TODO - documentazione!.
     * @param start TODO - documentazione!.
     */
    public SquareInteraction(final Range start) {
        super(start);
    }

    /**
     * 
     */
    @Override
    protected Stream<Stream<Pair<Integer, Integer>>> uploadShapeDelta(
            final Pair<Integer, Integer> from,
            final Pair<Integer, Integer> to) {
        return Pairs.computeSquare(from, SIZE, false);
    }

    /**
     * 
     */
    @Override
    protected Predicate<Pair<Integer, Integer>> filterToApply (final Room where) {
        return p -> where.isInRoom(p);
    }
    
}
