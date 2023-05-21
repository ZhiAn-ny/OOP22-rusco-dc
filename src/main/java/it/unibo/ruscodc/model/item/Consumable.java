package it.unibo.ruscodc.model.item;

import it.unibo.ruscodc.model.effect.SingleTargetEffect;

public interface Consumable extends Item{
    SingleTargetEffect consume();
}
