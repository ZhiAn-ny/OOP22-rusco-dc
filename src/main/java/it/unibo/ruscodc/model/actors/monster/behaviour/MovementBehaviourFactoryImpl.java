package it.unibo.ruscodc.model.actors.monster.behaviour;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import it.unibo.ruscodc.model.actors.Actor;
import it.unibo.ruscodc.model.actors.monster.Monster;
import it.unibo.ruscodc.model.actors.stat.StatImpl.StatName;
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
                Optional<Pair<Integer, Integer>> actorPos = Optional.of( 
                    actors
                        .stream()
                        .sorted((a, b) -> (int)(Pairs.computePPLine(monsterPos, a.getPos()).count() - Pairs.computePPLine(monsterPos, b.getPos()).count()))
                        .findFirst()
                        .get()
                        .getPos()
                );
                
                if (actorPos.isPresent()) {
                    if (monsterPos.getY() < actorPos.get().getY()) {
                        if (canMove(new Pair<>(monsterPos.getX(), monsterPos.getY() + 1), actors, room)) {
                            return monster.getSkills().getAction(GameControl.MOVERIGHT);
                        }
                    }
    
                    if (monsterPos.getY() > actorPos.get().getY()) {
                        if (canMove(new Pair<>(monsterPos.getX(), monsterPos.getY() - 1), actors, room)) {
                            return monster.getSkills().getAction(GameControl.MOVELEFT);
                        }
                    }
    
                    if (monsterPos.getX() > actorPos.get().getX()) {
                        if (canMove(new Pair<>(monsterPos.getX() - 1, monsterPos.getY()), actors, room)) {
                            return monster.getSkills().getAction(GameControl.MOVEUP);
                        }
                    }
    
                    if (monsterPos.getX() < actorPos.get().getX()) {
                        if (canMove(new Pair<>(monsterPos.getX() + 1, monsterPos.getY()), actors, room)) {
                            return monster.getSkills().getAction(GameControl.MOVEDOWN);
                        }
                    }
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

    @Override
    public MovementBehaviour createBrainless() {
        return new MovementBehaviour() {
            @Override
            public Optional<GameCommand> chooseMove(Monster monster, List<Actor> actors, Room room) {
                Pair<Integer, Integer> monsterPos = monster.getPos();
                Random rng = new Random();
                List<Boolean> check = List.of(false, false, false, false);
                while (check.contains(false)) {
                    switch (rng.nextInt(4)) {
                        case 0:
                        if (canMove(new Pair<>(monsterPos.getX(), monsterPos.getY() + 1), actors, room)) {
                            return monster.getSkills().getAction(GameControl.MOVERIGHT);
                        }
                        check.set(0, true);
                        break;

                        case 1:
                        if (canMove(new Pair<>(monsterPos.getX(), monsterPos.getY() - 1), actors, room)) {
                            return monster.getSkills().getAction(GameControl.MOVELEFT);
                        }
                        check.set(1, true);
                        break;

                        case 2:
                        if (canMove(new Pair<>(monsterPos.getX() - 1, monsterPos.getY()), actors, room)) {
                            return monster.getSkills().getAction(GameControl.MOVEUP);
                        }
                        check.set(2, true);
                        break;

                        case 3:
                        if (canMove(new Pair<>(monsterPos.getX() + 1, monsterPos.getY()), actors, room)) {
                            return monster.getSkills().getAction(GameControl.MOVEDOWN);
                        }
                        check.set(3, true);
                        break;

                        default:
                            break;
                    }
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

    @Override
    public MovementBehaviour createShy() {
        return new MovementBehaviour() {
            @Override
            public Optional<GameCommand> chooseMove(Monster monster, List<Actor> actors, Room room) {
                Pair<Integer, Integer> monsterPos = monster.getPos();
                Optional<Pair<Integer, Integer>> actorPos = Optional.of(
                    actors
                        .stream()
                        .sorted((a, b) -> (int)(Pairs.computePPLine(monsterPos, a.getPos()).count() - Pairs.computePPLine(monsterPos, b.getPos()).count()))
                        .filter(a -> ((int)Pairs.computePPLine(monsterPos, a.getPos()).count()) < monster.getStatActual(StatName.DEX))
                        .findFirst()
                        .get()
                        .getPos()
                );
                
                if (actorPos.isPresent()) {
                    if (monsterPos.getY() > actorPos.get().getY()) {
                        if (canMove(new Pair<>(monsterPos.getX(), monsterPos.getY() + 1), actors, room)) {
                            return monster.getSkills().getAction(GameControl.MOVERIGHT);
                        }
                    }
    
                    if (monsterPos.getY() < actorPos.get().getY()) {
                        if (canMove(new Pair<>(monsterPos.getX(), monsterPos.getY() - 1), actors, room)) {
                            return monster.getSkills().getAction(GameControl.MOVELEFT);
                        }
                    }
    
                    if (monsterPos.getX() > actorPos.get().getX()) {
                        if (canMove(new Pair<>(monsterPos.getX() + 1, monsterPos.getY()), actors, room)) {
                            return monster.getSkills().getAction(GameControl.MOVEUP);
                        }
                    }
    
                    if (monsterPos.getX() < actorPos.get().getX()) {
                        if (canMove(new Pair<>(monsterPos.getX() - 1, monsterPos.getY()), actors, room)) {
                            return monster.getSkills().getAction(GameControl.MOVEDOWN);
                        }
                    }
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
