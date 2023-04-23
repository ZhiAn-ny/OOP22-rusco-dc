package it.unibo.ruscodc.model.actors;

import it.unibo.ruscodc.model.gamecommand.BuilderGameCommand;

public interface Hero extends Actor {
    
    /**
     * @return
     */
    BuilderGameCommand act(int key);
}
