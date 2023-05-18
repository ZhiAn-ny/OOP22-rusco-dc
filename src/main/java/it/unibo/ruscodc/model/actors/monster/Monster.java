package it.unibo.ruscodc.model.actors.monster;

import java.util.List;

import it.unibo.ruscodc.model.actors.Actor;
import it.unibo.ruscodc.model.gamecommand.GameCommand;
import it.unibo.ruscodc.model.gamemap.Room;

public abstract interface Monster extends Actor {
    
    GameCommand behave(Room room, List<Actor> actors);
}
