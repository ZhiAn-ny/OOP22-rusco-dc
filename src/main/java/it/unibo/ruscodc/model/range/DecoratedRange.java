package it.unibo.ruscodc.model.range;

import java.util.Iterator;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import it.unibo.ruscodc.model.Entity;
import it.unibo.ruscodc.model.gamemap.Room;
import it.unibo.ruscodc.utils.Pair;
import it.unibo.ruscodc.utils.Pairs;

/**
 * Common implementation for many tipe of range.
 * It follow a "Decoretor" pattern, so it is possible create various tipe of Range
 */
public abstract class DecoratedRange implements Range {

    private final Range basicRange;

    //private Stream<Stream<Pair<Integer, Integer>>> shapeDelta;
    private Set<Pair<Integer, Integer>> effectiveShape = null;
    private Pair<Integer, Integer> lastBy = null;
    private Pair<Integer, Integer> lastTo = null;

    /**
     * Save the last decorated Range.
     * @param start the Range object to decorate with the new class
     */
    public DecoratedRange(final Range start) {
        this.basicRange = start;
    }

    /**
     * To know the sprite to draw.
     * @return the path sprite, coded into String format
     */  //TODO - WARNING
    protected String getPathRes() {
        return null;
    }

    /**
     * 
     * @param origin
     * @param direction
     * @param where
     * //TODO - protected perchè potrebbe cambiare, essere overriddato da altre classi
     */
    protected void commute(final Pair<Integer, Integer> origin, final Pair<Integer, Integer> direction, final Room where) {
        effectiveShape = uploadShapeDelta(origin, direction).map(s -> Pairs.applyInfLineDelta(s, origin))
            .flatMap(s -> s.takeWhile(p -> !where.isAccessible(p)))
            .collect(Collectors.toSet());
    }

    private void checkIfCommute(final Pair<Integer, Integer> by, final Pair<Integer, Integer> to, final Room where) {
        if (!to.equals(lastTo) || !by.equals(lastBy)){
            this.commute(by, to, where);
        }
        lastBy = by;
        lastTo = to;
    }


    /**
     * 
     */
    @Override
    public boolean isInRange(final Pair<Integer, Integer> by, final Pair<Integer, Integer> to, final Pair<Integer, Integer> toCheck, final Room where) {
        this.checkIfCommute(by, to, where);
        return effectiveShape.contains(toCheck) || basicRange.isInRange(by, to, toCheck, where);
    }

    /**
     * 
     */
    @Override
    public Iterator<Entity> getRange(final Pair<Integer, Integer> by, final Pair<Integer, Integer> to, final Room where) {
        checkIfCommute(by, to, where);
        Stream<Entity> thisRange = this.effectiveShape.stream().map(p -> byPosToEntity(p));
        final Iterator<Entity> tmp = this.basicRange.getRange(by, to, where);
        Stream<Entity> otherRange = Stream.generate(() -> tmp.next()).takeWhile(e -> tmp.hasNext());
        return Stream.concat(otherRange, thisRange).iterator();
    }

    private Entity byPosToEntity(final Pair<Integer, Integer> toConvert) {
        return new Entity() {

            @Override
            public String getInfo() {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'getInfo'");
            }

            @Override
            public String getPath() {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'getPath'");
            }

            @Override
            public Pair<Integer, Integer> getPos() {
                return toConvert;
            }
            
        };
    }

    protected abstract Stream<Stream<Pair<Integer, Integer>>> uploadShapeDelta(final Pair<Integer, Integer> from, final Pair<Integer, Integer> to);
}
