package it.unibo.ruscodc.model.item;

public interface ConsumableFactory {
    Consumable createHPPotion();
    Consumable createAPotion();
    Consumable createSTRPotion();
}
