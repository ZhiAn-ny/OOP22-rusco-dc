package it.unibo.ruscodc.model.item.consumable;

public interface ConsumableFactory {
    Consumable createHPPotion();
    Consumable createAPotion();
    Consumable createSTRPotion();
}
