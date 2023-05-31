package it.unibo.ruscodc.model.item.consumable;

import it.unibo.ruscodc.model.effect.SingleTargetEffect;
import it.unibo.ruscodc.model.item.Item;

public interface Consumable extends Item{
    SingleTargetEffect consume();
}
