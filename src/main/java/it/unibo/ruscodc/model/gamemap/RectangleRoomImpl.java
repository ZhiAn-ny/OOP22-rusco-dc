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
    private final Pair<Integer, Integer> size;
    private final List<Tile> tiles = new ArrayList<>();
    private final Set<Actor> monsters = new HashSet<>();
    private final Map<Direction, Optional<Room>> connectedRooms = new HashMap<>();

    /**
     *
     * @param width the width of the room
     * @param height the height of the room
     * @param doors the number of door to add
     */
    public RectangleRoomImpl(final int width, final int height, final int doors) {
        this.size = new Pair<>(width + 1, height + 1);
        this.addTiles();
        this.addDoors(doors);
    }

    /**
     *
     * @param width the width of the room
     * @param height the height of the room
     */
    public RectangleRoomImpl(final int width, final int height) {
        // Where 4 is the maximum number of doors and 1 is the minimum
        this(width, height, new Random().nextInt(4) + 1);
    }

    /**
     * Creates the room's tiles.
     */
    private void addTiles() {
        final TileFactory tf = new TileFactoryImpl();
        for (int i = 0; i <= this.size.getX(); i++) {
            for (int j = 0; j <= this.size.getY(); j++) {
                if (i == 0 || j == 0 || i == this.size.getX() || j == this.size.getY()) {
                    this.tiles.add(tf.createBaseWallTile(i,j, this.size));
                } else {
                    this.tiles.add(tf.createBaseFloorTile(i, j));
                }
            }
        }
    }

    /**
     * Inserts doors on a random side of the room.
     * @param doors the number of doors to add.
     */
    private void addDoors(final int doors) {
        final Random rnd = new Random();
        int i = 0;

        while (i < doors) {
            Direction dir = Direction.values()[rnd.nextInt(Direction.values().length)];
            Predicate<Tile> isOnRoomSide = this.getTilesOnSideFilter(dir);
            List<Tile> onSide = this.tiles.stream()
                    .filter(isOnRoomSide)
                    .filter(this.isNotCorner())
                    .toList();

            if (onSide.size() == 0 || onSide.stream().anyMatch(tile -> !(tile instanceof WallTileImpl))) {
                continue; // Already placed a door on this side
            }
            final Pair<Integer, Integer> pos = onSide.get(rnd.nextInt(onSide.size()))
                    .getPosition();
            this.tiles.removeIf(tile -> tile.getPosition().equals(pos));
            this.tiles.add(new FloorTileImpl(pos, true));
            this.connectedRooms.put(dir, Optional.empty());
            i = i + 1;
        }
    }

    private Predicate<Tile> getTilesOnSideFilter(final Direction dir) {
        return switch (dir) {
            case UP -> (Tile t) -> t.getPosition().getY() == 0;
            case LEFT -> (Tile t) -> t.getPosition().getX() == 0;
            case DOWN -> (Tile t) -> t.getPosition().getY().equals(this.size.getY());
            case RIGHT -> (Tile t) -> t.getPosition().getX().equals(this.size.getX());
            default -> (Tile t) -> false;
        };
    }

    private Predicate<Tile> isNotCorner() {
        return (Tile t) -> !(t.getPosition().equals(new Pair<>(0 ,0))
                || t.getPosition().equals(this.size)
                || t.getPosition().equals(new Pair<>(this.size.getX() ,0))
                || t.getPosition().equals(new Pair<>(0 ,this.size.getY())));
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
        return this.tiles.stream()
                .filter(tile -> tile.get().isPresent())
                .map(tile -> tile.get().orElseThrow())
                .collect(Collectors.toSet());
    }

    @Override
    public List<Entity> getTilesAsEntity() {
        return this.tiles.stream()
                .filter(tile -> tile instanceof Entity)
                .map(tile -> (Entity) tile).toList();
    }

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
    public boolean isAccessible(final Pair<Integer, Integer> pos) {
        final Optional<Tile> tile = this.tiles.stream()
                .filter(t -> t.getPosition().equals(pos))
                .findFirst();

        if (tile.isEmpty()) {
            return false;
        }
        return tile.get().isAccessible();
    }

    @Override
    public Optional<Room> getConnectedRoom(final Direction dir) {
        return this.connectedRooms.get(dir);
    }

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

    @Override
    public void addDoor() {
        if (this.connectedRooms.size() == 4) return;
        this.addDoors(1);
    }

    @Override
    public Pair<Integer, Integer> getSize() {
        return this.size;
    }

}
