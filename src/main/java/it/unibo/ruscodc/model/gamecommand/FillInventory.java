package it.unibo.ruscodc.model.gamecommand;

import it.unibo.ruscodc.model.gamecommand.quickcommand.QuickActionAbs;
import it.unibo.ruscodc.model.item.Item;
import it.unibo.ruscodc.utils.exception.ModelException;

import java.util.Set;

public abstract class FillInventory extends QuickActionAbs {

    private final Set<Item> items;
    public FillInventory(Set<Item> itemSet){
        this.items = itemSet;
    }

    @Override
    public void execute() throws ModelException {
        // this.getActor().getInventory();
        // todo
    }
}
