package it.unibo.ruscodc.model.actors.monster.behaviour;

public class BehaviourFactoryImpl implements BehaviourFactory {

    private final MovementBehaviourFactory MBFactory = new MovementBehaviourFactoryImpl();
    private final CombactBehaviourFactory CBFactory = new CombactBehaviourFactoryImpl();

    @Override
    public Behaviour makeMeleeAggressive() {
        return new BehaviourImpl(this.MBFactory.createAggressive(), CBFactory.createMelee());
    }

    @Override
    public Behaviour makeMeleeBrainless() {
        return new BehaviourImpl(this.MBFactory.createBrainless(), CBFactory.createMelee());
    }

    @Override
    public Behaviour makeRangedBrainless() {
        return new BehaviourImpl(this.MBFactory.createBrainless(), CBFactory.createRanged());
    }

    @Override
    public Behaviour makeRangedShy() {
        return new BehaviourImpl(this.MBFactory.createShy(), CBFactory.createRanged());
    }
    
}
