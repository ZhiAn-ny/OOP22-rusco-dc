package it.unibo.ruscodc.model.range;

import java.util.stream.Stream;

import it.unibo.ruscodc.utils.Pair;
import it.unibo.ruscodc.utils.Pairs;

/**
 * Specific a Range with a square shape.
 */
public class SquareRange extends DecoratedRange {

    private final int offset;

    /**
     * Add a square shape to the previous range.
     * @param offset the orthogonal distance between center of square and their side
     * @param start the previous range.
     */
    public SquareRange(final int offset, final Range start) {
        super(start);
        this.offset = offset;
    }

    /**
     * 
     */
    @Override
    protected Stream<Stream<Pair<Integer, Integer>>> uploadShapeDelta(Pair<Integer, Integer> from,
            Pair<Integer, Integer> to) {
        return Pairs.computeSquare(to, offset, false);
    }
    
}
