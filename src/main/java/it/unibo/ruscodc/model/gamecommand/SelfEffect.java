package it.unibo.ruscodc.model.gamecommand;

import it.unibo.ruscodc.utils.exception.ModelException;

/**
 * Class that wrap other type of QuickAction action, tipically wrapped into Effect interface.
 */
public class SelfEffect extends QuickActionBuilder {

    /**
     * 
     */
    @Override
    public void execute() throws ModelException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'execute'");
    }
}