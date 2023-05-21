package it.unibo.ruscodc.model.range;

import java.util.stream.Stream;

import it.unibo.ruscodc.utils.Pair;
import it.unibo.ruscodc.utils.Pairs;

/**
 * //TODO - da completare.
 */
public class ConeRangeSplash extends DecoratedRange {

    private final int radius;

    /**
     * Build a Cone shape.
     * @param start the last Range to decorate
     * @param radius the lenght of the radius of the cone
     */
    public ConeRangeSplash(final int radius, final Range start) {
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
        final Pair<Integer, Integer> newTo = Pairs.mirror(to, from);
        return Pairs.computePerpendicularCone(to, newTo, radius, false);
    }
}
