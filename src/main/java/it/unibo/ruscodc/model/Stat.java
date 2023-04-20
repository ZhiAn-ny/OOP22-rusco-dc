package it.unibo.ruscodc.model;

import java.util.Map;

import it.unibo.ruscodc.model.StatImpl.StatName;
import it.unibo.ruscodc.utils.Pair;

public interface Stat {
    void setStatActualValue(StatName toSet, int actualValue);
    void setStatMaxValue(StatName toSet, int MaxlValue);
    Map<StatName, Pair<Integer, Integer>> getStats();

}
