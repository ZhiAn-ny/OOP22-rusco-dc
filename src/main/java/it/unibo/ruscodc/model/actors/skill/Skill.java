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

    /**
     * @param key the keybind of the Gamecommand you want to set 
     * @param action the new GameCommand you want to put
     */
    void setAction(GameControl key, GameCommand action);
}
