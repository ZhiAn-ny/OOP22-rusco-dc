package it.unibo.ruscodc.model.actors.monster.behaviour;

public interface BehaviourFactory {
    Behaviour makeMeleeAggressive();
    Behaviour makeMeleeBrainless();
    Behaviour makeMeleeShy();
    Behaviour makeRangedAggressive();
    Behaviour makeRangedShy();
}
