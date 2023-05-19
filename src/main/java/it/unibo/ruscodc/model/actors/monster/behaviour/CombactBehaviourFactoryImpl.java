package it.unibo.ruscodc.model.actors.monster.behaviour;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import it.unibo.ruscodc.model.actors.Actor;
import it.unibo.ruscodc.model.actors.monster.Monster;
import it.unibo.ruscodc.model.actors.skill.Skill;
import it.unibo.ruscodc.model.actors.stat.StatImpl.StatName;
import it.unibo.ruscodc.model.gamecommand.GameCommand;
import it.unibo.ruscodc.model.gamemap.Room;
import it.unibo.ruscodc.utils.GameControl;

public class CombactBehaviourFactoryImpl implements CombactBehaviourFactory {

    @Override
    public CombactBehaviour Melee() {
        return new CombactBehaviour() {

            @Override
            public Optional<GameCommand> choseAttack(Monster monster, Room room, List<Actor> actors) {
                List<GameCommand> possibleActions = new LinkedList<>();
                Skill skills = monster.getSkills();
                for ( GameControl gameControl : GameControl.values()) {
                    Optional<GameCommand> action = skills.getAction(gameControl);  
                    if (action.isPresent()) {
                        possibleActions.add(action.get());
                    }
                }

                possibleActions.removeIf(gc -> !actors.stream().anyMatch(a -> gc.isTargetInRange(a)));

                if (possibleActions.isEmpty()) {
                    return Optional.empty();
                }

                possibleActions.stream().sorted((a, b) -> a.getAPCost() - b.getAPCost()).toList();

                for ( GameCommand gameCommand : possibleActions) {
                    if (gameCommand.getAPCost() <= monster.getStatInfo(StatName.AP)) {
                        return Optional.of(gameCommand);
                    }
                }

                return Optional.empty();
            }

        };
    }
    
}
