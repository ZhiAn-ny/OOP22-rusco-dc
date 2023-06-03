package it.unibo.ruscodc.model.item;

import java.util.List;

import it.unibo.ruscodc.model.actors.hero.Hero;
import it.unibo.ruscodc.model.item.equipement.Equipement;

public interface Inventory {
	Item getItem(int index);
	void addItem(Item item);
	void removeItem(int index);
	List<Equipement> getEquipedItems();
	void equip(Equipement equip, Hero hero);
	List<Item> getAllItems();
	boolean isEmpty();
	int slotOccupied();
}
