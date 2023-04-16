package it.unibo.ruscodc.model;

import javafx.util.Pair;

public abstract class Monster implements Actor {
    @Override
    public Pair<Integer, Integer> getPos() {
        return null;
    }

    @Override
    public void act() {

    }

    @Override
    public String getInfo() {
        return null;
    }
}
