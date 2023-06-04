package it.unibo.ruscodc.model.item.consumable;

/**
 * Interface for a Factory that creates Xonsumable Item.
 */
public interface ConsumableFactory {

    /**
     * @return an Heakth Potion
     */
    Consumable createHPPotion();

    /**
     * @return an Action Potion
     */
    Consumable createAPotion();

    /**
     * @return a Strenght Potion
     */
    Consumable createSTRPotion();

    /**
     * @return a Dexterity Potion
     */
    Consumable createDEXPotion();

    /**
     * @return an Intellect Potion
     */
    Consumable createINTPotion();

    /**
     * @return a Mega Health Potion
     */
    Consumable createMegaHPPotion();

    /**
     * @return a Super Health Potion
     */
    Consumable createSuperHPPotion();
}
