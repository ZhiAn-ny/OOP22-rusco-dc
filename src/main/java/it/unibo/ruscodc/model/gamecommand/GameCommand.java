package it.unibo.ruscodc.model.gamecommand;

import java.util.List;

import it.unibo.ruscodc.model.Actor;
import it.unibo.ruscodc.utils.exception.ModelException;

public interface GameCommand {
    
    void execute(Actor who, List<Actor> targets) throws ModelException;
    
}
