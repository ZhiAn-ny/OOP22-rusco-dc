package it.unibo.ruscodc.model.interactable;

import it.unibo.ruscodc.model.item.Item;
import it.unibo.ruscodc.utils.Pair;

import java.util.Set;

/**
 * This class represents the chest of the game. It is a collection of item.
 */
public class Chest extends PickableItem {

    private static final long serialVersionUID = 3000L;

    /**
     * The constructor of this class build a chest which contains a collection of random objects.
     * @param itemSet
     * @param pos
     */
    public Chest(final Set<Item> itemSet, final Pair<Integer, Integer> pos) {
        super(itemSet, pos);
    }


    /**
     *
     *
     */
    @Override
    public String getPath() {
        return "file:src/main/resources/it/unibo/ruscodc/pickable_item/chest";
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
        return "chest";
    }
}
