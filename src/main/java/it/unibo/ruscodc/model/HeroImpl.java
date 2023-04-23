package it.unibo.ruscodc.model;

import java.util.HashMap;
import java.util.Map;

import it.unibo.ruscodc.model.gamecommand.BuilderGameCommand;
import it.unibo.ruscodc.utils.Pair;

public class HeroImpl extends ActorAbs implements Hero {

    private final Map<Integer, BuilderGameCommand> allSkills = new HashMap<>();
    
    public HeroImpl(String name, Pair<Integer, Integer> initialPos) {
        super(name,initialPos);
    }

    
    @Override
    public String getInfo() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getInfo'");
    }
    
    @Override
    public String getPath() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPath'");
    }

    @Override
    public void loadStats() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'loadStats'");
    }
    
    @Override
    public void loadSkills() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'loadSkills'");
    }

    public BuilderGameCommand act(int key) {
        BuilderGameCommand builderGameCommand = this.getSkills().getAction(key);
        builderGameCommand.setActor(this);
        return builderGameCommand;
    }

}
