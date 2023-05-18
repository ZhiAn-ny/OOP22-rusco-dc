package it.unibo.ruscodc.model.effect;

import it.unibo.ruscodc.model.actors.Actor;

public interface SingleTargetEffect {
    /**
     * Applies an effect on a single <code>Actor</code>.
     * @param target the target of the effect
     */
     void applyEffect(Actor target);
}
