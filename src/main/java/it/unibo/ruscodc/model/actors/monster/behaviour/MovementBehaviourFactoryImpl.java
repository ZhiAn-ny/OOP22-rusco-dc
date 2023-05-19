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

public class MovementBehaviourFactoryImpl implements MovementBehaviourFactory{

    @Override
    public MovementBehaviour createAggressive() {
        return new MovementBehaviour() {

            @Override
            public Optional<GameCommand> chooseMove(Monster monster, List<Actor> actors, Room room) {
                
                Pair<Integer, Integer> monsterPos = monster.getPos();
                Pair<Integer, Integer> actorPos = 
                    actors
                        .stream()
                        .sorted((a, b) -> (int)(Pairs.computePPLine(monsterPos, a.getPos()).count() - Pairs.computePPLine(monsterPos, b.getPos()).count()))
                        .findFirst()
                        .get()
                        .getPos();
                
                if (monsterPos.getY() < actorPos.getY()) {
                    if (canMove(new Pair<>(monsterPos.getX(), monsterPos.getY() + 1), actors, room)) {
                        return monster.getSkills().getAction(GameControl.MOVERIGHT);
                    }
                }

                if (monsterPos.getY() > actorPos.getY()) {
                    return monster.getSkills().getAction(GameControl.MOVELEFT);
                }

                if (monsterPos.getX() < actorPos.getX()) {
                    return monster.getSkills().getAction(GameControl.MOVEUP);
                }

                if (monsterPos.getX() > actorPos.getX()) {
                    return monster.getSkills().getAction(GameControl.MOVEDOWN);
                }

                return Optional.empty();
            }

            private boolean canMove(Pair<Integer, Integer> pos, List<Actor> actors, Room room) {
                return room.isAccessible(pos)
                    && actors.stream().anyMatch(a -> a.getPos() == pos)
                    && room.getMonsters().stream().anyMatch(a -> a.getPos() == pos);
            }
            
        }; 
    }
    
}
