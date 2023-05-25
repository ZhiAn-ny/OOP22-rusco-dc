package it.unibo.ruscodc.model.gamemap;

import it.unibo.ruscodc.model.actors.monster.Monster;
import it.unibo.ruscodc.model.actors.monster.MonsterGenerator;
import it.unibo.ruscodc.model.actors.monster.MonsterGeneratorImpl;
import it.unibo.ruscodc.model.interactable.Chest;
import it.unibo.ruscodc.utils.Direction;

import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * The <coode>RoomFactory</coode> class can be used to generate different
 * types of rooms in the game.
 */
public class RoomFactoryImpl implements RoomFactory {
    private final Random rnd = new Random();
    private static final int MIN_ROOM_SIZE = 3;
    private static final int MAX_ROOM_SIZE = 20;
    private static final int MAX_DOORS_NUM = 4;
    private final MonsterGenerator monsterGen = new MonsterGeneratorImpl();

    /** {@inheritDoc} */
    @Override
    public Room randomRoomNoTraps() {
        final Room base = this.getRandomShapeRoom();
        this.addDoors(base);

        return this.getRandomShapeRoom();
    }

    @Override
    public Room randomRoomWithTraps() {
        final Room base = this.randomRoomNoTraps();
        final TileFactory tf = new TileFactoryImpl();

        base.getTilesAsEntity().stream()
                .filter(tile -> tile instanceof FloorTileImpl)
                .forEach(tile -> {
                    final Tile newTile = tf.createRandomFloorTile(tile.getPos().getX(), tile.getPos().getY());
                    base.replaceTile(tile.getPos(), newTile);
                });

        return base;
    }

    private Room getRandomShapeRoom() {
        return (this.rnd.nextInt() % 2 == 0) ?
                this.squareRoom(this.rnd.nextInt(MIN_ROOM_SIZE, MAX_ROOM_SIZE)) :
                this.rectangleRoom(this.rnd.nextInt(MIN_ROOM_SIZE, MAX_ROOM_SIZE), this.rnd.nextInt(MIN_ROOM_SIZE, MAX_ROOM_SIZE));
    }

    /** {@inheritDoc} */
    @Override
    public Room squareRoom(final int size) {
        return new RectangleRoomImpl(size, size);
    }

    /** {@inheritDoc} */
    @Override
    public Room rectangleRoom(final int width, final int height) {
        return new RectangleRoomImpl(width, height);
    }

    /** {@inheritDoc} */
    @Override
    public Room stairsRoom() {
        final Room base = this.randomRoomNoTraps();

        Direction dir = Direction.values()[rnd.nextInt(Direction.values().length)];
        while(!base.addStairs(dir)) {
            // Could not add stairs, retry on different direction
            dir = Direction.values()[rnd.nextInt(Direction.values().length)];
        }
        return base;
    }

    /** {@inheritDoc} */
    @Override
    public void addDoors(final Room room) {
        int i = new Random().nextInt(1, MAX_DOORS_NUM);
        while (i > 0) {
            Direction dir = Direction.values()[rnd.nextInt(Direction.values().length)];
            if (room.addDoor(dir)) {
                i = i - 1;
            }
        }
    }

    /** {@inheritDoc} */
    @Override
    public void addItems(final Room base, final int floor) {
        final Random rnd = new Random();
        int chestNum = rnd.nextInt(this.maxOccupation(base) / MIN_ROOM_SIZE);
        final List<Tile> tiles = base.getTilesAsEntity().stream()
                .filter(tile -> tile instanceof FloorTileImpl)
                .map(tile -> (Tile) tile).toList();

        // TODO: Add reference to drop factory

        while (chestNum > 0) {
            final Tile t = tiles.get(rnd.nextInt(tiles.size()));
            if (t.put(new Chest(Set.of(), t.getPosition()))) {
                chestNum = chestNum - 1;
            }
        }
    }

    /** {@inheritDoc} */
    @Override
    public void addMonsters(final Room base, final int floor) {
        final Random rnd = new Random();
        int monsterNum = rnd.nextInt(this.maxOccupation(base));
        final List<Tile> tiles = base.getTilesAsEntity().stream()
                .filter(tile -> tile instanceof FloorTileImpl)
                .map(tile -> (Tile) tile).toList();

        while (monsterNum > 0) {
            final Tile t = tiles.get(rnd.nextInt(tiles.size()));
            final Monster monster = this.monsterGen.makeMeleeRat("Rat_" + monsterNum, t.getPosition());
            if (base.addMonster(monster)) {
                monsterNum = monsterNum - 1;
            }
        }
    }

    private int maxOccupation(final Room room) {
        final int freePart = room.getArea() / MIN_ROOM_SIZE;
        return room.getArea() - freePart;
    }

}
