package it.unibo.ruscodc.model.item.equipement;

import it.unibo.ruscodc.model.actors.hero.Hero;
import it.unibo.ruscodc.model.item.Item;
import it.unibo.ruscodc.model.item.InventoryImpl.Slot;

public interface Equipement extends Item {
    void equip(Hero hero);
    void unequip(Hero hero);
    Slot getSlot();
}
