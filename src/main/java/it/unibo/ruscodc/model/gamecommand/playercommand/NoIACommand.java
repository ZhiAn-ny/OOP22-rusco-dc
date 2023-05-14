package it.unibo.ruscodc.model.gamecommand.playercommand;

import it.unibo.ruscodc.model.gamecommand.ComplexActionBuilder;
import it.unibo.ruscodc.utils.Pair;

public abstract class NoIACommand extends ComplexActionBuilder {

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

}
