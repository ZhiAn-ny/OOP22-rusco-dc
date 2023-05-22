package it.unibo.ruscodc.model.actors.monster.behaviour;

import java.util.List;
import java.util.Optional;

import it.unibo.ruscodc.model.actors.Actor;
import it.unibo.ruscodc.model.actors.monster.Monster;
import it.unibo.ruscodc.model.gamecommand.GameCommand;
import it.unibo.ruscodc.model.gamemap.Room;
import it.unibo.ruscodc.utils.GameControl;
import it.unibo.ruscodc.utils.Pair;
import it.unibo.ruscodc.utils.Pairs;

public class BehaviourImpl implements Behaviour{

    private final MovementBehaviour movementBehaviour;
    private final CombactBehaviour combactBehaviour;


    public BehaviourImpl(final MovementBehaviour movementBehaviour, final CombactBehaviour combactBehaviour) {
        this.movementBehaviour = movementBehaviour;
        this.combactBehaviour = combactBehaviour;
    }


    @Override
    public GameCommand makeDecision(Monster monster, Room room, List<Actor> actors) {

        Optional<GameCommand> action = this.combactBehaviour.choseAttack(monster, room, actors);
        if (action.isPresent()) {
            return this.optimizeComplexAction(action.get(), monster, room, actors);
        }

        action = this.movementBehaviour.chooseMove(monster, actors, room);
        
        if (action.isPresent()) {
            return action.get();
        }

        return monster.getSkills().getAction(GameControl.DONOTHING).get();
    }

    //TODO: Modifica
    private GameCommand optimizeComplexAction(GameCommand toOptimize, Monster monster, Room room, List<Actor> actors) {
        
        toOptimize.setActor(monster);
        toOptimize.setRoom(room);
        
        Pair<Integer, Integer> target = actors
            .stream()
            .sorted((a, b) -> (int)(Pairs.computePPLine(monster.getPos(), a.getPos()).count() - Pairs.computePPLine(monster.getPos(), b.getPos()).count()))
            .findFirst()
            .get()
            .getPos();
        
        toOptimize.setCursorPos(target);;

        return toOptimize;
    }
}
