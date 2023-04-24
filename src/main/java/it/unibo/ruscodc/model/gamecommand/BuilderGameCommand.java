package it.unibo.ruscodc.model.gamecommand;

import it.unibo.ruscodc.model.actors.Actor;
import it.unibo.ruscodc.model.gamemap.Room;

public interface BuilderGameCommand extends GameCommand {
    /**
     * 
     * @param from
     */
    void setActor(Actor from);
    /**
     * 
     * @param where
     */
    void setRoom(Room where);
}
