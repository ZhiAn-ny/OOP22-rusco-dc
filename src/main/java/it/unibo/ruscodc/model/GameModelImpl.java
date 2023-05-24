package it.unibo.ruscodc.model;

import it.unibo.ruscodc.model.actors.Actor;
import it.unibo.ruscodc.model.actors.hero.Hero;
import it.unibo.ruscodc.model.actors.hero.HeroImpl;
import it.unibo.ruscodc.model.gamemap.Floor;
import it.unibo.ruscodc.model.gamemap.FloorImpl;
import it.unibo.ruscodc.model.gamemap.Room;
import it.unibo.ruscodc.model.gamemap.Tile;
import it.unibo.ruscodc.model.interactable.Door;
import it.unibo.ruscodc.model.interactable.Interactable;
import it.unibo.ruscodc.utils.Direction;
import it.unibo.ruscodc.utils.Pair;

import java.util.List;
import java.util.Optional;

/**
 * This class process the input received that will help the view to print all.
 */
public class GameModelImpl implements GameModel {
    private int nFloorsExplored;
    private Floor floor;
    private final Hero hero;

    /**
     * Class constructor.
     */
    public GameModelImpl() {
        this.floor = new FloorImpl();
        this.nFloorsExplored = 1;
        this.hero = new HeroImpl(null, null, null, null);
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
        return List.of(hero);
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
            return getParty().stream().map(A -> (Entity) A).toList();
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

    @Override
    public void changeRoom(final Pair<Integer, Integer> pos) {
        final Room current = this.floor.getCurrentRoom();
        if (!current.isInRoom(pos)) {
            return;
        }
        if (pos.getX() == 0) {
            this.floor.goToRoom(Direction.LEFT);
        } else if (pos.getY() == 0) {
            this.floor.goToRoom(Direction.UP);
        } else if (pos.getY().equals(current.getSize().getY())) {
            this.floor.goToRoom(Direction.DOWN);
        } else if (pos.getX().equals(current.getSize().getX())) {
            this.floor.goToRoom(Direction.RIGHT);
        }
    }

    @Override
    public void changeFloor() {
        this.nFloorsExplored = this.nFloorsExplored + 1;
        this.floor = new FloorImpl();
    }
}
