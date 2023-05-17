package it.unibo.ruscodc.model.effect;

import it.unibo.ruscodc.model.actors.Actor;
import it.unibo.ruscodc.model.actors.StatImpl.StatName;

public class AttackEffect implements Effect {

    private final int cost;

    public AttackEffect(int cost){
        this.cost = cost;
    }

    @Override
    public void applyEffect(Actor from, Actor to) {
        final int damage = from.getStatInfo(StatName.DMG);
        to.modifyStat(StatName.HP, - damage);
    }

    @Override
    public int getAPcost() {
        return cost;
    }
    
}
