package it.unibo.ruscodc.model.range;

import java.util.Iterator;
import java.util.stream.Stream;

import it.unibo.ruscodc.model.Entity;
import it.unibo.ruscodc.utils.Pair;

/**
 * Specific a Range with a circle shape.
 */
public class CircleRangeoOLD extends DecoratedRange {

    private final int size;

    /**
     * Add a cicle shape to the previous range.
     * @param start the decorated range
     * @param size the radius of the circle
     */
    public CircleRangeoOLD(final Range start, final int size) {
        super(start);
        this.size = size;
    }

    /**
     * Specify the function that help to check if a position in in the range.
     * @param by the start position
     * @param toCheck the position to check
     * @return if toCheck is in the range
     */
    private boolean myInRangeCheck(final Pair<Integer, Integer> by, final Pair<Integer, Integer> toCheck) {
        return (Math.abs(by.getX() - toCheck.getX()) + Math.abs(by.getY() - toCheck.getY())) <= size;
    }

    /**
     * 
     */
    @Override
    public boolean isInRange(final Pair<Integer, Integer> by, final Pair<Integer, Integer> toCheck) {
        return getMyRange().isInRange(by, toCheck) || myInRangeCheck(by, toCheck);
    }

    /**
     * 
     */
    @Override
    public Iterator<Entity> getRange(final Pair<Integer, Integer> by) {
        final int xB = by.getX();
        final int yB = by.getY();
        final Stream<Entity> tmp = Stream
            .iterate(xB - size, i -> i <= xB + size, j -> j + 1)
            .flatMap(x -> Stream
                .iterate(yB - size, i -> i <= yB + size, j -> j + 1)
                .map(y -> new Pair<Integer, Integer>(x, y)))
            .filter(p -> myInRangeCheck(by, p))
            .map(p -> new Entity() {

                @Override
                public String getInfo() {
                    return "classic";
                }

                @Override
                public String getPath() {
                    return getPathRes();
                }

                @Override
                public Pair<Integer, Integer> getPos() {
                    return p;
                }
            });
        final Iterator<Entity> tmp2 = getMyRange().getRange(by);
        return Stream.concat(tmp, Stream.iterate(tmp2.next(), i -> tmp2.hasNext(), j -> tmp2.next())).iterator();
    }

}
