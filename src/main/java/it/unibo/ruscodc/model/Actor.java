package it.unibo.ruscodc.model;

import it.unibo.ruscodc.model.gamecommand.BuilderGameCommand;
import it.unibo.ruscodc.utils.Pair;

public abstract class Actor implements Entity {
    private Pair<Integer, Integer> currentPos;
    private final Skill skills;
    private final String name;

    public Actor(Pair<Integer, Integer> initialPos, String name) {
        this.currentPos = initialPos;
        this.skills = new SkillImpl();
        this.name = name;
    }

    final public Pair<Integer, Integer> getPos() {
        return this.currentPos;
    };

    final public void setPos(Pair<Integer, Integer> newPos) {
        this.currentPos = newPos;
    };

    final protected Skill getSkills() {
        return this.skills;
    };

    public abstract BuilderGameCommand act(int key);
    public abstract void updateSKill();

}
