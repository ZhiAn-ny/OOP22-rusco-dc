package it.unibo.ruscodc.model.range;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;
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
    private final Set<Pair<Integer, Integer>> effectiveShape = new HashSet<>();
    private Pair<Integer, Integer> lastBy;
    //private Pair<Integer, Integer> lastTo;

    /**
     * Save the last decorated Range.
     * @param start the Range object to decorate with the new class
     */
    protected DecoratedRange(final Range start) {
        this.basicRange = start;
    }

    private void commute(final Pair<Integer, Integer> origin, final Pair<Integer, Integer> direction, final Room where) {
        effectiveShape.clear();
        effectiveShape.addAll(this.uploadShapeDelta(origin, direction)
            .parallel()
            .map(s -> Pairs.applyLineDelta(s, this.centerToFrom() ? origin : direction))
            .flatMap(s -> s.takeWhile(this.filterToApply(where)))
            .collect(Collectors.toSet()));
    }

    private void checkIfCommute(final Pair<Integer, Integer> by, final Pair<Integer, Integer> to, final Room where) {
        //if (!to.equals(lastTo) || !by.equals(lastBy)) {
        //System.out.println("ahahah");
        if (!by.equals(lastBy)) {
            this.commute(by, to, where);
        }
        lastBy = by;
        //lastBy = by;
        //lastTo = to;
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
        this.checkIfCommute(by, to, where);
        if (basicRange.getRange(by, toCheck, where).size() == 1) {
            return effectiveShape.contains(toCheck); //okForLast = !okForLast;
        }
        final boolean okForLast = basicRange.isInRange(by, to, toCheck, where);
        return effectiveShape.contains(toCheck) || okForLast;
        //boolean tmp = effectiveShape.contains(toCheck) && okForLast;
        //return tmp;
    }

    /**
     * 
     */
    @Override
    public Set<Entity> getRange(
            final Pair<Integer, Integer> by, 
            final Pair<Integer, Integer> to, 
            final Room where) {
        final Set<Entity> tmp = this.basicRange.getRange(by, to, where);
        if (tmp.isEmpty()) {
            return tmp;
        }

        final Entity res = tmp.stream().findFirst().get();
        final Stream<Entity> otherRange = tmp.stream();

        checkIfCommute(by, to, where);

        final Stream<Entity> thisRange = this.effectiveShape.stream()
            .filter(p -> !p.equals(by))
            .map(p -> byPosToEntity(p, res));

        return Stream.concat(otherRange, thisRange).collect(Collectors.toSet());
    }

    /**
     * Utility function to conver a simple Pair<Integer, Integer> into a printable Entity.
     * @param toConvert the position to convert
     * @param res an Entity to get other info
     * @return the relative entity
     */
    protected Entity byPosToEntity(
            final Pair<Integer, Integer> toConvert,
            final Entity res) {

        return new Entity() {

            @Override
            public int getID() {
                return res.getID();
            }

            @Override
            public String getPath() {
                return res.getPath();
            }

            @Override
            public Pair<Integer, Integer> getPos() {
                return toConvert;
            }

        };
    }

    /**
     * Let other class define their natural shape.
     * @param from the begin of the shape
     * @param to the end of the shape
     * @return a stream of line that toghether make the shape
     */
    protected abstract Stream<Stream<Pair<Integer, Integer>>> uploadShapeDelta(
        Pair<Integer, Integer> from, 
        Pair<Integer, Integer> to);

    /**
     * Let other class define when stop a single row.
     * @param where the room, which contains info that define this stop.
     * @return this type of filter.
     */
    protected Predicate<Pair<Integer, Integer>> filterToApply(final Room where) {
        return p -> where.isInRoom(p) && where.isAccessible(p);
    }

    /**
     * Let other class define where Range start (if it start in "by" or in "to").
     * @return true if this start into "by", false otherwise
     */
    protected boolean centerToFrom() {
        return true;
    }
}
