package it.unibo.ruscodc.model.actors.monster;

import it.unibo.ruscodc.model.actors.Actor;
import it.unibo.ruscodc.model.gamecommand.GameCommand;

public abstract interface Monster extends Actor {
    
    GameCommand behave();
}
