package it.unibo.ruscodc.model.actors.stat;

import it.unibo.ruscodc.model.actors.stat.StatImpl.StatName;
import it.unibo.ruscodc.utils.Pair;

/**
 * Interface for the Stats used by Actors.
 */
public interface Stat {

    /**
     * @param toSet
     * @param actualValue
     */
    void setStatActualValue(StatName toSet, int actualValue);

    /**
     * @param toSet
     * @param maxlValue
     */
    void setStatMaxValue(StatName toSet, int maxlValue);

    /**
     * @param toSet
     * @param val
     */
    void setStat(StatName toSet, Pair<Integer, Integer> val);

    /**
     * @param statName which Stat you want to get the info from
     * @return the current value of that Stat
     */
    int getStatActual(StatName statName);

    /**
     * @param statName which Stat you want to get the info from
     * @return the max value of that Stat
     */
    int getStatMax(StatName statName);
}
