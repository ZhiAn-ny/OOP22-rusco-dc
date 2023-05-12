package it.unibo.ruscodc.model.actors;

import java.util.Map;
import it.unibo.ruscodc.model.actors.StatImpl.StatName;
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
     * @return the Map of all the Stats
     */
    Map<StatName, Pair<Integer, Integer>> getStats();

}
