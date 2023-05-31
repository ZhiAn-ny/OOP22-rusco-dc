package it.unibo.ruscodc.model.actors.monster.behaviour;

public interface CombactBehaviourFactory {
    CombactBehaviour createMelee();
    CombactBehaviour createRanged();
}
