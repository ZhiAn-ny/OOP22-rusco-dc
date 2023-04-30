package it.unibo.ruscodc.model.range;

import java.util.Iterator;

import it.unibo.ruscodc.model.Entity;
import it.unibo.ruscodc.utils.Pair;

/**
 * Specific a Range with a line shape.
 * For now, this line is
 * <ul>
 * <li>infinite</li>
 * <li>ortogonal</li>
 * </ul>
 * 
 */
public final class RowRange extends DecoratedRange{

    /**
     * Summon this tipe of range.
     * @param start the range to decorate
     */
    public RowRange(final Range start) {
        super(start);
    }

    /**
     * 
     */
    @Override
    public boolean isInRange(final Pair<Integer, Integer> by, final Pair<Integer, Integer> toCheck) {
        return getMyRange().isInRange(by, toCheck) 
            || 
            by.getX() == toCheck.getX() || by.getY() == toCheck.getY()
            || Math.abs(by.getX() - toCheck.getX()) == Math.abs(by.getY() - toCheck.getY());
    }

    /**
     * 
     */
    @Override
    public Iterator<Entity> getRange(final Pair<Integer, Integer> by) {
        return null; //TODO - finishImpl
    }

}
