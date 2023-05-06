package it.unibo.ruscodc.model.range;

import java.util.stream.Stream;

import it.unibo.ruscodc.utils.Pair;
import it.unibo.ruscodc.utils.Pairs;

public class ConeRange extends DecoratedRange {

    final int radius;

    public ConeRange(final Range start, final int radius) {
        super(start);
        this.radius = radius;
    }

    /**
     * //TODO WARNING: la libreria computa ancora solo un cono pronto, bisogna modificarla.
     * Questo range ancora non è usabile
     */
    @Override
    protected Stream<Stream<Pair<Integer, Integer>>> uploadShapeDelta(Pair<Integer, Integer> from,
            Pair<Integer, Integer> to) {
        return Pairs.computePerpendicularCone(from, to, radius);
    }
    
}
