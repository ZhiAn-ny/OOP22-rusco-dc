package it.unibo.ruscodc.model.actors.monster;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import it.unibo.ruscodc.model.actors.Actor;
import it.unibo.ruscodc.model.gamecommand.BuilderGameCommand;
import it.unibo.ruscodc.model.gamecommand.GameCommand;
import it.unibo.ruscodc.model.gamecommand.IAGameCommand;
import it.unibo.ruscodc.model.gamemap.Room;
import it.unibo.ruscodc.utils.GameControl;
import it.unibo.ruscodc.utils.Pair;

public class BehaviourImpl implements Behaviour{

    private final MovementBehave movementBehave;
    private final CombactBehave combactBehave;


    public BehaviourImpl(final MovementBehave movementBehave, final CombactBehave combatBehave) {
        this.movementBehave = movementBehave;
        this.combactBehave = combatBehave;
    }


    @Override
    public GameCommand makeDecision(Monster monster, Room room, List<Actor> actors) {

        Optional<GameCommand> action = this.combactBehave.choseAttack(room, monster.getSkills());
        if (action.isPresent()) {
            return this.optimizeComplexAction(action.get(), monster, room, actors);
        }

        action = this.movementBehave.choseMove(monster, actors, room);
        
        if (action.isPresent()) {
            return action.get();
        }

        return monster.getSkills().getAction(GameControl.DONOTHING);
    }

    //TODO: Modifica
    private GameCommand optimizeComplexAction(GameCommand toOptimize, Monster monster, Room room, List<Actor> actors) {
        return toOptimize;
    }
}
