package it.unibo.ruscodc.model;

import it.unibo.ruscodc.model.actors.Actor;
import it.unibo.ruscodc.model.gamemap.Room;

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
     * change the actual room saved into floor
     */
    void changeRoom();

    /**
     * change the entire floor 
     */
    void changeFloor();

}
