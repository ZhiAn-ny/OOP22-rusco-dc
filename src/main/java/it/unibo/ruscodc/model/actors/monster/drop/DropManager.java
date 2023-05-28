package it.unibo.ruscodc.model.actors.monster.drop;

import java.util.List;

import it.unibo.ruscodc.model.item.Item;

/**
 * TODO.
 */
public interface DropManager {

    /**
     * //TODO.
     * @return TODO.
     */
    List<Item> generateAllDrop();

    /**
     * //TODO.
     * @return TODO.
     */
    List<Item> generateRandomDrop();

    /**
     * //TODO.
     * @return TODO.
     */
    List<Item> generateConsumableDrop();

    /**
     * //TODO.
     * @return TODO.
     */
    List<Item> generateEquipDrop();

    /**
     * //TODO.
     * @return TODO.
     */
    List<Item> generateRandomConsumableDrop();

    /**
     * //TODO.
     * @return TODO.
     */
    List<Item> generateRandomEquipDrop();

}
