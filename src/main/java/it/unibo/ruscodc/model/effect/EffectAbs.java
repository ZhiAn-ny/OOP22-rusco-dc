package it.unibo.ruscodc.model.effect;

import it.unibo.ruscodc.model.actors.Actor;

public abstract class EffectAbs implements Effect {

    private final int cost;

    public EffectAbs(int cost){
        this.cost = cost;
    }

    @Override
    public int getEffectAPcost() {
        return cost;
    }

    @Override
    public abstract void applyEffect(Actor from, Actor to);
    
}
