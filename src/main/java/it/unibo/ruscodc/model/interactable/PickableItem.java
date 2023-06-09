package it.unibo.ruscodc.model.interactable;

import it.unibo.ruscodc.model.gamecommand.GameCommand;
import it.unibo.ruscodc.model.gamecommand.quickcommand.FillInventory;
import it.unibo.ruscodc.model.item.Item;
import it.unibo.ruscodc.utils.Pair;

import java.util.Set;


/**
 * Define a common implementation about all interactable objects 
 * that an actor can collect and add into this inventory.
 */
public abstract class PickableItem extends InteractableAbs {

    private final Set<Item> loot;

    /**
     * Create a generic "pickable item".
     * @param itemSet the amount of object that are effectily pickable
     * @param pos where they are
     */
    protected PickableItem(final Set<Item> itemSet, final Pair<Integer, Integer> pos) {
        super(pos);
        this.loot = itemSet;
    }


    /**
     * 
     */
    @Override
    public abstract String getPath();

    /**
     *
     */
    @Override
    public abstract String getName();

    /**
     *
     */
    @Override
    public GameCommand interact() {
        return new FillInventory(loot);
    }
}
