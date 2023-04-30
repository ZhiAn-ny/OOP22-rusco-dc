package it.unibo.ruscodc.model.gamecommand;

import it.unibo.ruscodc.model.actors.Actor;
import it.unibo.ruscodc.model.gamemap.Room;

/**
 * An interface usefull to take information by ComplexBuilderCommand.
 */
public interface ComplexObserver {
    /**
     * Take the Room informations.
     * @return Room informations
     */
    Room getOriginalRoom();
    /**
     * Take the Actor informations.
     * @return Actor informations
     */
    Actor getOriginalActor();
}
