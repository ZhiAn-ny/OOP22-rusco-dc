package it.unibo.ruscodc.model.range;

import java.util.stream.Stream;

import it.unibo.ruscodc.utils.Pair;
import it.unibo.ruscodc.utils.Pairs;

/**
 * Specific a Range with a line shape.
 **/
public class DirectRowRange extends DecoratedRange {

    /**
     * Build a line that connect directly the actor to his target.
     * @param start the previous Range to decorate
     */
    public DirectRowRange(final Range start) {
        super(start);
    }

    /**
     * 
     */
    @Override
    protected Stream<Stream<Pair<Integer, Integer>>> uploadShapeDelta(
            final Pair<Integer, Integer> from,
            final Pair<Integer, Integer> to) {
        return Stream.of(Pairs.computeBoldLine(from, to));
    }
}
