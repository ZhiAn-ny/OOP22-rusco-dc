package it.unibo.ruscodc.model.actors;

import it.unibo.ruscodc.model.gamecommand.GameCommand;
import it.unibo.ruscodc.utils.GameControl;

/**
 * Interface of the Skill used by Actors.
 */
public interface Skill {

    /**
     * @param key
     * @param action
     */
    void setAction(GameControl key, GameCommand action);

    /**
     * @param key
     * @return the Builder from the correct input
     */
    GameCommand getAction(GameControl key);

}
