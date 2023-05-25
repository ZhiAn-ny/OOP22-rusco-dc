package it.unibo.ruscodc.model.item;

import java.util.List;

import it.unibo.ruscodc.model.actors.Actor;
import it.unibo.ruscodc.model.item.equipement.Equipement;

public interface Inventory {
	Item getItem(int index);
	void addItem(Item item);
	void removeItem(int index);
	List<Equipement> getEquipedItems();
	void equip(Equipement equip, Actor actor);
	List<Item> getAllItems();
}
