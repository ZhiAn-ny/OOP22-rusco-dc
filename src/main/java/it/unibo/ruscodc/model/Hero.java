package it.unibo.ruscodc.model;

import java.util.HashMap;
import java.util.Map;

import it.unibo.ruscodc.model.gamecommand.BuilderGameCommand;
import it.unibo.ruscodc.utils.Pair;

public abstract class Hero extends Actor {

    private final Map<Integer, BuilderGameCommand> allSkills = new HashMap<>();

    public Hero(String name, Pair<Integer, Integer> initialPos) {
        super(name,initialPos);
    }

    @Override
    public BuilderGameCommand act(int key) {
        BuilderGameCommand builderGameCommand = this.getSkills().getAction(key);
        builderGameCommand.setActor(this);
        return builderGameCommand;
    }

    

}
