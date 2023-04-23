package it.unibo.ruscodc.model.actors;

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
        return "file:src/main/resources/it/unibo/ruscodc/hero_res/racoon-head.png";
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
