package it.unibo.ruscodc.model.actors;

import it.unibo.ruscodc.model.Entity;
import it.unibo.ruscodc.utils.Pair;

/**
 * Interface for the Actors.
 */
public interface Actor extends Entity {

    /**
     * @param newPos
     */
    void setPos(Pair<Integer, Integer> newPos);

    /**
     * @return the Skill of the Actor.
     */
    Skill getSkills();

    /**
     * @return the Name of the Actor.
     */
    String getName();

    /**
     * @return the Stats of the Actor.
     */
    Stat getStats();

    /**
     * a method used to load the other features of the Actor.
     */
    void load();
}
