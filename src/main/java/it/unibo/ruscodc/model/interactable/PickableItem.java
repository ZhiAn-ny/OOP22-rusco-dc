package it.unibo.ruscodc.model.interactable;

import it.unibo.ruscodc.model.gamecommand.GameCommand;
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
    public PickableItem(Set<Item> itemSet, Pair<Integer, Integer> pos){
        super(pos);
        this.loot = itemSet;
    }

    /**
     *
     * @return
     */
    @Override
    public abstract String getInfo();

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
    public String getName() {
        return null;
    }

    /**
     *
     * @return
     */
    @Override
    public GameCommand interact() {
        return null;
    }
}
