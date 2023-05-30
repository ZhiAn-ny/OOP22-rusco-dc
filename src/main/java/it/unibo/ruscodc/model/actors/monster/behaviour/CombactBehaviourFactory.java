package it.unibo.ruscodc.model.actors.monster.behaviour;

/**
 * The interface of the Factory that creates the
 * Combact Behaviour for the Monsters.
 */
public interface CombactBehaviourFactory {

    /**
     * @return a Melee based Combact Behaviour
     */
    CombactBehaviour createMelee();

    /**
     * @return a Ranged based Comact Behaviour
     */
    CombactBehaviour createRanged();
}
