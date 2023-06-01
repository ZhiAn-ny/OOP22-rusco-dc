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
 * The implementation of the Comabact Behaviour Factory interface that for ech method returns the specific Combact Behaviour.
 */
public class CombactBehaviourFactoryImpl implements CombactBehaviourFactory {

    private Optional<List<GameCommand>> getPossibleAttacks(final Skill skills, List<Actor> actors) {
        List<GameCommand> attacks = 
            GameControl.getAttackControls()
            .stream()
            .map(a -> skills.getAction(a))
            .filter(a -> a.isPresent())
            .map(a -> a.get())
            .collect(Collectors.toList());

        attacks.removeIf(gc -> !actors.stream().anyMatch(a -> gc.isTargetInRange(a)));

        return Optional.of(attacks);
    }

    /**
     * 
     */
    @Override
    public CombactBehaviour createMelee() {
        return new CombactBehaviour() {
            @Override
            public Optional<GameCommand> choseAttack(final Monster monster, final Room room, final List<Actor> actors) {

                Skill skills = monster.getSkills();

                List<GameCommand> attacks = getPossibleAttacks(skills, actors).get();

                if (attacks.isEmpty()) {
                    return Optional.empty();
                }

                return Optional.of(
                    attacks.stream()
                    .sorted((a, b) -> b.getAPCost() - a.getAPCost())
                    .findFirst()
                    .get()
                );
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

                Skill skills = monster.getSkills();

                List<GameCommand> attacks = getPossibleAttacks(skills, actors).get();

                int closestDistance =
                    actors.stream()
                    .map(a -> (int) (Pairs.computePPLine(monster.getPos(), a.getPos()).count()))
                    .sorted((a, b) -> a - b)
                    .findFirst()
                    .get();

                if (attacks.isEmpty() || closestDistance < monster.getStatActual(StatName.DEX)) {
                    return Optional.empty();
                }

                return Optional.of(
                    attacks.stream()
                    .sorted((a, b) -> b.getAPCost() - a.getAPCost())
                    .findFirst()
                    .get()
                );
            }
        };
    }
}
