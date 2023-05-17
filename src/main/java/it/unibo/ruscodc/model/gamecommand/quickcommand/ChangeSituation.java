package it.unibo.ruscodc.model.gamecommand.quickcommand;

import it.unibo.ruscodc.utils.exception.ModelException;

public abstract class ChangeSituation extends QuickActionAbs {

    @Override
    public abstract void execute() throws ModelException;
}
