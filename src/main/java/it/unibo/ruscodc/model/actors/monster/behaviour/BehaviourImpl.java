package it.unibo.ruscodc.model.actors.monster.behaviour;

import java.util.List;
import java.util.Optional;

import it.unibo.ruscodc.model.actors.Actor;
import it.unibo.ruscodc.model.actors.monster.Monster;
import it.unibo.ruscodc.model.gamecommand.GameCommand;
import it.unibo.ruscodc.model.gamemap.Room;
import it.unibo.ruscodc.utils.GameControl;

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
