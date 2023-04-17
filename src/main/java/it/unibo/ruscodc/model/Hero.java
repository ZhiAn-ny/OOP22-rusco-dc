package it.unibo.ruscodc.model;

import it.unibo.ruscodc.utils.Pair;

public abstract class Hero extends Actor {

    private Pair<Integer, Integer> currentPos;
    // private final Inventory inventory;

    public Hero(Pair<Integer, Integer> initialPos, Skill actorSkill) {
        super(initialPos, actorSkill);
        this.createHero();
    }

    private final void createHero() {
        //this.createInv();
        this.createSkills();
    }

    @Override
    public void act(int key) {
        k
    }

    @Override
    public String getInfo() {
        return null;
    }

    /*private void createInv() {
        this.
    }*/

    private void createSkills() {

    }
}
