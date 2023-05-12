package it.unibo.ruscodc.model.interagible;

import it.unibo.ruscodc.model.Entity;
import it.unibo.ruscodc.model.gamecommand.GameCommand;

public interface Interagible extends Entity {

    String getName();

    GameCommand interact();

}
