package it.unibo.ruscodc.model.actors.monster.behaviour;

import java.util.List;
import java.util.Optional;

import it.unibo.ruscodc.model.actors.Actor;
import it.unibo.ruscodc.model.actors.monster.Monster;
import it.unibo.ruscodc.model.gamecommand.GameCommand;
import it.unibo.ruscodc.model.gamemap.Room;

/**
 * Combact Behaviour is a functional interface that rappresents the behaviour that the
 * Monster will use to make a Combact action.
 */
public interface CombactBehaviour {

    /**
     * @param monster the Monster who needs to attack
     * @param actors the list of Actors that can alterate the combact decision
     * @param room the room in which the Monster is
     * @return a Optional GameCommand, which is empty if no attack can be done
     * or the attack it will do
     */
    Optional<GameCommand> choseAttack(Monster monster, Room room, List<Actor> actors);
}
