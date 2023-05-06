package it.unibo.ruscodc.model.range;

import java.util.Iterator;

import it.unibo.ruscodc.model.Entity;

/**
 * @deprecated, or better, not usable
 */
public final class RowRangeOLD{

    /**
     * @deprecated, or better, not usable
     */
    /*public RowRangeOLD(final Range start) {
    }*/

    /**
     * @deprecated, or better, not usable
     */
    public boolean isInRange() {
        return false;
        /*getMyRange().isInRange(by, toCheck) 
            || 
            by.getX().equals(toCheck.getX())  || by.getY().equals(toCheck.getY())
            || Math.abs(by.getX() - toCheck.getX()) == Math.abs(by.getY() - toCheck.getY());*/
    }

    /**
     * @deprecated, or better, not usable
     */
    public Iterator<Entity> getRange() {
        return null; //TODO - finishImpl
    }

}
