package it.unibo.ruscodc.model.gamecommand;

import it.unibo.ruscodc.utils.exception.ModelException;

/**
 * Interface for game's command
 */
public interface GameCommand {
    /**
     * 
     * @return
     */
    boolean isReady();
    /**
     * 
     * @throws ModelException
     */
    void execute() throws ModelException;
}
