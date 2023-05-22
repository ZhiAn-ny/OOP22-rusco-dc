package it.unibo.ruscodc.model.item.Consumable;

public interface ConsumableFactory {
    Consumable createHPPotion();
    Consumable createAPotion();
    Consumable createSTRPotion();
}
