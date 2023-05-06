package it.unibo.ruscodc.model.range;

import java.util.stream.Stream;

import it.unibo.ruscodc.utils.Pair;
import it.unibo.ruscodc.utils.Pairs;

/**
 * Specific a Range with a circle shape.
 */
public class CircleRange extends DecoratedRange {

    final int radius;

     /**
     * Add a cicle shape to the previous range.
     * @param start the decorated range
     * @param size the radius of the circle
     */
    public CircleRange(final Range start, final int radius) {
        super(start);
        this.radius = radius;
    }

    @Override
    protected Stream<Stream<Pair<Integer, Integer>>> uploadShapeDelta(Pair<Integer, Integer> from,
            Pair<Integer, Integer> to) {
        return Pairs.computeCircle(from, radius);
    }

    
}
