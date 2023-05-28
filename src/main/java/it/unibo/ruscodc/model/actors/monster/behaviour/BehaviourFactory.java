package it.unibo.ruscodc.model.actors.monster.behaviour;

public interface BehaviourFactory {
    Behaviour makeMeleeAggressive();
    Behaviour makeMeleeBrainless();
    Behaviour makeRangedBrainless();
    Behaviour makeRangedShy();
}
