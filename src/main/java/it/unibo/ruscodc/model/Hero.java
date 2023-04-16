package it.unibo.ruscodc.model;

import javafx.util.Pair;

public abstract class Hero implements Actor{

    private Pair<Integer, Integer> currentPos;
    //private Inventory heroInv = new Inventory;

    private final void createHero() {
        createInv();
        createSkills();
    }

    @Override
    public Pair<Integer, Integer> getPos() {
        return this.getPos();
    }

    @Override
    public void act() {

    }

    @Override
    public String getInfo() {
        return null;
    }

    private void createInv() {

    }

    private void createSkills() {

    }
}
