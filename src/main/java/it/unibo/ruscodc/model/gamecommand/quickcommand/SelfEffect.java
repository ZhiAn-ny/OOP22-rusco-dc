package it.unibo.ruscodc.model.gamecommand.quickcommand;

import java.util.Optional;

import it.unibo.ruscodc.model.effect.SingleTargetEffect;
import it.unibo.ruscodc.model.item.consumable.Consumable;
import it.unibo.ruscodc.model.outputinfo.InfoPayload;
import it.unibo.ruscodc.utils.exception.ModelException;

/**
 * Class that wrap other type of QuickAction action, tipically wrapped into Effect interface.
 */
public class SelfEffect extends QuickActionAbs {

    private final SingleTargetEffect toApply;

    /**
     * Create a command that use a consumable.
     * @param toUse the item to consume
     */
    public SelfEffect(final Consumable toUse) {
        this.toApply = toUse.consume();
    }

    public SelfEffect(final SingleTargetEffect toUse) {
        this.toApply = toUse;
    }

    /**
     * 
     */
    @Override
    public Optional<InfoPayload> execute() throws ModelException {
        toApply.applyEffect(this.getActor());
        return Optional.empty();
    }

}
