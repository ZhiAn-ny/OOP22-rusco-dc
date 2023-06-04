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

/**
 * Implementation of the interface Behaviour which make the monster choose which action it will do.
 */
public class BehaviourImpl implements Behaviour {

    private final MovementBehaviour movementBehaviour;
    private final CombactBehaviour combactBehaviour;


    /**
     * @param movementBehaviour the Behavior dedicated to the Movement
     * @param combactBehaviour the Behavior dedicated to the Combact
     */
    public BehaviourImpl(final MovementBehaviour movementBehaviour, final CombactBehaviour combactBehaviour) {
        this.movementBehaviour = movementBehaviour;
        this.combactBehaviour = combactBehaviour;
    }

    /**
     * 
     */
    @Override
    public GameCommand makeDecision(final Monster monster, final Room room, final List<Actor> actors) {

        Optional<GameCommand> action = this.combactBehaviour.choseAttack(monster, room, actors);
        if (action.isPresent()) {
            return this.optimizeComplexAction(action.get(), monster, actors);
        }

        action = this.movementBehaviour.chooseMove(monster, actors, room);

        if (action.isPresent()) {
            return action.get();
        }

        return monster.getSkills().getAction(GameControl.DONOTHING).get();
    }

    /**
     * @param toOptimize the GameCommand that needs to be optimized
     * @param monster the Monster that made the attack
     * @param actors the possible targets for this action
     * @return a GameCommand optimized with all 
     */
    private GameCommand optimizeComplexAction(
        final GameCommand toOptimize,
        final Monster monster,
        final List<Actor> actors
    ) {
        final Pair<Integer, Integer> target = actors
            .stream()
            .sorted((a, b) ->
                (int) (
                    Pairs.computePPLine(monster.getPos(), a.getPos()).count()
                    - Pairs.computePPLine(monster.getPos(), b.getPos()).count()
                )
            )
            .findFirst()
            .get()
            .getPos();

        toOptimize.setCursorPos(target);
        toOptimize.setTarget(actors);
        return toOptimize;
    }
}
