package it.unibo.ruscodc.model.interactable;

import it.unibo.ruscodc.model.item.Item;
import it.unibo.ruscodc.utils.Pair;

import java.util.Set;

/**
 * This class represent the items the player takes from a monster when he successfully kills it.
 */
public class Drop extends PickableItem {

    private static final long serialVersionUID = 3002L;
    private static final String NAME = "drop";

    /**
     * Create this type of pickable item.
     * @param itemSet items that are wrapped into this drop
     * @param pos where they are
    */
    public Drop(final Set<Item> itemSet, final Pair<Integer, Integer> pos) {
        super(itemSet, pos);
    }

    /**
     *
     */
    @Override
    public String getPath() {
        return "file:src/main/resources/it/unibo/ruscodc/pickable_item/drop";
    }

    /**
     * 
     */
    @Override
    public boolean isTransitable() {
        return false;
    }

    /**
     * 
     */
    @Override
    public String getName() {
        return NAME;
    }
}
