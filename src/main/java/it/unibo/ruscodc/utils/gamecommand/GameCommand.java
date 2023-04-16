package it.unibo.ruscodc.utils.gamecommand;

import java.util.List;

import it.unibo.ruscodc.model.Actor;

public interface GameCommand {
    
    void execute(Actor who, List<Actor> targets);
}
