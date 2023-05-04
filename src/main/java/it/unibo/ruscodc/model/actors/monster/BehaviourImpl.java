package it.unibo.ruscodc.model.actors.monster;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import it.unibo.ruscodc.model.actors.Actor;
import it.unibo.ruscodc.model.gamecommand.BuilderGameCommand;
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
    public GameControl makeDecision(final Room room, final List<Actor> actors, final Monster monster) {
        Optional<GameControl> control = Optional.empty();
        control = combactBehave.choseAttack(
                    room,
                    actors,
                    monster
                        .getFieldSkill()
                        .getSkills()
                        .entrySet()
                        .stream()
                        .peek(a -> a.getValue().setActor(monster))
                        .peek(a -> a.getValue().setRoom(room))
                        .filter(a -> a.getValue().isBuildable())
                        .map(a -> new Pair<>(a.getKey(), a.getValue().buildForIA()))
                        .filter(a -> actors
                            .stream()
                            .filter(b -> a.getY().getRange().isInRange(monster.getPos(), b.getPos()))
                            .findFirst().isPresent()
                        )
                        .collect(Collectors.toMap(a -> a.getX(), a -> a.getY(), (a,b) -> a, () -> new HashMap<>()))
                );
            
        if (control.isPresent()) {
            return control.get();
        } else if ((control = movementBehave.choseMove(room, actors, monster.getPos())).isPresent()) {
            return control.get();
        }
        return GameControl.DONOTHING;
    }

    @Override
    public void optimizeComplexAction(final IAGameCommand command) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'optimizeComplexAction'");
    }
    
}
