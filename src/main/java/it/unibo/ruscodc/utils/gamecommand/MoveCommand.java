package it.unibo.ruscodc.utils.gamecommand;

import java.util.List;

import it.unibo.ruscodc.model.Actor;
import it.unibo.ruscodc.utils.Pair;


public abstract class MoveCommand implements GameCommand {

    @Override
    public void execute(Actor who, List<Actor> targets) {
        who.setPos(computeNewPos(who));
    }

    public abstract Pair<Integer,Integer> computeNewPos(Actor who);
    
}
