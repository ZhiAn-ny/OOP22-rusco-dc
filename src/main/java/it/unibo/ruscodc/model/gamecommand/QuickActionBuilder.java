package it.unibo.ruscodc.model.gamecommand;

import it.unibo.ruscodc.utils.exception.ModelException;

/**
 * This abstract class defines that all the other class that extend this class will wrap an
 * action that tipically can do without any type of control (neither by player or IA).
 * So logically the wrapped command can be executed
 */ //TODO-a gradle non piace l'andata a capo
public abstract class QuickActionBuilder extends BuilderGameCommandImpl implements GameCommand {

    /**
     * 
     */
    @Override
    public boolean isReady() {
        return true;
    }

    @Override
    public abstract void execute() throws ModelException;
}
