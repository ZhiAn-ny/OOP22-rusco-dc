package it.unibo.ruscodc.model.gamecommand.quickcommand;

import it.unibo.ruscodc.model.actors.hero.Hero;
import it.unibo.ruscodc.model.item.Inventory;
import it.unibo.ruscodc.model.item.InventoryImpl;
import it.unibo.ruscodc.model.item.Item;
import it.unibo.ruscodc.model.outputinfo.InfoPayload;
import it.unibo.ruscodc.utils.exception.ModelException;

import java.util.Optional;
import java.util.Set;

public class FillInventory extends QuickActionAbs {

    private final Set<Item> items;
    private Inventory inventory;

    public FillInventory(Set<Item> itemSet){
        this.items = itemSet;
        final Hero hero = (Hero) this.getActor();
        this.inventory = hero.getInventory();
    }

    @Override
    public Optional<InfoPayload> execute() throws ModelException {
        for (Item item : items) {
            inventory.addItem(item);
        }
        return Optional.empty();
    }


}
