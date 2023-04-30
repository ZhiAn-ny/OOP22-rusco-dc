package it.unibo.ruscodc.model.range;

import java.util.Iterator;

import it.unibo.ruscodc.model.Entity;
import it.unibo.ruscodc.utils.Pair;

/**
 * Common implementation for many tipe of range.
 * It follow a "Decoretor" pattern, so it is possible create various tipe of Range
 */
public abstract class DecoratedRange implements Range {

    private final Range basicRange;

    /**
     * Save the last decorated Range.
     * @param start the Range object to decorate with the new class
     */
    public DecoratedRange(final Range start) {
        this.basicRange = start;
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
        return this.basicRange.getRange(new Pair<>(0, 0)).next().getPath();
    }

    /**
     * 
     */
    @Override
    public abstract boolean isInRange(Pair<Integer, Integer> by, Pair<Integer, Integer> toCheck);

    /**
     * 
     */
    @Override
    public abstract Iterator<Entity> getRange(Pair<Integer, Integer> by);
}
