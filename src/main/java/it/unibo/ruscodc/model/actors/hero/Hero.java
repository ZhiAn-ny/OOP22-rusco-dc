package it.unibo.ruscodc.model.actors.hero;

import it.unibo.ruscodc.model.actors.Actor;
<<<<<<< HEAD
import it.unibo.ruscodc.model.gamecommand.GameCommand;
=======
import it.unibo.ruscodc.model.gamecommand.BasicGameCommand;
>>>>>>> feature/interactable
import it.unibo.ruscodc.utils.GameControl;

/**
 * Interface of the Hero controlle dby the player.
 */
public interface Hero extends Actor {

    /**
     * @param key the key pressed by the Player
     * @return Method used to make the player act with the heros
     */
<<<<<<< HEAD
    GameCommand act(GameControl key);
=======
    BasicGameCommand act(GameControl key);
>>>>>>> feature/interactable
}
