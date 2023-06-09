package it.unibo.ruscodc.model.actors.monster.behaviour;

/**
 * The interface of the Factory that creates the Movement Behaviour
 * for the Monsters.
 */
public interface MovementBehaviourFactory {

    /**
     * @return a Movement Behaviour for an "Aggressive" Monster,
     * which is a Monster that runs to the closest hero.
     */
    MovementBehaviour createAggressive();

    /**
     * @return a Movement Behaviour for a "Brainless" Monster,
     * which is a Monster that moves randomly around the room.
     */
    MovementBehaviour createBrainless();

    /**
     * @return a Movement Behaviour for a "Shy" monster,
     * which is a Monster that runs away from the closest
     * hero if it is too close to him.
     */
    MovementBehaviour createShy();
}
