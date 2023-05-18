package it.unibo.ruscodc.model.gamecommand.quickcommand;

import it.unibo.ruscodc.utils.exception.ChangeFloorException;
import it.unibo.ruscodc.utils.exception.ModelException;

public class ChangeFloor extends ChangeSituation{

    @Override
    public void execute() throws ModelException {
        throw new ChangeFloorException("The floor is changing");

    }
}
