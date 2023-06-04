package it.unibo.ruscodc.model.gamecommand.quickcommand;

import it.unibo.ruscodc.model.actors.hero.Hero;
import it.unibo.ruscodc.model.item.Inventory;
import it.unibo.ruscodc.model.item.Item;
import it.unibo.ruscodc.model.outputinfo.InfoPayload;
import it.unibo.ruscodc.utils.exception.ModelException;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * A QuickAction, finalizate to fill the inventory of the actor that invoke this command with some objects
 */
public class FillInventory extends QuickActionAbs {

    private final Set<Item> items;

    /**
     * Create this type of command
     * @param itemSet the set of objects that have to add to the Inventory of the actor
     */
    public FillInventory(final Set<Item> itemSet) {
        this.items = new HashSet<>(itemSet);
    }

    /**
     * 
     */
    @Override
    public Optional<InfoPayload> execute() throws ModelException {
        final Inventory toFill = ((Hero) this.getActor()).getInventory();
        for (Item item : items) {
            toFill.addItem(item);
        }
        return Optional.empty();
    }
}
