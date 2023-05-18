package it.unibo.ruscodc.model.effect;

import it.unibo.ruscodc.model.actors.Actor;

/**
 * Interface for wrapping a type of action (basically an attack) that start from an actor and afflict another actor.
 * For now, it is an functional interface
 */
public interface Effect {

    /**
     * Apply a specific effect.
     * @param from actor from get some info
     * @param to actor to apply the effect
     */
    void applyEffect(Actor from, Actor to);

    /**
     * Obtain the cost of the effect
     * @return the cost
     */
    int getAPcost();

}
