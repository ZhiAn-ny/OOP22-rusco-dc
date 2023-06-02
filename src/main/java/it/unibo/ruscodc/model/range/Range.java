package it.unibo.ruscodc.model.range;

import java.util.Set;

import it.unibo.ruscodc.model.Entity;
import it.unibo.ruscodc.model.gamemap.Room;
import it.unibo.ruscodc.utils.Pair;

/**
 * Define methods about an Area concept.
 * This area could be afflected by some entity wrapped into a Room
 */
public interface Range {
    /**
     * Check if a posion can be considered "in range".
     * @param by the position where Range start
     * @param to the position where Range could end
     * @param toCheck the position that can be "in range" as not
     * @param where the room that could modify the naturally shape of the wrapped Area
     * @return if toCheck is into the Range
     */
    boolean isInRange(Pair<Integer, Integer> by, Pair<Integer, Integer> to, Pair<Integer, Integer> toCheck, Room where);

    /**
     * Compute and return a set of {@code}Entity{@code} that all together rappresent the Range.
     * @param by the position where Range start
     * @param to the position where Range could end
     * @param where the room that could modify the naturally shape of the wrapped Area
     * @return an iterator of this things
     */
    Set<Entity> getRange(Pair<Integer, Integer> by, Pair<Integer, Integer> to, Room where);
}
