package it.unibo.ruscodc.model.gamecommand.quickcommand;

import it.unibo.ruscodc.utils.exception.ModelException;

/**
 * This command simply do nothing. Actor that create and execute this command 
 * simply deside to do nothing for this round
 */
public class DoNothing extends QuickActionAbs {

    @Override
    public void execute() throws ModelException {
    }
    
}
