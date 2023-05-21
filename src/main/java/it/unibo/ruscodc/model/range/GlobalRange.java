package it.unibo.ruscodc.model.range;

import java.util.Iterator;
import java.util.stream.Stream;

import it.unibo.ruscodc.model.Entity;
import it.unibo.ruscodc.model.gamemap.Room;
import it.unibo.ruscodc.utils.Pair;

/**
 * //TODO - è da fare.
 */
public class GlobalRange extends DecoratedRange {

    private static final String TMP_ERR = "Override failed";
    private final Range prevRange;

    /**
     * //TODO - è da fare documentazione.
     * @param start
     */
    public GlobalRange(final Range start) {
        super(start);
        this.prevRange = start;
    }

    /**
     * This type of range is different by other.
     * It must not define a specified shape.
     */
    @Override
    protected Stream<Stream<Pair<Integer, Integer>>> uploadShapeDelta(
            final Pair<Integer, Integer> from,
            final Pair<Integer, Integer> to) {
        throw new IllegalAccessError(TMP_ERR);
    }

    /**
     * 
     */
    @Override
    public boolean isInRange(final Pair<Integer, Integer> by, 
            final Pair<Integer, Integer> to, 
            final Pair<Integer, Integer> toCheck, 
            final Room where) {
        return !where.isInRoom(by); //TODO - testato... ma perplesso...
    }

    /**
     * 
     */
    @Override
    public Iterator<Entity> getRange(final Pair<Integer, Integer> by, 
            final Pair<Integer, Integer> to, 
            final Room where) {
        final Entity res = this.prevRange.getRange(by, to, where).next();

        final Stream<Entity> thisRange = where.getTilesAsEntity().stream()
            .map(oldE -> byPosToEntity(oldE.getPos(), res));

        return thisRange.iterator(); //TODO - è da testare
    }

}
