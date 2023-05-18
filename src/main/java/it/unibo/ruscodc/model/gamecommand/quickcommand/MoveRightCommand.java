package it.unibo.ruscodc.model.gamecommand.quickcommand;

import it.unibo.ruscodc.utils.Pair;
import it.unibo.ruscodc.utils.Pairs;

/**
 * Implementation of abstract class <code>MoveBuilder</code>, usefull to move right the actor.
 */
public final class MoveRightCommand extends MoveCommand {

    @Override
    protected Pair<Integer, Integer> computeNewPos() {
        return Pairs.computeRightPair(this.getActor().getPos());
    }
}
