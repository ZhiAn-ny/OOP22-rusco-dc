package it.unibo.ruscodc.model.effect;

import it.unibo.ruscodc.model.actors.Actor;

/**
 * Functional interface for objects similar to "Effect" 
 * but which activates the effect only on the actor who is issuing the command.
 */
public interface SingleTargetEffect {

    /**
     * Applies an effect on a single <code>Actor</code>.
     * @param target the target of the effect
     */
     void applyEffect(Actor target);
}
