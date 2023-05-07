package it.unibo.ruscodc.model.range;

import java.util.stream.Stream;

import it.unibo.ruscodc.utils.Pair;
import it.unibo.ruscodc.utils.Pairs;

/**
 * TODO - DA FINIRE.
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
     * //TODO WARNING: la libreria computa ancora solo un cono pronto, bisogna modificarla.
     * Questo range ancora non è usabile
     */
    @Override
    protected Stream<Stream<Pair<Integer, Integer>>> uploadShapeDelta(
            final Pair<Integer, Integer> from,
            final Pair<Integer, Integer> to) {
        return Pairs.computePerpendicularCone(from, to, radius);
    }
}
