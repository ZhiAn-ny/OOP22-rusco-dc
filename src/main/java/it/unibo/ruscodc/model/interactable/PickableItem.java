package it.unibo.ruscodc.model.interactable;

import it.unibo.ruscodc.model.gamecommand.GameCommand;
import it.unibo.ruscodc.model.gamecommand.quickcommand.FillInventory;
import it.unibo.ruscodc.model.item.Item;
import it.unibo.ruscodc.utils.Pair;

import java.util.Set;


/**
 *
 */
public abstract class PickableItem extends InteractableAbs {

    private final Set<Item> loot;

    /**
     *
     * @param itemSet
     * @param pos
     */
    protected PickableItem(Set<Item> itemSet, Pair<Integer, Integer> pos){
        super(pos);
        this.loot = itemSet;
    }


    /**
     *
     * @return
     */
    @Override
    public abstract String getPath();

    /**
     *
     * @return
     */
    @Override
    public abstract String getName();

    /**
     *
     * @return
     */
    @Override
    public GameCommand interact() {
        return new FillInventory(loot);
    }
}
