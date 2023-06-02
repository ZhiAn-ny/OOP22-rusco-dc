package it.unibo.ruscodc.model.actors;

import it.unibo.ruscodc.model.Entity;
import it.unibo.ruscodc.model.actors.skill.Skill;
import it.unibo.ruscodc.model.actors.stat.Stat;
import it.unibo.ruscodc.model.actors.stat.StatImpl.StatName;
import it.unibo.ruscodc.utils.Pair;

/**
 * Interface for the Actors.
 */
public interface Actor extends Entity {

    String getName();

    Stat getStats();

    Skill getSkills();
    
    int getStatActual(StatName statName);

    int getStatMax(StatName statName);

    /**
     * @param newPos
     */
    void setPos(Pair<Integer, Integer> newPos);

    void modifyActualStat(StatName statName, int value);
    
    void modifyMaxStat(StatName statName, int value); 

    boolean isAlive();
    
}
