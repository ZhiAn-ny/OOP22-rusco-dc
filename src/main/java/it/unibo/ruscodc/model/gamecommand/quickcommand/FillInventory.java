package it.unibo.ruscodc.model.gamecommand.quickcommand;

import it.unibo.ruscodc.model.item.Item;
import it.unibo.ruscodc.model.outputinfo.InfoPayload;
import it.unibo.ruscodc.utils.exception.ModelException;

import java.util.Optional;
import java.util.Set;

public abstract class FillInventory extends QuickActionAbs {

    private final Set<Item> items;

    public FillInventory(Set<Item> itemSet){
        this.items = itemSet;
    }

    @Override
    public Optional<InfoPayload> execute() throws ModelException {
        // this.getActor().getInventory();
        // TODO - aspettare invertario eroe
        return null;
    }
}
