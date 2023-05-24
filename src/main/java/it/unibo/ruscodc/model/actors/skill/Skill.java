package it.unibo.ruscodc.model.actors.skill;

import java.util.Optional;

import it.unibo.ruscodc.model.gamecommand.GameCommand;
import it.unibo.ruscodc.utils.GameControl;

/**
 * Interface of the Skill used by Actors.
 */
public interface Skill {

    /**
     * @param key
     * @return the Builder from the correct input
     */
    Optional<GameCommand> getAction(GameControl key);

    void setAction(GameControl key, GameCommand action);

}
