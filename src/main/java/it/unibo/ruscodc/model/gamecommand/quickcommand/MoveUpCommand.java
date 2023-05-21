package it.unibo.ruscodc.model.gamecommand.quickcommand;

import it.unibo.ruscodc.utils.Pair;
import it.unibo.ruscodc.utils.Pairs;

/**
 * Implementation of abstract class <code>MoveBuilder</code>, usefull to move up the actor.
 */
public final class MoveUpCommand extends MoveCommand {

    /**
     * 
     */
    @Override
    protected Pair<Integer, Integer> computeNewPos() {
        return Pairs.computeUpPair(this.getActor().getPos());
    }

    @Override
    public String toString() {
        return super.toString() + " up";
    }
}
