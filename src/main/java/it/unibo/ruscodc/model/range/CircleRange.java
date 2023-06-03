package it.unibo.ruscodc.model.range;

import java.util.stream.Stream;

import it.unibo.ruscodc.utils.Pair;
import it.unibo.ruscodc.utils.Pairs;

/**
 * Specific a Range with a circle shape.
 */
public class CircleRange extends DecoratedRange {

    private final int radius;

    /**
     * Add a cicle shape to the previous range.
     * @param start the decorated range
     * @param radius the radius of the circle
     */
    public CircleRange(final int radius, final Range start) {
        super(start);
        this.radius = radius;
    }

    /**
     * 
     */
    @Override
    protected Stream<Stream<Pair<Integer, Integer>>> uploadShapeDelta(
            final Pair<Integer, Integer> from,
            final Pair<Integer, Integer> to) {
        return Pairs.computeCircle(from, radius, false);
    }

    /**
     * 
     */
    @Override
    public String toString() {
        return "[Circle range" + this.radius + "]";
    }
}
