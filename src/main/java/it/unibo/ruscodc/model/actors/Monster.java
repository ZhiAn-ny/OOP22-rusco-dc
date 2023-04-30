package it.unibo.ruscodc.model.actors;

import java.lang.module.ModuleDescriptor.Builder;

import it.unibo.ruscodc.model.gamecommand.BuilderGameCommand;

public abstract interface Monster extends Actor {
    
    BuilderGameCommand behave();
}
