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

public class EquipementImpl implements Equipement {

    private final String name;
    private final String path;
    private final Slot slot;
    private final Map<StatName, Integer> stat;
    private final Optional<Pair<GameControl, GameCommand>> action;
    private final InfoPayload info;

    
    public EquipementImpl(
        String name,
        String description,
        String path,
        Slot slot,
        Map<StatName, Integer> stat,
        Pair<GameControl, GameCommand> action
        ) {
            this.name = name;
            this.path = path;
            this.slot = slot;
            this.stat = stat;
            this.action = Optional.ofNullable(action);
            this.info = new InfoPayloadImpl(name, description, path);
    }
    
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
     * 
     */
    public boolean isWearable() {
        return true;
    }

    /**
     * 
     */
    public String getName() {
        return this.name;
    }

    /**
     * 
     */
    public String getPath() {
        return this.path;
    }

    /**
     * 
     */
    public InfoPayload getInfo() {
        return this.info;
    }

    /**
     * @param hero the actor that needs to equip the item
     */
    public void equip(final Hero hero) {
        this.stat
            .entrySet()
            .stream()
            .forEach(
                a -> hero.modifyMaxStat(a.getKey(), a.getValue()));

        if (action.isPresent()) {
            hero.getSkills().setAction(action.get().getX(), action.get().getY());
        }

        Inventory inventory = hero.getInventory();
        inventory.removeItem(inventory.getAllItems().indexOf(this));
    }

    /**
     * @param actor the actor that needs to unequip the item
     */
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
    public Slot getSlot() {
        return this.slot;
    }
}
