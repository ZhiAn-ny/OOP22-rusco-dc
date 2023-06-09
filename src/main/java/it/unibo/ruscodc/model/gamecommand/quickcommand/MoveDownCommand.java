package it.unibo.ruscodc.model.gamecommand.quickcommand;

import it.unibo.ruscodc.utils.Pair;
import it.unibo.ruscodc.utils.Pairs;

/**
 * Implementation of abstract class <code>MoveBuilder</code>, usefull to move down the actor.
 */
public final class MoveDownCommand extends MoveCommand {

    @Override
    protected Pair<Integer, Integer> computeNewPos() {
        return Pairs.computeDownPair(this.getActor().getPos());
    }

    @Override
    public String toString() {
        return super.toString() + " down";
    }
}
