package it.unibo.ruscodc.model.actors.monster.drop;

import it.unibo.ruscodc.model.actors.Actor;

public interface DropFactory {
    
    DropManager createGenericBasicDrop(Actor by);

    DropManager createGenericRichDrop(Actor by);
    
    DropManager createGenericPoorDrop(Actor by);

}
