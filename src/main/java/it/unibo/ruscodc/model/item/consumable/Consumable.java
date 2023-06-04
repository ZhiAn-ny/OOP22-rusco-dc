package it.unibo.ruscodc.model.item.consumable;

import it.unibo.ruscodc.model.effect.SingleTargetEffect;
import it.unibo.ruscodc.model.item.Item;

/**
 * Interface for the Consumable Item.
 */
public interface Consumable extends Item {
    /**
     * @return the Effect to apply
     */
    SingleTargetEffect consume();
}
