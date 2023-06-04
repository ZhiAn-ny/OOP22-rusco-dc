package it.unibo.ruscodc.model.item;

import java.util.List;

import it.unibo.ruscodc.model.actors.hero.Hero;
import it.unibo.ruscodc.model.item.equipement.Equipement;

/**
 * Interface for the Inventry of a Hero.
 */
public interface Inventory {
    /**
     * @param index of the Item 
     * @return the Item in that index
     */
    Item getItem(int index);

    /**
     * @param item that you want to add
     */
    void addItem(Item item);

    /**
     * @param index of the Item you want to remove
     */
    void removeItem(int index);

    /**
     * @return the list of equipped Items
     */
    List<Equipement> getEquipedItems();

    /**
     * @param equipement that you want to equip
     * @param hero that you want the equip the Item to
     */
    void equip(Equipement equipement, Hero hero);

    /**
     * @return the list of all the Items in the Inventory
     */
    List<Item> getAllItems();

    /**
     * @return if the inventory is Empty
     */
    boolean isEmpty();

    /**
     * @return if a Equipement Slot is occupied by something
     */
    int slotOccupied();
}
