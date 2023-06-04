package it.unibo.ruscodc.model.item.equipement;

import it.unibo.ruscodc.model.actors.hero.Hero;
import it.unibo.ruscodc.model.item.Item;
import it.unibo.ruscodc.model.item.InventoryImpl.Slot;

/**
 * Interface for the Equipemt.
 */
public interface Equipement extends Item {

    /**
     * @param hero that wnts to equip the Equipement
     */
    void equip(Hero hero);

    /**
     * @param hero that wants to unequip the Equipement
     */
    void unequip(Hero hero);

    /**
     * @return the Slot occupied by the Equipement
     */
    Slot getSlot();
}
