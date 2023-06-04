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
     * @return the name of interactable thing.
     */
    String getName();

    /**
     * This method is used to interact with Intractable entity.
     * @return the command wrapped into Intractable entity.
     */
    GameCommand interact();

    /**
     * Specific if this interactable could be transitable.
     * @return true if actors can pass over, false, otherwise.
     */
    boolean isTransitable();

}
