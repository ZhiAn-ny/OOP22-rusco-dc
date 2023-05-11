package it.unibo.ruscodc.model.actors.hero;

import it.unibo.ruscodc.model.actors.Actor;
import it.unibo.ruscodc.model.gamecommand.BuilderGameCommand;
import it.unibo.ruscodc.utils.GameControl;

/**
 * Interface of the Hero controlle dby the player.
 */
public interface Hero extends Actor {

    /**
     * @param key the key pressed by the Player
     * @return Method used to make the player act with the heros
     */
    BuilderGameCommand act(GameControl key);
}