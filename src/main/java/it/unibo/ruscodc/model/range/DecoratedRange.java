package it.unibo.ruscodc.model.range;

import java.util.Iterator;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import it.unibo.ruscodc.model.Entity;
import it.unibo.ruscodc.model.gamemap.Room;
import it.unibo.ruscodc.utils.Pair;

/**
 * Common implementation for many tipe of range.
 * It follow a "Decoretor" pattern, so it is possible create various tipe of Range
 */
public abstract class DecoratedRange implements Range {

    private Set<Pair<Integer, Integer>> effectiveShape = null;
    private final Stream<Stream<Pair<Integer, Integer>>> shape;
    private final Range basicRange;

    /**
     * Save the last decorated Range.
     * @param start the Range object to decorate with the new class
     */
    public DecoratedRange(final Range start, Stream<Stream<Pair<Integer, Integer>>> shape) {
        this.basicRange = start;
        this.shape = shape;
    }



    /**
     * For classes that implement Range method, is usefull know the previous Range state.
     * @return the last decorated Range
     */
    protected Range getMyRange() {
        return basicRange;
    }

    /**
     * To know the sprite to draw.
     * @return the path sprite, coded into String format
     */
    protected String getPathRes() {
        return null;
    }

    private void commute(Room where) {
        if (effectiveShape == null){
            effectiveShape = shape.flatMap(s -> s.takeWhile(p -> !where.isAccessible(p)))
                .collect(Collectors.toSet());
        }
    }

    /**
     * 
     */
    @Override
    public boolean isInRange(final Pair<Integer, Integer> by, final Pair<Integer, Integer> toCheck, final Room where){

    }
    

    /**
     * 
     */
    @Override
    public abstract Iterator<Entity> getRange(final Pair<Integer, Integer> by, final Room where);
}
