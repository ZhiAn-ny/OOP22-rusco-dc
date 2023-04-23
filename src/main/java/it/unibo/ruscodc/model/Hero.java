package it.unibo.ruscodc.model;

import it.unibo.ruscodc.model.gamecommand.BuilderGameCommand;

public interface Hero {
    
    /**
     * @return
     */
    BuilderGameCommand act(int key);
}
