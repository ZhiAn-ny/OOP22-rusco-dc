package it.unibo.ruscodc.model.item;

import it.unibo.ruscodc.model.actors.Actor;
import it.unibo.ruscodc.model.gamecommand.QuickActionBuilder;

public interface Item {
    String getName();
    String getPath();
    boolean isWearable();
 //   DropObject drop(Actor actor);
}
