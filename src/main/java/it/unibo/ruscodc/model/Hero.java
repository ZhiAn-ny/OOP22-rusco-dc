package it.unibo.ruscodc.model;

import it.unibo.ruscodc.model.gamecommand.BuilderGameCommand;
import it.unibo.ruscodc.utils.Pair;

public abstract class Hero extends Actor {

    public Hero(Pair<Integer, Integer> initialPos) {
        super(initialPos);
        this.updateSKill();
    }

    @Override
    public void updateSKill() {
        //Usa l'hashcode per caricare le skills dopo un level up o alla creazione
        //TODO
    }

    @Override
    public BuilderGameCommand act(int key) {
        BuilderGameCommand builderGameCommand = this.getSkills().getAction(key);
        builderGameCommand.setActor(this);
        return builderGameCommand;
    }

    

}
