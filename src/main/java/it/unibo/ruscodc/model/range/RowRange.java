package it.unibo.ruscodc.model.range;

import java.util.Iterator;

import it.unibo.ruscodc.model.Entity;
import it.unibo.ruscodc.utils.Pair;

public final class RowRange extends DecoratedRange{

    public RowRange(Range start) {
        super(start);
    }

    @Override
    public boolean isInRange(Pair<Integer, Integer> by, Pair<Integer, Integer> toCheck) {
        return getMyRange().isInRange(by, toCheck) 
            || 
            by.getX() == toCheck.getX() || by.getY() == toCheck.getY()
            || Math.abs(by.getX() - toCheck.getX()) == Math.abs(by.getY() - toCheck.getY());
    }

    @Override
    public Iterator<Entity> getRange(Pair<Integer, Integer> by) {
        return null; //TODO-finishImpl
    }
    
}
