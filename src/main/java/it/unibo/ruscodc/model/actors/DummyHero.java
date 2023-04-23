package it.unibo.ruscodc.model.actors;

import it.unibo.ruscodc.utils.Pair;

public final class DummyHero extends HeroImpl{

    public DummyHero(Pair<Integer, Integer> initialPos, String name) {
        super(name,initialPos);
    }

    @Override
    public String getInfo() {
        return "hi";
    }
    
}
