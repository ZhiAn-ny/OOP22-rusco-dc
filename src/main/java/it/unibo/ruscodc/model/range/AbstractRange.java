package it.unibo.ruscodc.model.range;

import it.unibo.ruscodc.utils.Pair;

public abstract class AbstractRange implements Range{

    private final Pair<Integer, Integer> actPos;

    public AbstractRange(Pair<Integer, Integer> actPos){
        this.actPos = actPos;
    }

    @Override
    public abstract boolean isInRange(Pair<Integer, Integer> pos);
    
    protected Pair<Integer, Integer> getActPos(){
        return actPos;
    }
}
