package it.unibo.ruscodc.model.range;

import java.util.Iterator;
import java.util.List;

import it.unibo.ruscodc.model.Entity;
import it.unibo.ruscodc.utils.Pair;

/**
 * This class implement the concept of "collapsed area".
 * So the begin of the range is the range itself.
 */
public abstract class SingleAbs implements Range {
    /**
     * 
     */
    @Override
    public boolean isInRange(final Pair<Integer, Integer> by, final Pair<Integer, Integer> toCheck) {
        return by.equals(toCheck);
    }

    /**
     * 
     */
    @Override
    public Iterator<Entity> getRange(final Pair<Integer, Integer> by) {
        final List<Entity> tmp = List.of(new Entity() {

            @Override
            public String getInfo() {
                return getSpecificInfo();
            }

            @Override
            public String getPath() {
                return getSpecificPath();
            }

            @Override
            public Pair<Integer, Integer> getPos() {
                return by;
            }
        });
        return tmp.iterator();
    }

    /**
     * Let the class that extend this abstract class to specific path resources.
     * @return a String rappresentation about the path
     */
    protected abstract String getSpecificPath();

    /**
     * Let the class that extend this abstract class to specific info.
     * @return a String rappresentation about info
     */
    protected abstract String getSpecificInfo();
}
