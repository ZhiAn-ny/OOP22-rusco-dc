package it.unibo.ruscodc.model.gamecommand.quickcommand;

import java.util.Optional;

import it.unibo.ruscodc.utils.exception.ModelException;
import it.unibo.ruscodc.utils.outputinfo.InfoPayload;

/**
 * This command simply do nothing. Actor that create and execute this command 
 * simply deside to do nothing for this round
 */
public class DoNothing extends QuickActionAbs {

    /**
     * 
     */
    @Override
    public Optional<InfoPayload> execute() throws ModelException {
        return Optional.empty();
    }

    /**
     * 
     */
    @Override
    public String toString() {
        return "Stay immobile (wow, what a wonderful rubbish!)";
    }

}
