package it.unibo.ruscodc.model.range;

import java.util.Iterator;

import it.unibo.ruscodc.model.Entity;
import it.unibo.ruscodc.utils.Pair;

public abstract class DecoratedRange implements Range {

    private final Range basicRange;

    public DecoratedRange(Range start){
        this.basicRange = start;
    }

    protected Range getMyRange(){
        return basicRange;
    }

    protected String getPathRes(){
        return "";
    }

    @Override
    public abstract boolean isInRange(Pair<Integer, Integer> by, Pair<Integer, Integer> toCheck);

    @Override
    public abstract Iterator<Entity> getRange(Pair<Integer, Integer> by);
    
}
