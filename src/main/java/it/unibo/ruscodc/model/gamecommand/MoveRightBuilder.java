package it.unibo.ruscodc.model.gamecommand;

import it.unibo.ruscodc.utils.Pair;

public final class MoveRightBuilder extends MoveBuilder {

    @Override
    protected Pair<Integer, Integer> computeNewPos() {
        Pair<Integer, Integer> actPos = this.getActPos();
        return new Pair<Integer,Integer>(actPos.getX(), actPos.getY()+1);
    }
    
}
