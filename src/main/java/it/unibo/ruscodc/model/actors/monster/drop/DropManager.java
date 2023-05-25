package it.unibo.ruscodc.model.actors.monster.drop;

import java.util.List;
import java.util.Set;

import it.unibo.ruscodc.model.item.Item;

/**
 * TODO
 */
public interface DropManager {
    
    /**
     * //TODO
     * @return TODO
     */
    public List<Item> generateAllDrop();

    /**
     * //TODO
     * @return TODO
     */
    public List<Item> generateRandomDrop();

    /**
     * //TODO
     * @return TODO
     */
    public List<Item> generateConsumableDrop();

    /**
     * //TODO
     * @return TODO
     */
    public List<Item> generateEquipDrop();

    /**
     * //TODO
     * @return TODO
     */
    public List<Item> generateRandomConsumableDrop();

    /**
     * //TODO
     * @return TODO
     */
    public List<Item> generateRandomEquipDrop();
    
}
