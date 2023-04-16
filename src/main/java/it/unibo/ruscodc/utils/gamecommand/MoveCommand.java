package it.unibo.ruscodc.utils.gamecommand;

import java.util.List;

import it.unibo.ruscodc.model.Actor;
import it.unibo.ruscodc.model.gamemap.Room;
import it.unibo.ruscodc.utils.Pair;
import it.unibo.ruscodc.utils.exception.UnreacheblePos;


public abstract class MoveCommand implements GameCommand {

    private final static String ERR = "is already occupied or is out of the room";

    @Override
    public void execute(Actor who, List<Actor> targets, Room actualRoom) throws UnreacheblePos {
        Pair<Integer,Integer> newPos = computeNewPos(who);
        if(targets.stream().map(a -> a.getPos()).anyMatch(p -> p.equals(newPos)) || actualRoom.isInRoom(newPos)) {
            throw new UnreacheblePos("The position " + newPos.toString() + ERR);
        }
        who.setPos(newPos);
    }

    public abstract Pair<Integer,Integer> computeNewPos(Actor who);
    
}
