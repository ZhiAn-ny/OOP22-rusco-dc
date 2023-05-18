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

    int getStatActual(StatName statName);

    int getStatMax(StatName statName);

}
