package it.unibo.ruscodc.model;

import it.unibo.ruscodc.model.gamecommand.BuilderGameCommand;

public abstract class Monster extends Actor {
    
    //private final Behave behave;

    public Monster(String name, it.unibo.ruscodc.utils.Pair<Integer, Integer> initialPos) {
        super(name, initialPos);
        this.setBehave();
    }

    @Override
    public BuilderGameCommand act(int key) {
        return null;
    }

    private void setBehave() {
        //TODO: Imposta il Behaviour del Mostro;
    }
}
