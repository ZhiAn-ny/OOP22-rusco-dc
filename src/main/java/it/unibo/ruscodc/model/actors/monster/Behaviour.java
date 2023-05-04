package it.unibo.ruscodc.model.actors.monster;

import java.util.List;

import it.unibo.ruscodc.model.actors.Actor;
import it.unibo.ruscodc.model.gamecommand.IAGameCommand;
import it.unibo.ruscodc.model.gamemap.Room;
import it.unibo.ruscodc.utils.GameControl;

public interface Behaviour {
    GameControl makeDecision(Room room, List<Actor> actors, Monster monster);
    void optimizeComplexAction(IAGameCommand command);
}
