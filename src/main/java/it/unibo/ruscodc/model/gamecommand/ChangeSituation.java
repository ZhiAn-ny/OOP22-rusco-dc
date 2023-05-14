package it.unibo.ruscodc.model.gamecommand;

import it.unibo.ruscodc.utils.exception.ModelException;

public abstract class ChangeSituation extends QuickActionBuilder {

    @Override
    public abstract void execute() throws ModelException;
}
