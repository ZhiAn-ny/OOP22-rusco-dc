package it.unibo.ruscodc.model.actors;

import java.util.Collections;

import it.unibo.ruscodc.model.actors.skill.Skill;
import it.unibo.ruscodc.model.actors.stat.Stat;
import it.unibo.ruscodc.model.actors.stat.StatImpl.StatName;
import it.unibo.ruscodc.utils.Pair;

/**
 * 
 */
public abstract class ActorAbs implements Actor {
    private static final int ID = 3;
    private final String name;
    private Pair<Integer, Integer> currentPos;
    private final Skill skills;
    private final Stat stats;

    /**
     * @param name of the Actor
     * @param currentPos of the Actor
     * @param skills of the Actor
     * @param stats of the Actor
     * Basic constructor for every Actor 
     */
    public ActorAbs(final String name, final Pair<Integer, Integer> currentPos, final Skill skills, final Stat stats) {
        this.name = name;
        this.currentPos = currentPos;
        this.skills = skills;
        this.stats = stats;
    }

    /**
     * 
     */
    @Override
    public int getID() {
        return ID;
    }

    /**
     * 
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * 
     */
    @Override
    public Pair<Integer, Integer> getPos() {
        return this.currentPos;
    }

    /**
     * 
     */
    @Override
    public void setPos(final Pair<Integer, Integer> newPos) {
        this.currentPos = newPos;
    }

    /**
     * 
     */
    @Override
    public int getStatActual(final StatName statName) {
        return this.stats.getStatActual(statName);
    }

    /**
     * 
     */
    @Override
    public int getStatMax(final StatName statName) {
        return this.stats.getStatMax(statName);
    }

    /**
     * 
     */
    @Override
    public void modifyActualStat(final StatName statName, final int value) {
        int current = this.stats.getStatActual(statName);
        if ((current + value) < 0) {
            this.stats.setStatActualValue(statName, 0);
        } else if ((current + value) > this.stats.getStatMax(statName)) {
            this.stats.setStatActualValue(statName, this.getStatMax(statName));
        } else {
            this.stats.setStatActualValue(statName, current + value);
        }
    }

    /**
     * 
     */
    @Override
    public void modifyMaxStat(final StatName statName, final int value) {
        int current = this.stats.getStatMax(statName);
        if ((current + value) < 0) {
            this.stats.setStatMaxValue(statName, 0);
        } else {
            this.stats.setStatMaxValue(statName, current + value);
        }
        modifyActualStat(statName, 0);
    }

    /**
     * 
     */
    @Override
    public Skill getSkills() {
        return this.skills;
    }

    /**
     * 
     */
    @Override
    public Stat getStats() {
        return this.stats;
    }

    /**
     * 
     */
    @Override
    public boolean isAlive() {
        return this.stats.getStatActual(StatName.HP) > 0;
    }
}
