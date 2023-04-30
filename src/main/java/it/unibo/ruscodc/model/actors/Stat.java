package it.unibo.ruscodc.model.actors;

import java.util.Map;

import it.unibo.ruscodc.model.actors.StatImpl.StatName;
import it.unibo.ruscodc.utils.Pair;

public interface Stat {
    void setStatActualValue(StatName toSet, int actualValue);
    void setStatMaxValue(StatName toSet, int MaxlValue);
    void setStat(StatName toSet, Pair<Integer,Integer> val);
    Map<StatName, Pair<Integer, Integer>> getStats();

}
