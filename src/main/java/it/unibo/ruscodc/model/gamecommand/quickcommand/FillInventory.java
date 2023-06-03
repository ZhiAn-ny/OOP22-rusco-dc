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
 * TODO documentazione!.
 */
public class FillInventory extends QuickActionAbs {

    private final Set<Item> items;

    /**
     * TODO documentazione!.
     * @param itemSet TODO documentazione!.
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
