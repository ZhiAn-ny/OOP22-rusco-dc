package it.unibo.ruscodc.model.gamecommand.quickcommand;

import it.unibo.ruscodc.model.actors.hero.Hero;
import it.unibo.ruscodc.model.item.Inventory;
import it.unibo.ruscodc.model.item.Item;
import it.unibo.ruscodc.utils.exception.ModelException;
import it.unibo.ruscodc.utils.outputinfo.InfoPayload;
import it.unibo.ruscodc.utils.outputinfo.InfoPayloadImpl;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * A QuickAction, finalizate to fill the inventory of the actor that invoke this command with some objects.
 */
public class FillInventory extends QuickActionAbs {

    private static final String ERR_MESS = "Your inventory is full!" 
     + "Take even one more item will cause you a sever back pain. Try delete something from your inventory!";

    private final Set<Item> items;

    /**
     * Create this type of command.
     * @param itemSet the set of objects that have to add to the Inventory of the actor
     */
    public FillInventory(final Set<Item> itemSet) {
        this.items = new HashSet<>(itemSet);
    }

    private Optional<InfoPayload> createMessage() {
        return Optional.of(new InfoPayloadImpl(
            this.getErrTitle(),
            ERR_MESS));
    }

    /**
     * 
     */
    @Override
    public Optional<InfoPayload> execute() throws ModelException {
        final Set<Item> fillable = new HashSet<>(items);
        final Inventory toFill = ((Hero) this.getActor()).getInventory();
        for (final Item item : fillable) {
            if (toFill.isFull()) {
                return createMessage();
            } else {
                toFill.addItem(item);
                items.remove(item);
            }
        }
        return Optional.empty();
    }
}
