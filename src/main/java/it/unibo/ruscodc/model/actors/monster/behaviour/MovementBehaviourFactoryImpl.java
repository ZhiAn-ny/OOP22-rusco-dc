package it.unibo.ruscodc.model.actors.monster.behaviour;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.function.Predicate;

import it.unibo.ruscodc.model.actors.Actor;
import it.unibo.ruscodc.model.actors.monster.Monster;
import it.unibo.ruscodc.model.actors.stat.StatImpl.StatName;
import it.unibo.ruscodc.model.gamecommand.GameCommand;
import it.unibo.ruscodc.model.gamemap.Room;
import it.unibo.ruscodc.utils.GameControl;
import it.unibo.ruscodc.utils.Pair;
import it.unibo.ruscodc.utils.Pairs;

/**
 * The implementation of the Movement Behaviour Factory interface that for each method returns the specific Movement Behaviour.
 */
public class MovementBehaviourFactoryImpl implements MovementBehaviourFactory {

    private Optional<Pair<Integer, Integer>> getActorTarget(final Pair<Integer, Integer> monsterPos, final List<Actor> actors, final Predicate<Actor> filter) {
        return Optional.of(actors
            .stream()
            .sorted((a, b) ->
                (int) (
                    Pairs.computePPLine(monsterPos, a.getPos()).count()
                    - Pairs.computePPLine(monsterPos, b.getPos()).count()
                )
            )
            .filter(filter)
            .findFirst()
            .get()
            .getPos()
        );
    }

    private int getDeltaX(final Pair<Integer, Integer> from, final Pair<Integer, Integer> to) {
        return from.getX() - to.getX();
    }

    private int getDeltaY(final Pair<Integer, Integer> from, final Pair<Integer, Integer> to) {
        return from.getY() - to.getY();
    }

    private boolean canMove(final Pair<Integer, Integer> pos, final List<Actor> actors, final Room room) {
        return room.isAccessible(pos)
            && actors.stream().anyMatch(a -> a.getPos() == pos)
            && room.getMonsters().stream().anyMatch(a -> a.getPos() == pos);
    }

    private Optional<GameControl> computeGameCommand(final Monster monster, final List<Actor> actors, final Pair<Integer, Integer> actorPos, final Room room) {

        int deltaX = getDeltaX(monster.getPos(), actorPos);
        int deltaY = getDeltaY(monster.getPos(), actorPos);
        Pair<Integer, Integer> monsterPos = monster.getPos();

        if (deltaX < 0) {
            if (canMove(Pairs.computeLeftPair(monsterPos), actors, room)) {
                return Optional.of(GameControl.MOVELEFT);
            }
        }
        if (deltaX > 0) {
            if (canMove(Pairs.computeRightPair(monsterPos), actors, room)) {
                return Optional.of(GameControl.MOVERIGHT);
            }
        }
        if (deltaY < 0) {
            if (canMove(Pairs.computeUpPair(monsterPos), actors, room)) {
                return Optional.of(GameControl.MOVEUP);
            }
        }
        if (deltaY > 0) {
            if (canMove(Pairs.computeDownPair(monsterPos), actors, room)) {
                return Optional.of(GameControl.MOVEDOWN);
            }
        }

        return Optional.empty();
    }

    /**
     * 
     */
    @Override
    public MovementBehaviour createAggressive() {
        return new MovementBehaviour() {

            @Override
            public Optional<GameCommand> chooseMove(final Monster monster, final List<Actor> actors, final Room room) {

                Optional<Pair<Integer, Integer>> actorPos = getActorTarget(
                    monster.getPos(),
                    actors,
                    (a) -> true
                );

                Optional<GameControl> gc = computeGameCommand(monster, actors, actorPos.get(), room);
                return gc.map(
                    gcc -> 
                    monster.getSkills().getAction(gcc).isPresent() 
                        ? monster.getSkills().getAction(gcc).get() 
                        : null
                );
            }
        };
    }

    /**
     * 
     */
    @Override
    public MovementBehaviour createBrainless() {
        return new MovementBehaviour() {
            @Override
            public Optional<GameCommand> chooseMove(final Monster monster, final List<Actor> actors, final Room room) {
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

            private boolean canMove(final Pair<Integer, Integer> pos, final List<Actor> actors, final Room room) {
                return room.isAccessible(pos)
                    && actors.stream().anyMatch(a -> a.getPos() == pos)
                    && room.getMonsters().stream().anyMatch(a -> a.getPos() == pos);
            }
        };
    }

    /**
     * 
     */
    @Override
    public MovementBehaviour createShy() {
        return new MovementBehaviour() {
            @Override
            public Optional<GameCommand> chooseMove(final Monster monster, final List<Actor> actors, final Room room) {

                Optional<Pair<Integer, Integer>> actorPos = getActorTarget(
                    monster.getPos(),
                    actors,
                    (a) ->  (int) Pairs.computePPLine(monster.getPos(), a.getPos()).count() < monster.getStatActual(StatName.DEX)
                );

                Optional<GameControl> gc = computeGameCommand(monster, actors, actorPos.get(), room);

                if (gc.isPresent()) {
                    GameControl old = gc.get();
                    if (old.equals(GameControl.MOVEDOWN)) {
                        gc = Optional.of(GameControl.MOVEUP);
                    }
                    if (old.equals(GameControl.MOVEUP)) {
                        gc = Optional.of(GameControl.MOVEDOWN);
                    }
                    if (old.equals(GameControl.MOVELEFT)) {
                        gc = Optional.of(GameControl.MOVERIGHT);
                    }
                    if (old.equals(GameControl.MOVERIGHT)) {
                        gc = Optional.of(GameControl.MOVELEFT);
                    }
                }

                return gc.map(
                    gcc -> 
                    monster.getSkills().getAction(gcc).isPresent() 
                        ? monster.getSkills().getAction(gcc).get() 
                        : null
                );
            };
        };
    }
}
