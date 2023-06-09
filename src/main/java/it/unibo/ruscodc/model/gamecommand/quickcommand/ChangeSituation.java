package it.unibo.ruscodc.model.gamecommand.quickcommand;

import java.util.Optional;

import it.unibo.ruscodc.utils.exception.ModelException;
import it.unibo.ruscodc.utils.outputinfo.InfoPayload;

/**
 * Defines a set of command that tipically alter the classic flow of the game.
 */
public abstract class ChangeSituation extends QuickActionAbs {

    /**
     * {@inheritDoc}
     */
    @Override
    public abstract Optional<InfoPayload> execute() throws ModelException;
}
