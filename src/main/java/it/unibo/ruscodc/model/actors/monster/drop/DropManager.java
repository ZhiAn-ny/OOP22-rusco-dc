package it.unibo.ruscodc.model.actors.monster.drop;

import java.util.List;

import it.unibo.ruscodc.model.item.Item;

/**
 * Interface that wrap the concept of drop. Instead of pass directly a Set of Item, 
 * it is preferred created a class that help get some item instead of other.
 */
public interface DropManager {

    /**
     * Gived before a collection of item, get all of this items.
     * @return a list of item, as described before
     */
    List<Item> generateAllDrop();

    /**
     * Gived before a collection of item, get a random partitions of this items.
     * @return a list of item, as described before
     */
    List<Item> generateRandomDrop();

    /**
     * Gived before a collection of item, get all items that are consumable.
     * @return a list of item, as described before
     */
    List<Item> generateConsumableDrop();

    /**
     * Gived before a collection of item, get all items that are equippable.
     * @return a list of item, as described before
     */
    List<Item> generateEquipDrop();

    /**
     * Gived before a collection of item, get a randomic set of items that are consumable.
     * @return a list of item, as described before
     */
    List<Item> generateRandomConsumableDrop();

    /**
     * Gived before a collection of item, get a randomic set of items that are equippable.
     * @return a list of item, as described before
     */
    List<Item> generateRandomEquipDrop();

}
