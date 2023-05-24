package it.unibo.ruscodc.model.actors.monster.behaviour;

public class BehaviourFactoryImpl implements BehaviourFactory {

    private final MovementBehaviourFactory MBFactory = new MovementBehaviourFactoryImpl();
    private final CombactBehaviourFactory CBFactory = new CombactBehaviourFactoryImpl();

    @Override
    public Behaviour makeMeleeAggressive() {
        return new BehaviourImpl(this.MBFactory.createAggressive(), CBFactory.Melee());
    }

    @Override
    public Behaviour makeMeleeBrainless() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'makeMeleeBrainless'");
    }

    @Override
    public Behaviour makeMeleeShy() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'makeMeleeShy'");
    }

    @Override
    public Behaviour makeRangedAggressive() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'makeRangedAggressive'");
    }

    @Override
    public Behaviour makeRangedShy() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'makeRangedShy'");
    }
    
}
