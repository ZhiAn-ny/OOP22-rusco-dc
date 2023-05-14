package it.unibo.ruscodc.model.actors.monster;

import java.util.List;
import java.util.Optional;

import it.unibo.ruscodc.model.actors.Actor;
import it.unibo.ruscodc.model.gamecommand.GameCommand;
import it.unibo.ruscodc.model.gamemap.Room;

public interface MovementBehave {
    Optional<GameCommand> choseMove(Monster monster, List<Actor> actors, Room room);
}
