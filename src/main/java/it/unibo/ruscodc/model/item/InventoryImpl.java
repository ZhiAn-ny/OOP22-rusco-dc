package it.unibo.ruscodc.model.item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.unibo.ruscodc.model.actors.Actor;
import it.unibo.ruscodc.model.item.equipement.Equipement;

public class InventoryImpl implements Inventory {

    public enum Slot {
        HEAD,
        ARMOR,
        WEAPON,
        SPECIAL;
    }

    private final List<Item> bag;
    private final Map<Slot, Equipement> equipement;

    public InventoryImpl() {
        this.bag = new ArrayList<>();
        this.equipement = new HashMap<>();
    }

    @Override
    public Item getItem(int index) {
        return this.bag.get(index);
    }

    @Override
    public List<Equipement> getEquipedItems() {
        return equipement
            .entrySet()
            .stream()
            .map(a -> a.getValue())
            .toList();
    }

    @Override
    public void equip(Equipement equip, Actor actor) {
        this.equipement.get(equip.getSlot()).unequip(actor);
        this.equipement.put(equip.getSlot(), equip);
        equip.equip(actor);
    }

    @Override
    public List<Item> getAllItems() {
        return List.copyOf(this.bag);
    }

}
