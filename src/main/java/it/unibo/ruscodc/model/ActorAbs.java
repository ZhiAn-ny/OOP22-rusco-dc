package it.unibo.ruscodc.model;

import it.unibo.ruscodc.utils.Pair;

/**
 * 
 */
public abstract class ActorAbs implements Actor {
    private final String name;
    private Pair<Integer, Integer> currentPos;
    private final Skill skills;
    private final Stat stats;

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
    final public Skill getSkills() {
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
     * 
     */
    final public void updateSKill() {
        //TODO: Aggiorna le skill con quelle specifiche del Mosto o dell'Eroe 
    };

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
