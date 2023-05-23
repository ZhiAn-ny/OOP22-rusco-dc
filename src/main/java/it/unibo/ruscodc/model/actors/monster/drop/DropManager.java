package it.unibo.ruscodc.model.actors.monster.drop;

import java.util.Set;

import it.unibo.ruscodc.model.item.Item;

public interface DropManager {
    
    public Set<Item> generateDrop();
    
}
