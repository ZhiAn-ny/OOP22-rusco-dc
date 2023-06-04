package it.unibo.ruscodc.model.item.equipement;

import java.util.Map;
import java.util.Optional;

import it.unibo.ruscodc.model.actors.hero.Hero;
import it.unibo.ruscodc.model.actors.stat.StatImpl.StatName;
import it.unibo.ruscodc.model.gamecommand.GameCommand;
import it.unibo.ruscodc.model.item.Inventory;
import it.unibo.ruscodc.model.item.InventoryImpl.Slot;
import it.unibo.ruscodc.model.outputinfo.InfoPayload;
import it.unibo.ruscodc.model.outputinfo.InfoPayloadImpl;
import it.unibo.ruscodc.utils.GameControl;
import it.unibo.ruscodc.utils.Pair;

/**
 * Implementation of the interface Equipement.
 */
public class EquipementImpl implements Equipement {

    private final String name;
    private final String path;
    private final Slot slot;
    private final Map<StatName, Integer> stat;
    private final Optional<Pair<GameControl, GameCommand>> action;
    private final InfoPayload info;

    /**
     * Constructor for Equipement.
     * @param name of the Equipement
     * @param description of the Equipement
     * @param path to the Equipement related info
     * @param slot of the Equipement
     * @param stat modified by the Equipement
     * @param action changed by Equipement
     */
    public EquipementImpl(
        final String name,
        final String description,
        final String path,
        final Slot slot,
        final Map<StatName, Integer> stat,
        final Pair<GameControl, GameCommand> action
        ) {
            this.name = name;
            this.path = path;
            this.slot = slot;
            this.stat = stat;
            this.action = Optional.ofNullable(action);
            this.info = new InfoPayloadImpl(name, description, path);
    }

    /**
     * Alternative Constructor for Equipement.
     * @param name of the Equipement
     * @param description of the Equipementn
     * @param path to the Equipement related info
     * @param slot of the Equipement
     * @param stat modified by the Equipement
     */
    public EquipementImpl(
            final String name,
            final String description,
            final String path,
            final Slot slot,
            final Map<StatName, Integer> stat
    ) {
        this(
            name,
            description,
            path,
            slot,
            stat,
            null
        );
    }

    /**
     * @return if the item is Wearable
     */
    @Override
    public boolean isWearable() {
        return true;
    }

    /**
     * @return the Name of the Equipement
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * @return the Path related to the Equipement information
     */
    @Override
    public String getPath() {
        return this.path;
    }

    /**
     * @return return the InfoPayload of the Equipement
     */
    @Override
    public InfoPayload getInfo() {
        return this.info;
    }

    /**
     * @param hero the actor that needs to equip the item
     */
    @Override
    public void equip(final Hero hero) {
        this.stat
            .entrySet()
            .stream()
            .forEach(
                a -> hero.modifyMaxStat(a.getKey(), a.getValue()));

        if (action.isPresent()) {
            hero.getSkills().setAction(action.get().getX(), action.get().getY());
        }

        final Inventory inventory = hero.getInventory();
        inventory.removeItem(inventory.getAllItems().indexOf(this));
    }

    /**
     * @param hero the Hero that needs to unequip the item
     */
    @Override
    public void unequip(final Hero hero) {
        this.stat
            .entrySet()
            .stream()
            .forEach(
                a -> hero.modifyMaxStat(a.getKey(), -a.getValue())
            );
        hero.getInventory().addItem(this);
    }

    /**
     * @return the Slot occupied by the Equipement
     */
    @Override
    public Slot getSlot() {
        return this.slot;
    }
}
