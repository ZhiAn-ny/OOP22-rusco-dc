package it.unibo.ruscodc.model;

import it.unibo.ruscodc.model.actors.Actor;
import it.unibo.ruscodc.model.actors.hero.Hero;
import it.unibo.ruscodc.model.actors.hero.HeroImpl;
import it.unibo.ruscodc.model.actors.hero.HeroSkill;
import it.unibo.ruscodc.model.actors.hero.HeroStat;
import it.unibo.ruscodc.model.actors.monster.Monster;
import it.unibo.ruscodc.model.actors.skill.Skill;
import it.unibo.ruscodc.model.actors.stat.Stat;
import it.unibo.ruscodc.model.actors.stat.StatImpl.StatName;
import it.unibo.ruscodc.model.gamecommand.playercommand.Interact;
import it.unibo.ruscodc.model.gamemap.Floor;
import it.unibo.ruscodc.model.gamemap.FloorImpl;
import it.unibo.ruscodc.model.gamemap.Room;
import it.unibo.ruscodc.model.interactable.Interactable;
import it.unibo.ruscodc.model.outputinfo.Portrait;
import it.unibo.ruscodc.model.outputinfo.PortraitImpl;
import it.unibo.ruscodc.utils.Direction;
import it.unibo.ruscodc.utils.GameControl;
import it.unibo.ruscodc.utils.Pair;
import it.unibo.ruscodc.utils.Pairs;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * This class process the input received that will help the view to print all.
 */
public class GameModelImpl implements GameModel {
    private int nFloorsExplored;
    private Floor floor;
    private final Hero hero;
    private final Pair<Integer, Integer> initialPosition = new Pair<>(3, 3);

    /**
     * Class constructor.
     */
    public GameModelImpl() {
        this.nFloorsExplored = 1;
        this.floor = new FloorImpl(this.nFloorsExplored);
        final Skill skills = new HeroSkill();
        final Stat stats = new HeroStat();
        skills.setAction(GameControl.INTERACT, new Interact());
        this.hero = new HeroImpl("Rusco", this.initialPosition, skills, stats);
    }

    private List<Actor> getParty() {
        return List.of(hero);
    }

    /**
     * list with the initiative of actor.
     * @return the list of hero
     */
    @Override
    public List<Actor> getActorByInitative() {
        final List<Actor> list = new ArrayList<>();
        list.add(hero);
        final List<Monster> monsters = this.getCurrentRoom().getMonsters();
        list.addAll(monsters);
        list.sort(Comparator.comparingInt(a -> a.getStatActual(StatName.DEX)));
        return list;
    }

    /**
     * This method say if the tile is accessible.
     * @return information of specific tile
     */
    @Override
    public List<Entity> getTile() {
        return this.floor.getCurrentRoom().getTilesAsEntity();
    }

    /**
     *
     * @param toGet
     * @return a list with the info of the entity.
     */
    @Override
    public List<Entity> getInfo(final Entity toGet) {
        if (toGet instanceof Hero) {
            return getParty().stream().map(a -> (Entity) a).toList();
        }
        return List.of();
    }

    /**
     * Get a current room.
     * @return the current room
     */
    @Override
    public Room getCurrentRoom() {
        return this.floor.getCurrentRoom();
    }

    /** {@inheritDoc} */
    @Override
    public void changeRoom(final Pair<Integer, Integer> pos) {
        if (!this.floor.getCurrentRoom().isInRoom(pos)) {
            return;
        }
        final Direction dir = this.getDoorDirection(pos.getX(), pos.getY());
        this.floor.goToRoom(dir);

        final Optional<Interactable> door = this.floor.getCurrentRoom()
                                          .getDoorOnSide(dir.getOpposite());
        if (door.isEmpty()) {
            throw new IllegalStateException("Door not found");
        }
        this.respawnParty(this.moveToDirection(door.get().getPos(), dir.getOpposite()));
    }

    private Direction getDoorDirection(final int x, final int y) {
        if (x == 0) {
            return Direction.LEFT;
        } else if (y == 0) {
            return Direction.UP;
        } else if (y == this.floor.getCurrentRoom().getSize().getY() + 1) {
            return Direction.DOWN;
        } else if (x == this.floor.getCurrentRoom().getSize().getX() + 1) {
            return Direction.RIGHT;
        }
        return Direction.UNDEFINED;
    }

    private Pair<Integer, Integer> moveToDirection(final Pair<Integer, Integer> pos, final Direction dir) {
        return switch (dir) {
            case UP -> Pairs.computeDownPair(pos);
            case DOWN -> Pairs.computeUpPair(pos);
            case RIGHT -> Pairs.computeLeftPair(pos);
            case LEFT -> Pairs.computeRightPair(pos);
            default -> pos;
        };
    }

    private void respawnParty(final Pair<Integer, Integer> pos) {
        final int radius = 1;
        this.hero.setPos(pos);
        this.getCurrentRoom().clearArea(pos, radius);
    }

    /** {@inheritDoc} */
    @Override
    public void changeFloor() {
        this.nFloorsExplored = this.nFloorsExplored + 1;
        this.floor = new FloorImpl(this.nFloorsExplored);

        this.respawnParty(this.initialPosition);
    }

    /** {@inheritDoc} */
    @Override
    public Floor getCurrentFloor() {
        return this.floor;
    }

    /** {@inheritDoc} */
    @Override
    public Portrait getRuscoInfo() {
        return new PortraitImpl(hero,
            (hero.getStatActual(StatName.HP) * 1.0) / (hero.getStatMax(StatName.HP) * 1.0),
            (hero.getStatActual(StatName.AP) * 1.0) / (hero.getStatMax(StatName.AP) * 1.0));
    }

    @Override
    public boolean isGameOver() {
        return !hero.isAlive();
    }
}
