package it.unibo.ruscodc.model.gamecommand.quickcommand;

import java.util.Optional;

import it.unibo.ruscodc.model.outputinfo.InfoPayload;
import it.unibo.ruscodc.utils.exception.ModelException;

/**
 * TODO documentazione!.
 */
public abstract class ChangeSituation extends QuickActionAbs {

    @Override
    public abstract Optional<InfoPayload> execute() throws ModelException;
}
