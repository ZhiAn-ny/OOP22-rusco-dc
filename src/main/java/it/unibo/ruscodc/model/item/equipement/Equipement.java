package it.unibo.ruscodc.model.item.equipement;

import it.unibo.ruscodc.model.actors.Actor;
import it.unibo.ruscodc.model.item.Item;
import it.unibo.ruscodc.model.item.InventoryImpl.Slot;

public interface Equipement extends Item {
    void equip(Actor actor);
    void unequip(Actor actor);
    Slot getSlot();
}
