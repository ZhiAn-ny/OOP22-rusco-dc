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
     * @return
     */
    public List<Item> generateAllDrop();

    /**
     * //TODO
     * @return
     */
    public List<Item> generateRandomDrop();

    /**
     * //TODO
     * @return
     */
    public List<Item> generateConsumableDrop();

    /**
     * //TODO
     * @return
     */
    public List<Item> generateEquipDrop();

    /**
     * //TODO
     * @return
     */
    public List<Item> generateRandomConsumableDrop();

    /**
     * //TODO
     * @return
     */
    public List<Item> generateRandomEquipDrop();
    
}
