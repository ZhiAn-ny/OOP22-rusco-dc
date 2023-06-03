package it.unibo.ruscodc.model;

import it.unibo.ruscodc.model.actors.Actor;
import it.unibo.ruscodc.model.gamemap.Floor;
import it.unibo.ruscodc.model.gamemap.Room;
import it.unibo.ruscodc.model.outputinfo.Portrait;
import it.unibo.ruscodc.utils.Pair;

import java.util.List;

/**
 * It is a interface for the model. This process the input received.
 */
public interface GameModel {

    /**
     * order the actor with initiative.
     * @return the list of actor
     */
    List<Actor> getActorByInitative();

    /**
     *
     * @return the current tile.
     */
    List<Entity> getTile();

    /**
     *
     * @param toGet for kown his info.
     * @return the info of specific entity.
     */
    List<Entity> getInfo(Entity toGet);

    /**
     *
     * @return a current room.
     */
    Room getCurrentRoom();

    /**
     * Change the current room.
     * @param pos the coordinates of the passage
     */
    void changeRoom(Pair<Integer, Integer> pos);

    /**
     * Change the entire floor allowing the player into the next level.
     */
    void changeFloor();

    /**
     * Returns the current <code>Floor</code>.
     * @return the current floor.
     */
    Floor getCurrentFloor();


    Portrait getRuscoInfo();

    /**
     * Check if the game is over.
     * @return <code>True</code> if the hero has died, <code>False</code> otherwise.
     */
    boolean isGameOver();
}
