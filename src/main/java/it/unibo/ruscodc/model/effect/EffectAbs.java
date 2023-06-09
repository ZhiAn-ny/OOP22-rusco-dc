package it.unibo.ruscodc.model.effect;

import it.unibo.ruscodc.model.actors.Actor;

/**
 * Basic (half)implementation of an Effect.
 */
public abstract class EffectAbs implements Effect {

    private final int cost;

    /**
     * Create an effect.
     * @param cost how much it cost to be applied to targets
     */
    public EffectAbs(final int cost) {
        this.cost = cost;
    }

    /**
     * 
     */
    @Override
    public int getEffectAPcost() {
        return cost;
    }

    /**
     * Let other classes define which effect is to apply.
     */
    @Override
    public abstract void applyEffect(Actor from, Actor to);
}
