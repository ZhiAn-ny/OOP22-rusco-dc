package it.unibo.ruscodc.model.gamemap;

import it.unibo.ruscodc.model.Entity;
import it.unibo.ruscodc.model.actors.monster.Monster;
import it.unibo.ruscodc.model.interactable.Door;
import it.unibo.ruscodc.model.interactable.Interactable;
import it.unibo.ruscodc.model.interactable.Stair;
import it.unibo.ruscodc.utils.Direction;
import it.unibo.ruscodc.utils.Pair;
import it.unibo.ruscodc.utils.Pairs;

import java.io.Serial;
import java.io.Serializable;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.function.Predicate;

/**
 * The <code>RectangleRoomImpl</code> class creates a basic implementation of the interface <code>Room</code>.
 * The created <code>Room</code> will have a rectangular shape and could have multiple door leading to other rooms.
 */
public class RectangleRoomImpl implements Room, Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private static final int MAX_DOORS_NUM = 4;
    private final Pair<Integer, Integer> size;
    private final List<Tile> tiles = new ArrayList<>();
    private final List<Monster> monsters = new ArrayList<>();
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
                || t.getPosition().equals(new Pair<>(0, this.size.getY() + 1))
                || t.getPosition().equals(new Pair<>(this.size.getX() + 1, this.size.getY() + 1)));
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
        final Tile tile = this.tiles.stream()
                .filter(t -> t.getPosition().equals(monster.getPos()))
                .findFirst().orElse(null);

        // Items do not represent an obstacle to movement therefore,
        // monsters can be placed on top of an occupied Tile
        if (tile == null) { // || tile.get().isPresent()) {
            return  false;
        }
        this.monsters.add(monster);
        return true;
    }

    /** {@inheritDoc} */
    @Override
    public List<Monster> getMonsters() {
        return this.monsters;
    }

    /** {@inheritDoc} */
    @Override
    public List<Interactable> getObjectsInRoom() {
        return this.tiles.stream()
                .filter(tile -> tile.get().isPresent())
                .map(tile -> tile.get().orElseThrow())
                .toList();
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

    /** {@inheritDoc} */
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
    public Map<Direction, Room> getConnectedRooms() {
        return this.connectedRooms;
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
        other.addDoor(dir.getOpposite());
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
                .filter(this.onSide(dir))
                .filter(this.isNotCorner())
                .toList();

        final Tile tile = onSide.get(rnd.nextInt(onSide.size()));
        this.replaceTile(tile.getPosition(), new FloorTileImpl(tile.getPosition(), true));
        this.put(tile.getPosition(), new Door(tile.getPosition(), this.getSide(tile.getPosition())));

        this.connectedRooms.put(dir, null);
        return true;
    }

    /**
     * Return on which side is placed the specified position.
     * @param pos the position to check
     * @return the side of the room on which lies the specified position
     */
    private Direction getSide(final Pair<Integer, Integer> pos) {
        if (pos.getX() == 0) {
            return Direction.LEFT;
        }
        if (pos.getY() == 0) {
            return Direction.UP;
        }
        if (pos.getX() == this.size.getX() + 1) {
            return Direction.RIGHT;
        }
        if (pos.getY() == this.size.getY() + 1) {
            return Direction.DOWN;
        }
        return Direction.UNDEFINED;
    }

    /** {@inheritDoc} */
    @Override
    public boolean addStairs(final Direction dir) {
        Tile tile;
        final Optional<Interactable> door = this.getDoorOnSide(dir);
        if (door.isPresent()) {
            tile = this.get(door.get().getPos()).orElse(null);
            if (tile == null) {
                return false;
            }
            tile.empty();
        } else {
            final List<Tile> onSide = this.tiles.stream()
                    .filter(this.onSide(dir))
                    .filter(this.isNotCorner())
                    .toList();
            tile = onSide.get(new Random().nextInt(onSide.size()));
            tile = new FloorTileImpl(tile.getPosition(), true);
            this.replaceTile(tile.getPosition(), tile);
        }
        tile.put(new Stair(tile.getPosition()));
        return true;
    }

    private Predicate<Tile> onSide(final Direction dir) {
        return (Tile tile) -> switch (dir) {
            case UP -> tile.getPosition().getY() == 0;
            case DOWN -> tile.getPosition().getY() == this.size.getY() + 1;
            case RIGHT -> tile.getPosition().getX() == this.size.getX() + 1;
            case LEFT -> tile.getPosition().getX() == 0;
            default -> false;
        };
    }

    /** {@inheritDoc} */
    @Override
    public Pair<Integer, Integer> getSize() {
        return this.size;
    }

    /** {@inheritDoc} */
    @Override
    public int getArea() {
        return this.size.getX() * this.size.getY();
    }

    /** {@inheritDoc} */
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

    /** {@inheritDoc} */
    @Override
    public Optional<Interactable> getDoorOnSide(final Direction side) {
        final Optional<Tile> tile = this.tiles.stream().filter(this.onSide(side))
                .filter(this.isNotCorner())
                .filter(t -> t.get().isPresent() && t.get().get() instanceof Door)
                .findFirst();
        if (tile.isPresent()) {
            return tile.get().get();
        }
        return Optional.empty();
    }

    /** {@inheritDoc} */
    @Override
    public void clearArea(final Pair<Integer, Integer> pos, final int rad) {
        final List<Tile> tilesInArea = new ArrayList<>();
        Pairs.computeCircle(pos, rad, true).forEach(str -> str.forEach(xy ->
            this.get(xy).ifPresent(tilesInArea::add)
        ));
        final List<Pair<Integer, Integer>> positions = tilesInArea.stream()
                .peek(tile -> {
                    if (tile.get().isEmpty() || tile.get().get() instanceof Door) {
                        return;
                    }
                    tile.empty();
                })
                .map(Tile::getPosition).toList();
        this.monsters.removeIf(m -> positions.contains(m.getPos()));
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        // TODO: remove when view is working
        final StringBuilder str = new StringBuilder("size: " + this.size.getX() + ", " + this.size.getY() + "\n");
        for (int y = 0; y < this.size.getY() + 2; y++) {
            for (int x = 0; x < this.size.getX() + 2; x++) {
                final Tile t = this.get(new Pair<>(x, y)).orElse(null);
                if (t == null) {
                    str.append("NIL");
                    continue;
                }
                final String tileStr = t.toString();
                if (t.get().isPresent()) {
                    str.append(tileStr);
                } else if (this.monsters.stream().anyMatch(m -> m.getPos().equals(t.getPosition()))) {
                    str.append("[M]");
                } else {
                    str.append(tileStr);
                }
            }
            str.append("\n");
        }
        return str.toString();
    }

}
