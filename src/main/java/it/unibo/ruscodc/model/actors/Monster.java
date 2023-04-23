package it.unibo.ruscodc.model.actors;

public abstract class Monster extends ActorAbs {
    
    //private final Behave behave;

    public Monster(String name, it.unibo.ruscodc.utils.Pair<Integer, Integer> initialPos) {
        super(name, initialPos);
    }
}
