package it.unibo.ruscodc.model.actors;

import it.unibo.ruscodc.model.actors.skill.Skill;
import it.unibo.ruscodc.model.actors.stat.Stat;
import it.unibo.ruscodc.model.actors.stat.StatImpl.StatName;
import it.unibo.ruscodc.utils.Pair;

/**
 * 
 */
public abstract class ActorAbs implements Actor {
    protected final String name;
    private Pair<Integer, Integer> currentPos;
    protected final Skill skills;
    
    public ActorAbs(String name, Pair<Integer, Integer> currentPos, Skill skills, Stat stats) {
        this.name = name;
        this.currentPos = currentPos;
        this.skills = skills;
        this.stats = stats;
    }

    protected final Stat stats;

    @Override
    public int getID() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getPath() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Pair<Integer, Integer> getPos() {
        return this.currentPos;
    }

    @Override
    public void setPos(Pair<Integer, Integer> newPos) {
        this.currentPos = newPos;
    }

    @Override
    public int getStatActual(StatName statName) {
        return this.stats.getStatActual(statName);
    }

    @Override
    public int getStatMax(StatName statName) {
        return this.stats.getStatMax(statName);
    }

    @Override
    public void modifyStat(StatName statName, int value) {
        int current = this.stats.getStatActual(statName);
        this.stats.setStatActualValue(statName, current + value);
    }

    @Override
    public Skill getSkills() {
        return this.skills;
    }

    @Override
    public boolean isAlive() {
        return this.stats.getStatActual(StatName.HP) > 0;
    }
}
