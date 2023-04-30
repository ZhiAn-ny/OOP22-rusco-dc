package it.unibo.ruscodc.model.gamecommand;

import it.unibo.ruscodc.utils.exception.ModelException;

/**
 * Interface for game's command. Defines method that all builder's command must to implement
 */
public interface GameCommand {
    /**
     * Check if the wrapped command is logically ready to be executed.
     * @return the relative boolean value
     */
    boolean isReady();

    /**
     * Execute the wrapped command.
     * Even if the command can logically run, maybe there is a problem
     * For example, if the Actor wants to move up, but it cannot do it because there is a wall
     * @throws ModelException 
     */
    void execute() throws ModelException;
}
