package it.unibo.ruscodc.model;

import it.unibo.ruscodc.utils.Pair;

public abstract class Actor implements Entity {
    private Pair<Integer, Integer> currentPos;
    private Skills skills;

    public Actor(Pair<Integer, Integer> initialPos, Skills actorSkill) {
        this.currentPos = initialPos;
        this.skills = actorSkill;
    }

    final public Pair<Integer, Integer> getPos() {
        return this.currentPos;
    };

    final public void setPos(Pair<Integer, Integer> newPos) {
        this.currentPos = newPos;
    };

    public abstract void act();
}
