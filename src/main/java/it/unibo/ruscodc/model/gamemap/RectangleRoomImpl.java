package it.unibo.ruscodc.model.gamemap;

import it.unibo.ruscodc.model.Entity;
import it.unibo.ruscodc.model.actors.monster.Monster;
import it.unibo.ruscodc.model.interactable.Interactable;
import it.unibo.ruscodc.utils.Direction;
import it.unibo.ruscodc.utils.Pair;

import java.security.InvalidParameterException;
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
    private final Set<Monster> monsters = new HashSet<>();
    private final Map<Direction, Room> connectedRooms = new HashMap<>();

    /**
     *
     * @param width the width of the room
     * @param height the height of the room
     */
    public RectangleRoomImpl(final int width, final int height) {
        if (width < 3 || height < 3) {
            throw new InvalidParameterException();
        }
        this.size = new Pair<>(width, height);
        this.addTiles();
    }

    /**
     * Creates the room's tiles.
     */
    private void addTiles() {
        final TileFactory tf = new TileFactoryImpl();
        for (int i = 0; i <= this.size.getX() + 1; i++) {
            for (int j = 0; j <= this.size.getY() + 1; j++) {
                if (i == 0 || j == 0 || i == this.size.getX() + 1 || j == this.size.getY() + 1) {
                    this.tiles.add(tf.createBaseWallTile(i, j, this.size));
                } else {
                    this.tiles.add(tf.createBaseFloorTile(i, j));
                }
            }
        }
    }

    private Predicate<Tile> isNotCorner() {
        return (Tile t) -> !(t.getPosition().equals(new Pair<>(0, 0))
                || t.getPosition().equals(new Pair<>(this.size.getX() + 1, 0))
                || t.getPosition().equals(new Pair<>(0, this.size.getY() + 1)))
                || t.getPosition().equals(new Pair<>(this.size.getX() + 1, this.size.getY() + 1));
    }

    /** {@inheritDoc} */
    @Override
    public boolean isInRoom(final Pair<Integer, Integer> pos) {
        return this.tiles.stream().anyMatch(t -> t.getPosition().equals(pos));
    }

    /** {@inheritDoc} */
    @Override
    public boolean addMonster(final Monster monster) {
        final List<Pair<Integer, Integer>> positions = this.monsters.stream().map(Entity::getPos).toList();
        if (positions.contains(monster.getPos()) || !this.isInRoom(monster.getPos())) {
            return false;
        }

        final int minFreeTiles = 5;
        final int freeTiles = (int) this.tiles.stream()
                .filter(tile -> tile.get().isEmpty())
                .count();
        if (positions.size() >= (freeTiles - minFreeTiles)) {
            return false;
        }

        this.monsters.add(monster);
        return true;
    }

    /** {@inheritDoc} */
    @Override
    public Set<Monster> getMonsters() {
        return this.monsters;
    }

    /** {@inheritDoc} */
    @Override
    public Set<Interactable> getObjectsInRoom() {
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
    public boolean put(final Pair<Integer, Integer> pos, final Interactable obj) {
        final Optional<Tile> tile = this.tiles.stream()
                .filter(t -> t.getPosition().equals(pos))
                .findFirst();
        if (tile.isEmpty()) {
            return false;
        }
        return tile.get().put(obj);
    }

    @Override
    public Optional<Tile> get(final Pair<Integer, Integer> pos) {
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
        return Optional.ofNullable(this.connectedRooms.get(dir));
    }

    /** {@inheritDoc} */
    @Override
    public boolean addConnectedRoom(final Direction dir, final Room other) {
        if (!this.connectedRooms.containsKey(dir) || dir == Direction.UNDEFINED) {
            return false;
        }
        if (this.getConnectedRoom(dir).isPresent()) {
            return false;
        }
        this.connectedRooms.put(dir, other);
        other.addConnectedRoom(dir.getOpposite(), this);
        return true;
    }

    /** {@inheritDoc} */
    @Override
    public boolean addDoor(final Direction dir) {
        if (this.connectedRooms.size() == MAX_DOORS_NUM || dir == Direction.UNDEFINED
                || this.connectedRooms.containsKey(dir)) {
            return false;
        }
        final Random rnd = new Random();
        final List<Tile> onSide = this.tiles.stream()
                .filter(tile -> tile instanceof  WallTileImpl)
                .filter(this.isNotCorner())
                .toList();

        final Tile tile = onSide.get(rnd.nextInt(onSide.size()));
        // tile.put() // TODO: add Door item
        this.connectedRooms.put(dir, null);
        return true;
    }

    /** {@inheritDoc} */
    @Override
    public Pair<Integer, Integer> getSize() {
        return this.size;
    }

    @Override
    public boolean replaceTile(final Pair<Integer, Integer> pos, final Tile newTile) {
        if (!pos.equals(newTile.getPosition()) || this.get(pos).isEmpty()) {
            return false;
        }
        final Optional<Interactable> content = this.get(pos).get().get();
        if (content.isPresent()) {
            // Cannot replace an occupied Tile!
            return false;
        }
        this.tiles.removeIf(t -> t.getPosition().equals(pos));
        this.tiles.add(newTile);
        return true;
    }

}
