package it.unibo.ruscodc.model;

import it.unibo.ruscodc.utils.Pair;

public final class DummyHero extends Hero{

    public DummyHero(Pair<Integer, Integer> initialPos, String name) {
        super(initialPos, name);
    }

    @Override
    public String getInfo() {
        return "hi";
    }
    
}
