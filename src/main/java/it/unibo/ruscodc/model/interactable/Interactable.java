package it.unibo.ruscodc.model.interactable;

import it.unibo.ruscodc.model.Entity;
import it.unibo.ruscodc.model.gamecommand.GameCommand;

public interface Interactable extends Entity {

    String getName();

    GameCommand interact();

}
