package it.unibo.ruscodc.model.gamecommand.quickcommand;

import it.unibo.ruscodc.utils.exception.ModelException;
import it.unibo.ruscodc.utils.exception.Undo;

public class AbortCommand extends ChangeSituation {

    @Override
    public void execute() throws ModelException {
        throw new Undo(""); // TODO - magari mettere una stringa
    }
    
}
