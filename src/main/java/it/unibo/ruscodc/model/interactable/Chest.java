package it.unibo.ruscodc.model.interactable;

import it.unibo.ruscodc.model.item.Item;
import it.unibo.ruscodc.utils.Pair;

import java.util.Set;

public class Chest extends PickableItem{
    public Chest(Set<Item> itemSet, Pair<Integer, Integer> pos) {
        super(itemSet, pos);
    }


    @Override
    public String getPath() {
        return null;
    }
}
