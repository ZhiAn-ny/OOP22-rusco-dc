package it.unibo.ruscodc.model.actors.monster.behaviour;

/**
 * Implementation of the Behaviour Factory which return for each method a specific Behaviour.
 */
public class BehaviourFactoryImpl implements BehaviourFactory {

    private final MovementBehaviourFactory movementBehaviourFactory = new MovementBehaviourFactoryImpl();
    private final CombactBehaviourFactory combactBehaviourFactory = new CombactBehaviourFactoryImpl();

    /**
     * 
     */
    @Override
    public Behaviour makeMeleeAggressive() {
        return new BehaviourImpl(this.movementBehaviourFactory.createAggressive(), combactBehaviourFactory.createMelee());
    }

    /**
     * 
     */
    @Override
    public Behaviour makeMeleeBrainless() {
        return new BehaviourImpl(this.movementBehaviourFactory.createBrainless(), combactBehaviourFactory.createMelee());
    }

    /**
     * 
     */
    @Override
    public Behaviour makeRangedBrainless() {
        return new BehaviourImpl(this.movementBehaviourFactory.createBrainless(), combactBehaviourFactory.createRanged());
    }

    /**
     * 
     */
    @Override
    public Behaviour makeRangedShy() {
        return new BehaviourImpl(this.movementBehaviourFactory.createShy(), combactBehaviourFactory.createRanged());
    }
}
