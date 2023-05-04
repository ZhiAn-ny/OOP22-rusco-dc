package it.unibo.ruscodc.model.actors;

import it.unibo.ruscodc.utils.Pair;

/**
 * 
 */
public abstract class ActorAbs implements Actor {
    protected final String name;
    private Pair<Integer, Integer> currentPos;
    protected final Skill skills;
    protected final Stat stats;

    /**
     * @param name
     * @param initialPos
     */
    public ActorAbs(final String name, final Pair<Integer, Integer> initialPos) {
        this.name = name;
        this.currentPos = initialPos;
        this.skills = new SkillImpl();
        this.stats = new StatImpl();
    }

    /**
     * @return foo
     */
    final public Pair<Integer, Integer> getPos() {
        return this.currentPos;
    };

    /**
     * @param newPos foo
     */
    final public void setPos(final Pair<Integer, Integer> newPos) {
        this.currentPos = newPos;
    };

    /**
     * @return foo
     */
    final public Skill getFieldSkill() {
        return this.skills;
    };

    /**
     * @return foo
     */
    final public String getName() {
        return this.name;
    }

    /**
     * @return foo
     */
    final public Stat getStats() {
        return this.stats;
    }

    /**
     * @return foo
     */
    final public void load() {
        loadSkills();
        loadStats();
    }

    public abstract void loadStats();
    public abstract void loadSkills();

}
