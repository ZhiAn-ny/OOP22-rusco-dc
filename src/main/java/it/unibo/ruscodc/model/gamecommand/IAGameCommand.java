package it.unibo.ruscodc.model.gamecommand;

import java.util.List;

import it.unibo.ruscodc.model.actors.Actor;
import it.unibo.ruscodc.utils.Pair;

/**
 * Defines a set of method usefull to manage automatically a specific situation.
 * With this class the game can manage mobs
 */
public interface IAGameCommand {

    /**
     * Focus the attack to a specific actor.
     * @param toFocus where the attack start
     */
    void setCursorPos(Pair<Integer, Integer> toFocus);

    /**
     * Specifies possible actory that can be afflicted by the attack.
     * @param targettableActors these //TODO migliorami
     */
    void setTarget(List<Actor> targettableActors);

    /**
     * Check if an actor is targetable.
     * @param target who desire to attack
     * @return if it is possible
     */
    boolean isTargetInRange(Actor target);

    /**
     * Get the cost of this action.
     * @return the AP cost
     */
    int getAPCost();
}
