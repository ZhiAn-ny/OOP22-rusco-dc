package it.unibo.ruscodc.model.gamecommand.quickcommand;

import java.util.Optional;

import it.unibo.ruscodc.model.item.Consumable;
import it.unibo.ruscodc.model.outputinfo.InfoPayload;
import it.unibo.ruscodc.utils.exception.ModelException;

/**
 * Class that wrap other type of QuickAction action, tipically wrapped into Effect interface.
 */
public class SelfEffect extends QuickActionAbs {

    private final Consumable usedItem;

    public SelfEffect(final Consumable toUse) {
        this.usedItem = toUse;
    }

    /**
     * 
     */
    @Override
    public Optional<InfoPayload> execute() throws ModelException {
        // TODO Da fare: aspettare Item
        throw new UnsupportedOperationException("Unimplemented method 'execute'");
    }

    @Override
    public String toString() {
        return "Use " + usedItem.getName() + " consumable";
    }

    
}
