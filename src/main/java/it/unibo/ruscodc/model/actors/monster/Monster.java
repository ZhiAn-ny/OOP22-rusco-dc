package it.unibo.ruscodc.model.actors.monster;

import it.unibo.ruscodc.model.actors.Actor;
import it.unibo.ruscodc.model.gamecommand.BasicGameCommand;

public abstract interface Monster extends Actor {
    
    BasicGameCommand behave();
}
