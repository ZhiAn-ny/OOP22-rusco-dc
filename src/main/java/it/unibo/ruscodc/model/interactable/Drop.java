package it.unibo.ruscodc.model.interactable;

import it.unibo.ruscodc.model.item.Item;
import it.unibo.ruscodc.utils.Pair;

import java.util.Set;

/**
 * This class represent the items the player takes from a monster when he successfully kills it.
 */
public class Drop extends PickableItem{

    private static final long serialVersionUID = 3002L;

    /**
     * This is a constructor class.
     * @param itemSet
     * @param pos
     */
    public Drop(Set<Item> itemSet, Pair<Integer, Integer> pos) {
        super(itemSet, pos);
    }



    /**
     *
     */
    @Override
    public String getPath() {
        return "file:src/main/resources/it/unibo/ruscodc/pickable_item/drop";
    }

    @Override
    public boolean isTransitable() {
        return false;
    }



    @Override
    public String getName() {
        return "drop";
    }
}
