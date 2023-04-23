package it.unibo.ruscodc.model.effect;

import it.unibo.ruscodc.model.actors.Actor;
import it.unibo.ruscodc.utils.exception.ModelException;

public interface Effect {

    void applyEffect(Actor from, Actor to) throws ModelException;

}