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

    /**
     * @return the name of the Actor
     */
    String getName();

    /**
     * @return the Actor's Stat
     */
    Stat getStats();

    /**
     * @return the Actor's Skill 
     */
    Skill getSkills();

    /**
     * @param statName of the Stat you want to get info of
     * @return it's actual Value
     */
    int getStatActual(StatName statName);

    /**
     * @param statName of the Stat you want to get info of
     * @return it's max Value
     */
    int getStatMax(StatName statName);

    /**
     * @param newPos of the Actor
     */
    void setPos(Pair<Integer, Integer> newPos);

    /**
     * @param statName of the Stat you want to modify
     * @param value that you want to add to it's actual Value
     */
    void modifyActualStat(StatName statName, int value);

    /**
     * @param statName of the Stat you want to modify
     * @param value that you want to add to it's max Value
     */
    void modifyMaxStat(StatName statName, int value);

    /**
     * @return if the Hero is alive
     */
    boolean isAlive();
}
