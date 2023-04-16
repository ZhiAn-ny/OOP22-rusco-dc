package it.unibo.ruscodc.utils.gamecommand;

import it.unibo.ruscodc.model.Actor;
import it.unibo.ruscodc.utils.Pair;

public final class LeftMovement extends MoveCommand {

    @Override
    public Pair<Integer, Integer> computeNewPos(Actor who) {
        Pair<Integer, Integer> actPos = who.getPos();
        return new Pair<Integer,Integer>(actPos.getX(), actPos.getY()-1);
    }
    
}
