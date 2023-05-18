package it.unibo.ruscodc.model.actors.stat;

import java.util.HashMap;
import java.util.Map;
import it.unibo.ruscodc.utils.Pair;

/**
 * A class used to manage the Stats of an Actor
 */
public class StatImpl implements Stat {

    public enum StatName {
        HP,
        AP,
        DMG,
        STR,
        DEX,
        INT;
    }
    
    private final Map<StatName, Pair<Integer, Integer>> stats = new HashMap<>();

    //TODO: Constructor default
    
    @Override
    public void setStatActualValue(StatName toSet, int actualValue) {
        Pair<Integer, Integer> values = this.stats.get(toSet);
        this.stats.put(toSet, new Pair<Integer,Integer>(actualValue, values.getY()));
    }
    
    @Override
    public void setStatMaxValue(StatName toSet, int MaxlValue) {
        Pair<Integer, Integer> values = this.stats.get(toSet);
        this.stats.put(toSet, new Pair<Integer,Integer>(values.getX(), MaxlValue));
    }
    
    @Override
    public void setStat(StatName toSet, Pair<Integer, Integer> val) {
        this.stats.put(toSet, val);
    }

    @Override
    public int getStatActual(StatName statName) {
        return this.stats.get(statName).getX();
    }

    @Override
    public int getStatMax(StatName statName) {
        return this.stats.get(statName).getY();
    }

}