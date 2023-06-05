package it.unibo.ruscodc.model.item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import it.unibo.ruscodc.model.actors.hero.Hero;
import it.unibo.ruscodc.model.item.consumable.ConsumableFactory;
import it.unibo.ruscodc.model.item.consumable.ConsumableFactoryImpl;
import it.unibo.ruscodc.model.item.equipement.Equipement;
import it.unibo.ruscodc.model.item.equipement.EquipementFactory;
import it.unibo.ruscodc.model.item.equipement.EquipementFactoryImpl;

/**
 * Implementation of Inventory that is used by the 
 * Hero to store Items.
 */
public class InventoryImpl implements Inventory {

    /**
     * Slot used in the Equipement.
     */
    public enum Slot {
        /**
         * Head slot for Equipement.
         */
        HEAD,
        /**
         * Armor slot for Equipement.
         */
        ARMOR,
        /**
         * Weapon slot for Equipement.
         */
        WEAPON,
        /**
         * Special slot for Equipement.
         */
        SPECIAL;
    }

    private static final int INV_SPACE = 30;

    private final List<Item> bag;
    private final Map<Slot, Equipement> equipement;

    /**
     * Basic Constructor for the Inventory.
     */
    public InventoryImpl() {
        this.bag = new ArrayList<>();
        this.equipement = new HashMap<>();
        for (final Slot slot : Slot.values()) {
            equipement.put(slot, null);
        }

        //TODO: leva
        final EquipementFactory equipementFactory = new EquipementFactoryImpl();
        final ConsumableFactory consumableFactory = new ConsumableFactoryImpl();
        this.bag.add(equipementFactory.createActionRing());
        this.bag.add(equipementFactory.createChainmail());
        this.bag.add(equipementFactory.createCrusaderHelmet());
        this.bag.add(equipementFactory.createDexterityRing());
        this.bag.add(consumableFactory.createDEXPotion());
        this.bag.add(consumableFactory.createDEXPotion());
        this.bag.add(consumableFactory.createDEXPotion());
    }

    /**
     * 
     */
    @Override
    public Item getItem(final int index) {
        return this.bag.get(index);
    }

    /**
     * 
     */
    @Override
    public List<Equipement> getEquipedItems() {
        return equipement
            .entrySet()
            .stream()
            .map(a -> a.getValue())
            .collect(Collectors.toList());
    }

    /**
     * 
     */
    @Override
    public void equip(final Equipement equip, final Hero hero) {
        final Equipement toUnequip = this.equipement.get(equip.getSlot());
        if (toUnequip != null) {
            toUnequip.unequip(hero);
        }
        this.equipement.put(equip.getSlot(), equip);
        equip.equip(hero);
    }

    /**
     * 
     */
    @Override
    public List<Item> getAllItems() {
        return List.copyOf(this.bag);
    }

    /**
     * 
     */
    @Override
    public void addItem(final Item item) {
        this.bag.add(item);
    }

    /**
     * 
     */
    @Override
    public void removeItem(final int index) {
        this.bag.remove(index);
    }

    /**
     * 
     */
    @Override
    public boolean isEmpty() {
        return this.bag.isEmpty();
    }

    /**
     * 
     */
    @Override
    public int slotOccupied() {
        return this.bag.size();
    }

    /**
     * 
     */
    @Override
    public boolean isFull() {
        return this.bag.size() < INV_SPACE;
    }
}
