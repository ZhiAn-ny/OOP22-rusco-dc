package it.unibo.ruscodc.model.actors.monster;

import java.util.Optional;

import it.unibo.ruscodc.model.actors.Skill;
import it.unibo.ruscodc.model.gamecommand.GameCommand;
import it.unibo.ruscodc.model.gamemap.Room;

public interface CombactBehave {
    Optional<GameCommand> choseAttack(Room room, Skill skills);
}
