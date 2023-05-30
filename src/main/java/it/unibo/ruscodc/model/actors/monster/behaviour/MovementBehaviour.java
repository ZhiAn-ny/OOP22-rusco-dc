package it.unibo.ruscodc.model.actors.monster.behaviour;

import java.util.List;
import java.util.Optional;

import it.unibo.ruscodc.model.actors.Actor;
import it.unibo.ruscodc.model.actors.monster.Monster;
import it.unibo.ruscodc.model.gamecommand.GameCommand;
import it.unibo.ruscodc.model.gamemap.Room;

/**
 * Movement Behaviour is a functional interface that rappresents the behaviour that the
 * Monster will use to make a Movement action.
 */
public interface MovementBehaviour {

    /**
     * @param monster the Monster who needs to move
     * @param actors the list of Actors that can alterate the move decision
     * @param room the room in which the Monster is
     * @return a Optional GameCommand, which is empty if no move can be done
     * or the move it will do
     */
    Optional<GameCommand> chooseMove(Monster monster, List<Actor> actors, Room room);
}
