package it.unibo.ruscodc.model.gamemap;

import it.unibo.ruscodc.model.Entity;
import it.unibo.ruscodc.model.actors.Actor;
import it.unibo.ruscodc.utils.Direction;
import it.unibo.ruscodc.utils.Pair;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * The <code>RectangleRoomImpl</code> class creates a basic implementation of the interface <code>Room</code>.
 * The created <code>Room</code> will have a rectangular shape and could have multiple door leading to other rooms.
 */
public class RectangleRoomImpl implements Room {
    private static final int MAX_DOORS_NUM = 4;
    private final Pair<Integer, Integer> size;
    private final List<Tile> tiles = new ArrayList<>();
    private final Set<Actor> monsters = new HashSet<>();
    private final Map<Direction, Optional<Room>> connectedRooms = new HashMap<>();

    /**
     *
     * @param width the width of the room
     * @param height the height of the room
     */
    public RectangleRoomImpl(final int width, final int height) {
        this.size = new Pair<>(width + 1, height + 1);
        this.addTiles();
    }

    /**
     * Creates the room's tiles.
     */
    private void addTiles() {
        final TileFactory tf = new TileFactoryImpl();
        for (int i = 0; i <= this.size.getX(); i++) {
            for (int j = 0; j <= this.size.getY(); j++) {
                if (i == 0 || j == 0 || i == this.size.getX() || j == this.size.getY()) {
                    this.tiles.add(tf.createBaseWallTile(i, j, this.size));
                } else {
                    this.tiles.add(tf.createBaseFloorTile(i, j));
                }
            }
        }
    }

    private Predicate<Tile> isNotCorner() {
        return (Tile t) -> !(t.getPosition().equals(new Pair<>(0, 0))
                || t.getPosition().equals(this.size)
                || t.getPosition().equals(new Pair<>(this.size.getX(), 0))
                || t.getPosition().equals(new Pair<>(0, this.size.getY())));
    }

    /** {@inheritDoc} */
    @Override
    public boolean isInRoom(final Pair<Integer, Integer> pos) {
        return this.tiles.stream().anyMatch(t -> t.getPosition().equals(pos));
    }

    /** {@inheritDoc} */
    @Override
    public Set<Actor> getMonsters() {
        return this.monsters;
    }

    /** {@inheritDoc} */
    @Override
    public Set<Entity> getObjectsInRoom() {
        return this.tiles.stream()
                .filter(tile -> tile.get().isPresent())
                .map(tile -> tile.get().orElseThrow())
                .collect(Collectors.toSet());
    }

    /** {@inheritDoc} */
    @Override
    public List<Entity> getTilesAsEntity() {
        return this.tiles.stream()
                .filter(tile -> tile instanceof Entity)
                .map(tile -> (Entity) tile).toList();
    }

    /** {@inheritDoc} */
    @Override
    public boolean put(final Pair<Integer, Integer> pos, final Entity obj) {
        final Optional<Tile> tile = this.tiles.stream()
                .filter(t -> t.getPosition().equals(pos))
                .findFirst();
        if (tile.isEmpty()) {
            return false;
        }
        return tile.get().put(obj);
    }

    @Override
    public Optional<Tile> get(Pair<Integer, Integer> pos) {
        if (this.isInRoom(pos)) {
            return this.tiles.stream()
                    .filter(tile -> tile.getPosition().equals(pos))
                    .findFirst();
        }
        return Optional.empty();
    }

    /** {@inheritDoc} */
    @Override
    public boolean isAccessible(final Pair<Integer, Integer> pos) {
        final Optional<Tile> tile = this.tiles.stream()
                .filter(t -> t.getPosition().equals(pos))
                .findFirst();

        if (tile.isEmpty()) {
            return false;
        }
        return tile.get().isAccessible();
    }

    /** {@inheritDoc} */
    @Override
    public Optional<Room> getConnectedRoom(final Direction dir) {
        return this.connectedRooms.get(dir);
    }

    /** {@inheritDoc} */
    @Override
    public boolean addConnectedRoom(final Direction dir, final Room other) {
        if (!this.connectedRooms.containsKey(dir)) {
            return false;
        }
        if (this.connectedRooms.get(dir).isPresent()) {
            return false;
        }

        this.connectedRooms.put(dir, Optional.of(other));

        other.addConnectedRoom(dir.getOpposite(), this);
        return true;
    }

    /** {@inheritDoc} */
    @Override
    public void addDoor(final Direction dir) {
        if (this.connectedRooms.size() == MAX_DOORS_NUM) {
            return;
        }
        final Random rnd = new Random();

        final List<Tile> onSide = this.tiles.stream()
                .filter(tile -> tile instanceof  WallTileImpl)
                .filter(this.isNotCorner())
                .toList();

        final Tile tile = onSide.get(rnd.nextInt(onSide.size()));
        // tile.put() // TODO: add Door item
        this.connectedRooms.put(dir, Optional.empty());
    }

    /** {@inheritDoc} */
    @Override
    public Pair<Integer, Integer> getSize() {
        return this.size;
    }

}
