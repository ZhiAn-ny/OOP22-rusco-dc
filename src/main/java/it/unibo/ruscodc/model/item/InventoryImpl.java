package it.unibo.ruscodc.model.item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.unibo.ruscodc.model.actors.Actor;
import it.unibo.ruscodc.model.item.equipement.Equipement;

/**
 * Implementation of Inventory that is used by the 
 * Hero to store Items.
 */
public class InventoryImpl implements Inventory {

    /**
     * Slot used in the Equipement.
     */
    public enum Slot {
        /**
         * Head slot for Equipement.
         */
        HEAD,
        /**
         * Armor slot for Equipement.
         */
        ARMOR,
        /**
         * Weapon slot for Equipement.
         */
        WEAPON,
        /**
         * Special slot for Equipement.
         */
        SPECIAL;
    }

    private final List<Item> bag;
    private final Map<Slot, Equipement> equipement;

    /**
     * Basic Constructor for the Inventory
     */
    public InventoryImpl() {
        this.bag = new ArrayList<>();
        this.equipement = new HashMap<>();
    }

    /**
     * 
     */
    @Override
    public Item getItem(final int index) {
        return this.bag.get(index);
    }

    /**
     * 
     */
    @Override
    public List<Equipement> getEquipedItems() {
        return equipement
            .entrySet()
            .stream()
            .map(a -> a.getValue())
            .toList();
    }

    /**
     * 
     */
    @Override
    public void equip(final Equipement equip, final Actor actor) {
        this.equipement.get(equip.getSlot()).unequip(actor);
        this.equipement.put(equip.getSlot(), equip);
        equip.equip(actor);
    }

    /**
     * 
     */
    @Override
    public List<Item> getAllItems() {
        return List.copyOf(this.bag);
    }

    /**
     * 
     */
    @Override
    public void addItem(final Item item) {
        this.bag.add(item);
    }

    /**
     * 
     */
    @Override
    public void removeItem(final int index) {
        this.bag.remove(index);
    }

    /**
     * 
     */
    @Override
    public boolean isEmpty() {
        return this.bag.isEmpty();
    }

    /**
     * 
     */
    @Override
    public int slotOccupied() {
        return this.bag.size();
    }
}
