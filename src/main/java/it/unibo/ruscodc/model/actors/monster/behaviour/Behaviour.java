package it.unibo.ruscodc.model.actors.monster.behaviour;

import java.util.List;

import it.unibo.ruscodc.model.actors.Actor;
import it.unibo.ruscodc.model.actors.monster.Monster;
import it.unibo.ruscodc.model.gamecommand.GameCommand;
import it.unibo.ruscodc.model.gamemap.Room;

public interface Behaviour {
    GameCommand makeDecision(Monster monster, Room room, List<Actor> actors);
}
