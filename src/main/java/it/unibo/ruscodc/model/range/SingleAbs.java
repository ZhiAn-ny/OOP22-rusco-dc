package it.unibo.ruscodc.model.range;

import java.util.Iterator;
import java.util.stream.Stream;

import it.unibo.ruscodc.model.Entity;
import it.unibo.ruscodc.model.gamemap.Room;
import it.unibo.ruscodc.utils.Pair;

/**
 * This class implement the concept of "collapsed area".
 * So "the begin of the range" is the range itself.
 */
public class SingleAbs implements Range {

    private final String path;
    private final String info;

    /**
     * Client must't create this object directly, but it must use the subclasses that 
     * extend this class. In these subclasses's costructor, have to specify:
     * @param path the path where stored the entity information, coded as String
     * @param info the info about what to print
     */
    protected SingleAbs(final String path, final String info) {
        this.path = path;
        this.info = info;
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
        return by.equals(toCheck) && where.isAccessible(toCheck);
    }

    /**
     * 
     */
    @Override
    public Iterator<Entity> getRange(
            final Pair<Integer, Integer> by, 
            final Pair<Integer, Integer> to, 
            final Room where) {

        final Entity begin = new Entity() {

            @Override
            public int getID() {
                return 4;
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

        return Stream.of(begin).filter(e -> !where.isAccessible(e.getPos())).iterator();
    }

    // /**
    //  * Let the class that extend this abstract class to specific path resources.
    //  * @return a String rappresentation about the path
    //  */
    // protected abstract String getSpecificPath();

    // /**
    //  * Let the class that extend this abstract class to specific info.
    //  * @return a String rappresentation about info
    //  */
    // protected abstract String getSpecificInfo();
}
