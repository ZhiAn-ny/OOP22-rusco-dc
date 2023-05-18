package it.unibo.ruscodc.model.item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class InventoryImpl implements Inventory {

    public enum Slot {
        HEAD,
        TORSO,
        LEGS,
        R_ARM,
        L_ARM;
    }

    private final List<Item> bag;
    private final Map<Slot, Equipement> equipement;

    public InventoryImpl() {
        this.bag = new ArrayList<>();
        this.equipement = new HashMap<>();
        for (Slot slot : Slot.values()) {
            //this.equipement.put(slot, new DefaultEquipement());
        }
    }

    @Override
    public void openBag() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'openBag'");
    }

    @Override
    public void openEquipement() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'openEquipement'");
    }

    @Override
    public Item getItem(int index) {
        return this.bag.get(index);
    }

    @Override
    public Equipement getEquipment(Slot slot) {
        return this.equipement.get(slot);
    }

    @Override
    public List<Item> getAllItems() {
        return List.copyOf(this.bag);
    }

    @Override
    public List<Consumable> getAllConsumable() {
        return List.copyOf(
            this.bag
            .stream()
            .filter(a -> !a.isWearable())
            .map(a -> (Consumable) a)
            .collect(Collectors.toList())
        );
    }

    @Override
    public List<Equipement> getAllEquipement() {
        return List.copyOf(
            this.bag
            .stream()
            .filter(a -> a.isWearable())
            .map(a -> (Equipement) a)
            .collect(Collectors.toList())
        );
    }

    @Override
    public void swapEquipement(int index) {
        Equipement toEquip = this.getAllEquipement().get(index);
        for (Slot slot : toEquip.getSlots()) {
            var tmp = this.equipement.get(slot).getSlots();
            if (tmp.size() > 1) {
                for (Slot slotTmp : tmp) {
                    
                }
            };

        }  
    }

    @Override
    public void useItem(int index) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'useItem'");
    }
    
}
