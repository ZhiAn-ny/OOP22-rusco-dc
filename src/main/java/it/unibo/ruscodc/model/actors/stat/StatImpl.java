package it.unibo.ruscodc.model.actors.stat;

import java.util.HashMap;
import java.util.Map;
import it.unibo.ruscodc.utils.Pair;

/**
 * A class used to manage the Stats of an Actor.
 */
public class StatImpl implements Stat {

    /**
     * StatName represent every name associated to every Stat of an Actor.
     */
    public enum StatName {
        /**
         * HP represent the Health Value of the actor.
         */
        HP,
        /**
         * AP represent the Action Points of the actor.
         */
        AP,
        /**
         * DMG represent the Base Damage of the actor.
         */
        DMG,
        /**
         * STR represent the Strenght Value of the actor.
         */
        STR,
        /**
         * DEX represent the Dexterity Value of the actor.
         */
        DEX,
        /**
         * INT represent the Intellect Value of the actor.
         */
        INT;
    }

    private static final Pair<Integer, Integer> DEFAULT = new Pair<Integer, Integer>(0, 0);

    private final Map<StatName, Pair<Integer, Integer>> stats = new HashMap<>();

    /**
     * Basic Constructor for StatImpl.
     */
    public StatImpl() {
        for (StatName statName : StatName.values()) {
            this.stats.put(statName, DEFAULT);
        }
    }

    /**
     * 
     */
    @Override
    public void setStatActualValue(final StatName toSet, final int actualValue) {
        Pair<Integer, Integer> values = this.stats.get(toSet);
        this.stats.put(toSet, new Pair<Integer, Integer>(actualValue, values.getY()));
    }

    /**
     * 
     */
    @Override
    public void setStatMaxValue(final StatName toSet, final int maxValue) {
        Pair<Integer, Integer> values = this.stats.get(toSet);
        this.stats.put(toSet, new Pair<Integer, Integer>(values.getX(), maxValue));
    }

    /**
     * 
     */
    @Override
    public void setStat(final StatName toSet, final Pair<Integer, Integer> val) {
        this.stats.put(toSet, val);
    }

    /**
     * 
     */
    @Override
    public int getStatActual(final StatName statName) {
        return this.stats.get(statName).getX();
    }

    /**
     * 
     */
    @Override
    public int getStatMax(final StatName statName) {
        return this.stats.get(statName).getY();
    }

    /**
     * 
     */
    @Override
    public String toString() {
        StringBuilder info = new StringBuilder();
        for (StatName statName : StatName.values()) {
            info.append(
                statName
                + ":["
                + this.getStatActual(statName)
                + "|"
                + this.getStatMax(statName)
                + "], "
            );
        }
        return info.toString();
    }
}
