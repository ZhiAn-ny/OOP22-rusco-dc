package it.unibo.ruscodc.model.actors.monster;

import java.util.List;

import it.unibo.ruscodc.model.actors.Actor;
import it.unibo.ruscodc.model.gamecommand.GameCommand;
import it.unibo.ruscodc.model.gamemap.Room;

/**
 * The interface representing.
 */
public interface Monster extends Actor {

    /**
     * @param room the Room in which the Monster is
     * @param actors the list of Actors needed to make a decision
     * @return the GameCommand corrisponding to the action the Monster will do 
     */
    GameCommand behave(Room room, List<Actor> actors);
}
