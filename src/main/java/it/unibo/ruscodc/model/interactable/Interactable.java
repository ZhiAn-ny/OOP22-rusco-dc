package it.unibo.ruscodc.model.interactable;

import it.unibo.ruscodc.model.Entity;
import it.unibo.ruscodc.model.gamecommand.GameCommand;

/**
 * The Intractable represent the Intractable object.
 *
 */
public interface Interactable extends Entity {

    /**
     * This method return the name of the Intractable entity.
     * @return
     */
    String getName();

    /**
     * This method is used to interact with Intractable entity.
     * @return the command wrapped into Intractable entity.
     */
    GameCommand interact();

}
