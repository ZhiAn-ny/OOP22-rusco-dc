package it.unibo.ruscodc.model.gamecommand;

import it.unibo.ruscodc.utils.Pair;

/**
 * Implementation of abstract class <code>MoveBuilder</code>, usefull to move right the actor.
 */
public final class MoveRightBuilder extends MoveBuilder {

    @Override
    protected Pair<Integer, Integer> computeNewPos() {
        final Pair<Integer, Integer> actPos = this.getActor().getPos();
        return new Pair<Integer, Integer>(actPos.getX(), actPos.getY() + 1);
    }
}
