package it.unibo.ruscodc.model.actors.monster;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import it.unibo.ruscodc.model.actors.Actor;
import it.unibo.ruscodc.model.gamecommand.BuilderGameCommand;
import it.unibo.ruscodc.model.gamecommand.GameCommand;
import it.unibo.ruscodc.model.gamemap.Room;
import it.unibo.ruscodc.utils.GameControl;

public interface CombactBehave {
    Optional<GameControl> choseAttack(Room room, List<Actor> actors, Map<GameCommand, BuilderGameCommand> skills);
}
