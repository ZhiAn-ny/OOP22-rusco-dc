package it.unibo.ruscodc.model;

import it.unibo.ruscodc.model.gamecommand.BuilderGameCommand;
import it.unibo.ruscodc.utils.Pair;

public abstract class Actor implements Entity {
    private final String name;
    private Pair<Integer, Integer> currentPos;
    private final Skill skills;
    private final Stat stats;

    public Actor( String name, Pair<Integer, Integer> initialPos) {
        this.name = name;
        this.currentPos = initialPos;
        this.skills = new SkillImpl();
        this.stats = new StatImpl();
    }

    final public Pair<Integer, Integer> getPos() {
        return this.currentPos;
    };

    final public void setPos(Pair<Integer, Integer> newPos) {
        this.currentPos = newPos;
    };

    final public Skill getSkills() {
        return this.skills;
    };

    final public String getName() {
        return this.name;
    }

    final public Stat geStats() {
        return this.stats;
    }

    final public void updateSKill(){
        //TODO: Aggiorna le skill con quelle specifiche del Mosto o dell'Eroe 
    };
    
    public abstract BuilderGameCommand act(int key);

}
