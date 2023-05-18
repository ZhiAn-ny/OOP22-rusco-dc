package it.unibo.ruscodc.model.gamecommand.playercommand;

import it.unibo.ruscodc.model.actors.Actor;
import it.unibo.ruscodc.model.gamecommand.ComplexActionAbs;
import it.unibo.ruscodc.utils.Pair;

public abstract class NoIACommand extends ComplexActionAbs {

    /**
     * 
     */
    @Override
    public Pair<Integer, Integer> getActorPos() {
        throw new UnsupportedOperationException(this.getGlobalErrMess());
    }

    /**
     * 
     */
    @Override
    public void setCursePos(Pair<Integer, Integer> newPos) {
        throw new UnsupportedOperationException(this.getGlobalErrMess());
    }

    @Override
    public boolean isTargetInRange(Actor target) {
        throw new UnsupportedOperationException(this.getGlobalErrMess());
    }

    @Override
    public int getAPCost() {
        throw new UnsupportedOperationException(this.getGlobalErrMess());
    }

}
