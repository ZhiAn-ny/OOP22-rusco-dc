package it.unibo.ruscodc.model.actors.monster.behaviour;

/**
 * The interface of the Factory that
 * creates Behaviours for the Monsters.
 */
public interface BehaviourFactory {

    /**
     * @return a Behaviour for a Monster 
     * which is a Melee Fighter and has
     * an Aggressive Movement
     */
    Behaviour makeMeleeAggressive();

    /**
     * @return a Behaviour for a Monster 
     * which is a Melee Fighter and has
     * a Randomized Movement
     */
    Behaviour makeMeleeBrainless();

    /**
     * @return a Behaviour for a Monster 
     * which is a Ranged Fighter and has
     * a Randomized Movement
     */
    Behaviour makeRangedBrainless();

    /**
     * @return a Behaviour for a Monster 
     * which is a Ranged Fighter and has
     * a Shy Movement
     */
    Behaviour makeRangedShy();
}
