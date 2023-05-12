package it.unibo.ruscodc.model.actors.monster;

import java.util.List;
import java.util.Optional;

import it.unibo.ruscodc.model.actors.Actor;
import it.unibo.ruscodc.model.gamemap.Room;
import it.unibo.ruscodc.utils.GameControl;
import it.unibo.ruscodc.utils.Pair;

public interface MovementBehave {
    Optional<GameControl> choseMove(Room room, List<Actor> actors, Pair<Integer, Integer> currentPos);
}
