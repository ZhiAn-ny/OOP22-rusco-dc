package it.unibo.ruscodc.model.range;

import java.util.Set;

import it.unibo.ruscodc.model.Entity;
import it.unibo.ruscodc.model.gamemap.Room;
import it.unibo.ruscodc.utils.Pair;

/**
 * This class implement the concept of "collapsed area".
 * So "the begin of the range" is the range itself.
 */
public class SingleAbs implements Range {

    private final String path;
    private final int depth;

    /**
     * Client must't create this object directly, but it must use the subclasses that 
     * extend this class. In these subclasses's costructor, have to specify:
     * @param path the path where stored the entity information, coded as String
     * @param info the info about what to print
     */
    protected SingleAbs(final String path, final int depth) {
        this.path = path;
        this.depth = depth;
    }

    /**
     * 
     */
    @Override
    public boolean isInRange(
            final Pair<Integer, Integer> by, 
            final Pair<Integer, Integer> to, 
            final Pair<Integer, Integer> toCheck, 
            final Room where) {
        return to.equals(toCheck);
    }

    /**
     * 
     */
    @Override
    public Set<Entity> getRange(
            final Pair<Integer, Integer> by, 
            final Pair<Integer, Integer> to, 
            final Room where) {

        final Entity begin = new Entity() {

            @Override
            public int getID() {
                return depth;
            }

            @Override
            public String getPath() {
                return path;
            }

            @Override
            public Pair<Integer, Integer> getPos() {
                return by;
            }

        };

        return Set.of(begin);
    }

    @Override
    public String toString() {
        return "[Single range]";
    }
}
