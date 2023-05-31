package it.unibo.ruscodc.model.range;

import java.util.stream.Stream;

import it.unibo.ruscodc.utils.Pair;
import it.unibo.ruscodc.utils.Pairs;

/**
 * Specific a Range with a cone shape. 
 * This cone has an angle of 90 degrees, starts at the "from" position of the "uploadShapeDelta" method 
 * and the bisector of this cone is oriented along the direction of the half line
 * that connects the "from" position to the "to" position
 * (specified in the "uploadShapeDelta" method)
 */
public class ConeRange extends DecoratedRange {

    private final int radius;

    /**
     * Build a Cone shape.
     * @param start the last Range to decorate
     * @param radius the lenght of the radius of the cone
     */
    public ConeRange(final int radius, final Range start) {
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
        return Pairs.computePerpendicularCone(to, from, radius, false);
    }
}
