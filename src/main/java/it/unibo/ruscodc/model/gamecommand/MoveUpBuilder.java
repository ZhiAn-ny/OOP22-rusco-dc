package it.unibo.ruscodc.model.gamecommand;

import it.unibo.ruscodc.utils.Pair;

public final class MoveUpBuilder extends MoveBuilder {

    @Override
    protected Pair<Integer, Integer> computeNewPos() {
        Pair<Integer, Integer> actPos = this.getActPos();
        return new Pair<Integer,Integer>(actPos.getX()-1, actPos.getY());
    }
    
}
