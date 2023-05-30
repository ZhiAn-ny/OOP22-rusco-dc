package it.unibo.ruscodc.model.range;

import java.util.Iterator;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import it.unibo.ruscodc.model.Entity;
import it.unibo.ruscodc.model.gamemap.Room;
import it.unibo.ruscodc.utils.Pair;

/**
 * Specific a Range with "Room" shape.
 * Wrap the concept that the command, or more generally an alghoritm, 
 * that need this type of Range take effect to all Room position.
 */
public class GlobalRange extends DecoratedRange {

    private static final String TMP_ERR = "Override failed";
    private final Range prevRange;

    /**
     * Create this type of range
     * @param start the previous range. This type of range ignore the previous range shape,
     * so this range use this information to be commuted as Entity to print to view
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
        return where.isInRoom(by);
    }

    /**
     * 
     */
    @Override
    public Set<Entity> getRange(final Pair<Integer, Integer> by, 
            final Pair<Integer, Integer> to, 
            final Room where) {
        final Entity res = this.prevRange.getRange(by, to, where).stream().findFirst().get();

        final Stream<Entity> thisRange = where.getTilesAsEntity().stream()
            .map(oldE -> byPosToEntity(oldE.getPos(), res));

        return thisRange.collect(Collectors.toSet()); //TODO - è da testare
    }

}
