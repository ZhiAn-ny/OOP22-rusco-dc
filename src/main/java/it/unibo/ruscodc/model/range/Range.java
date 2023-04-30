package it.unibo.ruscodc.model.range;

import java.util.Iterator;

import it.unibo.ruscodc.model.Entity;
import it.unibo.ruscodc.utils.Pair;

/**
 * Define methods about an Area concept.
 */
public interface Range {
    /**
     * Check if a posion can be considered "in range".
     * @param by the position where Range start
     * @param toCheck the position that can be "in range" as not
     * @return if toCheck is into the Range
     */
    boolean isInRange(Pair<Integer, Integer> by, Pair<Integer, Integer> toCheck);

    /**
     * Compute and return a set of {@code}Entity{@code} that all together rappresent the Range.
     * @param by the position where Range start
     * @return an iterator of this things
     */
    Iterator<Entity> getRange(Pair<Integer, Integer> by);
}
