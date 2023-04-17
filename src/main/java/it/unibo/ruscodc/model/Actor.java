package it.unibo.ruscodc.model;

import it.unibo.ruscodc.utils.Pair;
import it.unibo.ruscodc.utils.gamecommand.GameCommand;

public abstract class Actor implements Entity {
    private Pair<Integer, Integer> currentPos;
    private final Skill skills = new SkillImpl();

    public Actor(Pair<Integer, Integer> initialPos) {
        this.currentPos = initialPos;
        /*this.skills*/
    }

    final public Pair<Integer, Integer> getPos() {
        return this.currentPos;
    };

    final public void setPos(Pair<Integer, Integer> newPos) {
        this.currentPos = newPos;
    };

    public abstract GameCommand act(int key);

}
