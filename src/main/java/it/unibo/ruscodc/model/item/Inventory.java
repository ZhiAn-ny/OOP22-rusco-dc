package it.unibo.ruscodc.model.item;

import java.util.List;

import it.unibo.ruscodc.model.item.InventoryImpl.Slot;

public interface Inventory {
    void openBag();
	void openEquipement();
	Item getItem(int index);
	Equipement getEquipment(Slot slot);
	List<Item> getAllItems();
    List<Consumable> getAllConsumable();
	List<Equipement> getAllEquipement();
	void swapEquipement(int index);
	void useItem(int index);
}
