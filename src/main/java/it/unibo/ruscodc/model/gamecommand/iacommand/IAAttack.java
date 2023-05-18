package it.unibo.ruscodc.model.gamecommand.iacommand;

import it.unibo.ruscodc.model.actors.Actor;
import it.unibo.ruscodc.model.effect.Effect;
import it.unibo.ruscodc.model.range.Range;
import it.unibo.ruscodc.utils.Pair;
import it.unibo.ruscodc.utils.exception.ModelException;

public class IAAttack extends NoPlayerCommand {

    private final Range range;
    private final Range splash;
    private final Effect actionToPerform;

    public IAAttack(Range r, Range s, Effect eff) {
        this.range = r;
        this.splash = s;
        this.actionToPerform = eff;
    }

    /**
     * 
     */
    @Override
    public Pair<Integer, Integer> getActorPos() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getActorPos'");
    }

    /**
     * 
     */
    @Override
    public void setCursePos(Pair<Integer, Integer> newPos) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setCursePos'");
    }

    @Override
    public void execute() throws ModelException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'execute'");
    }

    @Override
    public boolean isTargetInRange(Actor target) {
        return this.range.isInRange(this.getActor().getPos(), target.getPos(), target.getPos(), this.getRoom());
    }

    @Override
    public int getAPCost() {
        return actionToPerform.getAPcost();
    }
    
}
