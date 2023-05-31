package it.unibo.ruscodc.model.actors.monster.behaviour;

import java.util.List;

import it.unibo.ruscodc.model.actors.Actor;
import it.unibo.ruscodc.model.actors.monster.Monster;
import it.unibo.ruscodc.model.gamecommand.GameCommand;
import it.unibo.ruscodc.model.gamemap.Room;

/**
 * Behaviour is a functional interface that rappresents the behaviour used by
 * the Monster to make a choice.
 */
public interface Behaviour {

    /**
     * @param monster the Monster who needs to make the decision
     * @param room the Room in which the Monster is
     * @param actors the list of Actors needed to make the decision 
     * @return a GameCommand that is the action the monster will do 
     */
    GameCommand makeDecision(Monster monster, Room room, List<Actor> actors);
}
