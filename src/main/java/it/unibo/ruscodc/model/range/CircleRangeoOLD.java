/**/
package it.unibo.ruscodc.model.range;

import java.util.Iterator;

import it.unibo.ruscodc.model.Entity;
import it.unibo.ruscodc.utils.Pair;

/**
 * * @deprecated
 */
public class CircleRangeoOLD{

    //private final int size;

    /**
     * Add a cicle shape to the previous range.
     * @param start the decorated range
     * @param size the radius of the circle
     */
    /*public CircleRangeoOLD(final Range start, final int size) {
        super(start);
        this.size = size;
    }*/

    /**
     * @deprecated, or better, not usable
     */
    //private boolean myInRangeCheck() {
    //    return false; 
    //    //(Math.abs(by.getX() - toCheck.getX()) + Math.abs(by.getY() - toCheck.getY())) <= size;
    //}

    /**
     * @deprecated, or better, not usable
     */
    public boolean isInRange() {
        return false; 
        //getMyRange().isInRange(by, toCheck) || myInRangeCheck(by, toCheck);
    }

    /**
     * @deprecated, or better, not usable
     */
    public Iterator<Entity> getRange(final Pair<Integer, Integer> by) {
        return null;
        /*final int xB = by.getX();
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
        return Stream.concat(tmp, Stream.iterate(tmp2.next(), i -> tmp2.hasNext(), j -> tmp2.next())).iterator();*/
    }

}