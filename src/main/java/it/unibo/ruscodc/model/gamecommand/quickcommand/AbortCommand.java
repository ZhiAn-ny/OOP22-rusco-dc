package it.unibo.ruscodc.model.gamecommand.quickcommand;

import java.util.Optional;

import it.unibo.ruscodc.model.outputinfo.InfoPayload;
import it.unibo.ruscodc.utils.exception.ModelException;
import it.unibo.ruscodc.utils.exception.Undo;

public class AbortCommand extends ChangeSituation { //TODO - credo che eliminerò questa classe

    /**
     * 
     */
    @Override
    public Optional<InfoPayload> execute() throws ModelException {
        throw new Undo(""); // TODO - magari mettere una stringa
    }
    
}
