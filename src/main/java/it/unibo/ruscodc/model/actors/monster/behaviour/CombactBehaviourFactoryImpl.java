package it.unibo.ruscodc.model.actors.monster.behaviour;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import it.unibo.ruscodc.model.actors.Actor;
import it.unibo.ruscodc.model.actors.monster.Monster;
import it.unibo.ruscodc.model.actors.skill.Skill;
import it.unibo.ruscodc.model.actors.stat.StatImpl.StatName;
import it.unibo.ruscodc.model.gamecommand.GameCommand;
import it.unibo.ruscodc.model.gamemap.Room;
import it.unibo.ruscodc.utils.GameControl;
import it.unibo.ruscodc.utils.Pairs;

/**
 * The implementation of the Comabact Behaviour Factory interface that for each method returns the specific Combact Behaviour.
 */
public class CombactBehaviourFactoryImpl implements CombactBehaviourFactory {

    private List<GameCommand> getPossibleAttacks(final Monster monster, final Room room, List<Actor> actors) {
        Skill skills = monster.getSkills();
        List<GameCommand> attacks = 
            GameControl.getAttackControls()
            .stream()
            .map(a -> skills.getAction(a))
            .filter(a -> a.isPresent())
            .map(a -> a.get())
            .peek(a -> a.setActor(monster))
            .peek(a -> a.setRoom(room))
            .collect(Collectors.toList());

        attacks.removeIf(gc -> !actors.stream().anyMatch(a -> gc.isTargetInRange(a)));

        return attacks;
    }

    /**
     * 
     */
    @Override
    public CombactBehaviour createMelee() {
        return new CombactBehaviour() {
            @Override
            public Optional<GameCommand> choseAttack(final Monster monster, final Room room, final List<Actor> actors) {

                Optional<GameCommand> action = Optional.empty();

                List<GameCommand> attacks = getPossibleAttacks(monster, room, actors);

                if (attacks.isEmpty()) {
                    return action;
                }

                action = Optional.of(
                    attacks.stream()
                    .sorted((a, b) -> b.getAPCost() - a.getAPCost())
                    .findFirst()
                    .get()
                );

                return action;
            }
        };
    }

    /**
     * 
     */
    @Override
    public CombactBehaviour createRanged() {
        return new CombactBehaviour() {
            @Override
            public Optional<GameCommand> choseAttack(final Monster monster, final Room room, final List<Actor> actors) {

                Optional<GameCommand> action = Optional.empty();

                List<GameCommand> attacks = getPossibleAttacks(monster, room, actors);

                int closestDistance =
                    actors.stream()
                    .map(a -> (int) (Pairs.computePPLine(monster.getPos(), a.getPos()).count()))
                    .sorted((a, b) -> a - b)
                    .findFirst()
                    .get();

                if (attacks.isEmpty() || closestDistance < monster.getStatActual(StatName.DEX)) {
                    return Optional.empty();
                }

                action = Optional.of(
                    attacks.stream()
                    .sorted((a, b) -> b.getAPCost() - a.getAPCost())
                    .findFirst()
                    .get()
                );
                

                return action;
            }
        };
    }
}
