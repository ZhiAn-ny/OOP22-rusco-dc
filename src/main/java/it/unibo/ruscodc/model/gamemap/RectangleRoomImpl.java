package it.unibo.ruscodc.model.gamemap;

import it.unibo.ruscodc.model.Entity;
import it.unibo.ruscodc.model.actors.Actor;
import it.unibo.ruscodc.utils.Direction;
import it.unibo.ruscodc.utils.Pair;

import java.util.*;

/**
 * The <code>RectangleRoomImpl</code> class creates a basic implementation of the interface <code>Room</code>.
 * The created <code>Room</code> will have a rectangular shape and could have multiple door leading to other rooms.
 */
public class RectangleRoomImpl implements Room {
    private final Pair<Integer, Integer> size;
    private final List<Tile> tiles = new ArrayList<>();
    private final Set<Actor> monsters = new HashSet<>();
    private final Map<Direction, Optional<Room>> connectedRooms = new HashMap<>();

    public RectangleRoomImpl(final int width, final int height) {
        this.size = new Pair<Integer, Integer>(width+1, height+1);
        this.addTiles();
        this.addDoors();
    }

    /**
     * Creates the room's tiles.
     */
    private void addTiles() {
        TileFactory tf = new TileFactoryImpl();

        for (int i = 0; i <= this.size.getX(); i++) {
            for (int j = 0; j <= this.size.getY(); j++) {
                if (i == 0 || j == 0 || i == this.size.getX() || j == this.size.getY())
                    this.tiles.add(tf.createBaseWallTile(i,j, this.size));
                else
                    this.tiles.add(tf.createBaseFloorTile(i, j));
            }
        }
    }

    /**
     * Inserts a random number of doors in the room.
     */
    private void addDoors() {
        // TODO:
    }

    @Override
    public boolean isInRoom(final Pair<Integer, Integer> pos) {
        return this.tiles.stream().anyMatch(t -> t.getPosition().equals(pos));
    }

    @Override
    public Set<Actor> getMonsters() {
        return this.monsters;
    }

    @Override
    public Set<Entity> getObjectsInRoom() {
        Set<Entity> objs = new HashSet<>();
        this.tiles.forEach(tile -> {
            if (tile.get().isPresent())
                objs.add(tile.get().get());
        });
        return objs;
    }

    @Override
    public List<Entity> getTilesAsEntity() {
        List<Entity> asEntity = new ArrayList<>();
        this.tiles.forEach(tile -> {
            if (tile instanceof Entity)
                asEntity.add((Entity) tile);
        });
        return asEntity;
    }

    @Override
    public boolean put(final Pair<Integer, Integer> pos, final Entity obj) {
        Optional<Tile> tile = this.tiles.stream()
                .filter(t -> t.getPosition().equals(pos))
                .findFirst();
        if (tile.isEmpty())
            return false;
        return tile.get().put(obj);
    }

    @Override
    public boolean isAccessible(final Pair<Integer, Integer> pos) {
        Optional<Tile> tile = this.tiles.stream()
                .filter(t -> t.getPosition().equals(pos))
                .findFirst();

        if (tile.isEmpty())
            return false;
        return tile.get().isAccessible();
    }

    @Override
    public Optional<Room> getConnectedRoom(final Direction dir) {
        return this.connectedRooms.get(dir);
    }

    @Override
    public boolean addConnectedRoom(final Direction dir, final Room other) {
        if (!this.connectedRooms.containsKey(dir))
            return false;
        if (this.connectedRooms.get(dir).isPresent())
            return false;

        this.connectedRooms.put(dir, Optional.of(other));

        other.addConnectedRoom(dir.getOpposite(), this);
        return true;
    }

}
