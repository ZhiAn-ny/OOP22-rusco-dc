package it.unibo.ruscodc.utils.gamecommand;

import java.util.List;

import it.unibo.ruscodc.model.Actor;
import it.unibo.ruscodc.model.gamemap.Room;
import it.unibo.ruscodc.utils.exception.ModelException;

public interface GameCommand {
    
    void execute(Actor who, List<Actor> targets, Room actualRoom) throws ModelException;
}
