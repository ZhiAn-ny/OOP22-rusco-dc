package it.unibo.ruscodc.model.item;

import java.util.List;

import it.unibo.ruscodc.model.item.InventoryImpl.Slot;

public interface Equipement extends Item {
    void equip();
    void unequip();
    List<Slot> getSlots();
}
