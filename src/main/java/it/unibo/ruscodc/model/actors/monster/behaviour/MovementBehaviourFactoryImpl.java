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

    private static final Random RNG = new Random();

    private Optional<Pair<Integer, Integer>> getActorTarget(
        final Pair<Integer, Integer> monsterPos,
        final List<Actor> actors,
        final Predicate<Actor> filter
    ) {
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

    private void setGameCommand(final GameCommand action, final Monster monster, final Room room) {
        action.setActor(monster);
        action.setRoom(room);
    }

    private int getDeltaX(final Pair<Integer, Integer> from, final Pair<Integer, Integer> to) {
        return from.getX() - to.getX();
    }

    private int getDeltaY(final Pair<Integer, Integer> from, final Pair<Integer, Integer> to) {
        return from.getY() - to.getY();
    }

    private boolean canMove(final Pair<Integer, Integer> pos, final List<Actor> actors, final Room room) {
        return room.isAccessible(pos)
            && !actors.stream().anyMatch(a -> a.getPos().equals(pos))
            && !room.getMonsters().stream().anyMatch(a -> a.getPos().equals(pos));
    }

    private Optional<GameControl> computeGameCommand(
        final Monster monster,
        final List<Actor> actors,
        final Pair<Integer, Integer> actorPos,
        final Room room
    ) {
        final int deltaX = getDeltaX(monster.getPos(), actorPos);
        final Pair<Integer, Integer> monsterPos = monster.getPos();

        if (deltaX > 0 && canMove(Pairs.computeLeftPair(monsterPos), actors, room)) {
            return Optional.of(GameControl.MOVELEFT);
        }
        if (deltaX < 0 && canMove(Pairs.computeRightPair(monsterPos), actors, room)) {
            return Optional.of(GameControl.MOVERIGHT);
        }

        final int deltaY = getDeltaY(monster.getPos(), actorPos);

        if (deltaY > 0 && canMove(Pairs.computeUpPair(monsterPos), actors, room)) {
            return Optional.of(GameControl.MOVEUP);
        }

        if (deltaY < 0 && canMove(Pairs.computeDownPair(monsterPos), actors, room)) {
            return Optional.of(GameControl.MOVEDOWN);
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
                Optional<GameCommand> action = Optional.empty();

                final Optional<Pair<Integer, Integer>> actorPos = getActorTarget(
                    monster.getPos(),
                    actors,
                    (a) -> true
                );

                final Optional<GameControl> gc = computeGameCommand(monster, actors, actorPos.get(), room);

                if (gc.isPresent()) {
                    action = monster.getSkills().getAction(gc.get());
                    setGameCommand(action.get(), monster, room);
                }

                return action;
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
                final Pair<Integer, Integer> monsterPos = monster.getPos();
                final List<Boolean> check = List.of(false, false, false, false);
                Optional<GameCommand> action;
                while (check.contains(false)) {
                    switch (RNG.nextInt(4)) {
                        case 0:
                        if (canMove(new Pair<>(monsterPos.getX(), monsterPos.getY() + 1), actors, room)) {
                            action = monster.getSkills().getAction(GameControl.MOVERIGHT);
                            setGameCommand(action.get(), monster, room);
                            return action;
                        }
                        check.set(0, true);
                        break;

                        case 1:
                        if (canMove(new Pair<>(monsterPos.getX(), monsterPos.getY() - 1), actors, room)) {
                            action = monster.getSkills().getAction(GameControl.MOVELEFT);
                            setGameCommand(action.get(), monster, room);
                            return action;
                        }
                        check.set(1, true);
                        break;

                        case 2:
                        if (canMove(new Pair<>(monsterPos.getX() - 1, monsterPos.getY()), actors, room)) {
                            action = monster.getSkills().getAction(GameControl.MOVEUP);
                            setGameCommand(action.get(), monster, room);
                            return action;
                        }
                        check.set(2, true);
                        break;

                        case 3:
                        if (canMove(new Pair<>(monsterPos.getX() + 1, monsterPos.getY()), actors, room)) {
                            action = monster.getSkills().getAction(GameControl.MOVEDOWN);
                            setGameCommand(action.get(), monster, room);
                            return action;
                        }
                        check.set(3, true);
                        break;

                        default:
                        break;
                    }
                }
                return Optional.empty();
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

                Optional<GameCommand> action = Optional.empty();

                final Optional<Pair<Integer, Integer>> actorPos = getActorTarget(
                    monster.getPos(),
                    actors,
                    (a) ->  (int) Pairs.computePPLine(monster.getPos(), a.getPos()).count() < monster.getStatActual(StatName.DEX)
                );

                Optional<GameControl> gc = computeGameCommand(monster, actors, actorPos.get(), room);

                if (gc.isPresent()) {
                    final GameControl old = gc.get();
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
                    action = monster.getSkills().getAction(gc.get());
                    setGameCommand(action.get(), monster, room);
                }

                return action;
            }
        };
    }
}
