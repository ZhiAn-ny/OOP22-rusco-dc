package it.unibo.ruscodc.model.actors;

import it.unibo.ruscodc.model.Entity;
import it.unibo.ruscodc.model.actors.StatImpl.StatName;
import it.unibo.ruscodc.utils.Pair;

/**
 * Interface for the Actors.
 */
public interface Actor extends Entity {

    String getName();

    int getStatInfo(StatName statName);

    /**
     * @param newPos
     */
    void setPos(Pair<Integer, Integer> newPos);

    void modifyStat(StatName statName, int value);

    Skill getSkills();

    boolean isAlive();
    
}
