package it.unibo.ruscodc.model.actors.monster.behaviour;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import it.unibo.ruscodc.model.actors.monster.Monster;
import it.unibo.ruscodc.model.actors.skill.Skill;
import it.unibo.ruscodc.model.gamecommand.GameCommand;
import it.unibo.ruscodc.model.gamemap.Room;
import it.unibo.ruscodc.utils.GameControl;

public class CombactBehaveFactoryImpl implements CombactBehaveFactory {

    @Override
    public CombactBehave Melee() {
        return null;
        // new CombactBehave() {

        //     @Override
        //     public Optional<GameCommand> choseAttack(Monster monster, Room room, List<Actor> actors) {
        //         Set<GameCommand> possibleActions = new HashSet<>();
        //         Skill skills = monster.getSkills();
        //         for ( GameControl gameControl : GameControl.values()) {
        //             possibleActions.add(skills.getAction(gameControl));
        //         }
                
        //         Optional<GameCommand> action =
        //             possibleActions.stream()
        //                 .filter(a -> a.)
        //     }
            
        // };
    }
    
}
