package it.unibo.ruscodc.model.interactable;

import it.unibo.ruscodc.model.gamecommand.GameCommand;
import it.unibo.ruscodc.model.item.Item;
import it.unibo.ruscodc.utils.Pair;

import java.util.Set;

public abstract class PickableItem extends InteractableAbs {

    private final Set<Item> loot;
    public PickableItem(Set<Item> itemSet, Pair<Integer, Integer> pos){
        super(pos);
        this.loot = itemSet;
    }


    @Override
    public String getInfo() {
        return null;
    }

    @Override
    public abstract String getPath();

    @Override
    public String getName() {
        return null;
    }

    @Override
    public GameCommand interact() {
        return null;
    }
}
