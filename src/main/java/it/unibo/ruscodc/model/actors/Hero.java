package it.unibo.ruscodc.model.actors;

import it.unibo.ruscodc.model.gamecommand.BuilderGameCommand;
import it.unibo.ruscodc.utils.GameControl;

public interface Hero extends Actor {
    
    /**
     * @return
     */
    BuilderGameCommand act(GameControl key);
}
