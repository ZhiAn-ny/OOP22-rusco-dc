package it.unibo.ruscodc.model.gamemap;

import it.unibo.ruscodc.model.actors.monster.Monster;
import it.unibo.ruscodc.model.actors.monster.MonsterGenerator;
import it.unibo.ruscodc.model.actors.monster.MonsterGeneratorImpl;
import it.unibo.ruscodc.model.actors.monster.drop.DropFactory;
import it.unibo.ruscodc.model.actors.monster.drop.DropFactoryImpl;
import it.unibo.ruscodc.model.actors.monster.drop.DropManager;
import it.unibo.ruscodc.model.interactable.Chest;
import it.unibo.ruscodc.model.interactable.Interactable;
import it.unibo.ruscodc.utils.Direction;
import it.unibo.ruscodc.utils.Pair;

import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

/**
 * The <code>RoomFactory</code> class can be used to generate different
 * types of rooms in the game.
 */
public class RoomFactoryImpl implements RoomFactory {
    private final Random rnd = new Random();
    private static final int MIN_ROOM_SIZE = 3;
    private static final int MAX_ROOM_SIZE = 15;
    private static final int MAX_DOORS_NUM = 4;
    private static final int FIRST_LEVEL_RANGED = 30;
    private static final int FIRST_LEVEL_MAGE = 50;
    private final MonsterGenerator monsterGen = new MonsterGeneratorImpl();
    private final DropFactory dropFactory = new DropFactoryImpl();

    /** {@inheritDoc} */
    @Override
    public Room randomRoomNoTraps() {
        final Room base = this.getRandomShapeRoom();
        this.addDoors(base);

        return this.getRandomShapeRoom();
    }

    /** {@inheritDoc} */
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

    /** {@inheritDoc} */
    @Override
    public Room randomRoom() {
        final int roomWithTrapProbability = 3;
        if (this.rnd.nextInt() % roomWithTrapProbability == 0) {
            return this.randomRoomWithTraps();
        }
        return this.randomRoomNoTraps();
    }

    private Room getRandomShapeRoom() {
        return (this.rnd.nextInt() % 2 == 0)
                ? this.emptySquareRoom(this.rnd.nextInt(MIN_ROOM_SIZE, MAX_ROOM_SIZE))
                : this.emptyRectangleRoom(this.rnd.nextInt(MIN_ROOM_SIZE, MAX_ROOM_SIZE),
                                          this.rnd.nextInt(MIN_ROOM_SIZE, MAX_ROOM_SIZE));
    }

    /** {@inheritDoc} */
    @Override
    public Room emptySquareRoom(final int size) {
        return new RectangleRoomImpl(size, size);
    }

    /** {@inheritDoc} */
    @Override
    public Room emptyRectangleRoom(final int width, final int height) {
        return new RectangleRoomImpl(width, height);
    }

    /** {@inheritDoc} */
    @Override
    public Room stairsRoom() {
        final Room base = this.randomRoomNoTraps();

        Direction dir = Direction.values()[rnd.nextInt(Direction.values().length)];
        while (!base.addStairs(dir)) {
            // Could not add stairs, retry on different direction
            dir = Direction.values()[rnd.nextInt(Direction.values().length)];
        }
        return base;
    }

    /** {@inheritDoc} */
    @Override
    public void addDoors(final Room room) {
        int i = this.rnd.nextInt(0, MAX_DOORS_NUM);
        while (i > 0) {
            final Direction dir = Direction.values()[this.rnd.nextInt(Direction.values().length)];
            if (room.addDoor(dir)) {
                i = i - 1;
            }
        }
    }

    /** {@inheritDoc} */
    @Override
    public void addItems(final Room base, final int floor) {
        int chestNum = this.rnd.nextInt(this.maxItemNum(base, floor));
        chestNum = chestNum - 1;
        final DropManager dm = this.dropFactory.createDropForRoom(base.getSize(), floor);
        final List<Tile> tiles = base.getTilesAsEntity().stream()
                .filter(tile -> tile instanceof FloorTileImpl)
                .map(tile -> (Tile) tile).toList();

        while (chestNum > 0) {
            final Tile t = tiles.get(rnd.nextInt(tiles.size()));
            final Interactable chest = new Chest(new HashSet<>(dm.generateRandomDrop()), t.getPosition());

            if (t.put(chest)) {
                chestNum = chestNum - 1;
            }
        }
    }

    /** {@inheritDoc} */
    @Override
    public void addMonsters(final Room base, final int floor) {
        int monsterNum = this.rnd.nextInt(this.maxMonstersNum(base, floor));
        monsterNum = monsterNum % (base.getArea() - base.getObjectsInRoom().size());
        final List<Tile> tiles = base.getTilesAsEntity().stream()
                .filter(tile -> tile instanceof FloorTileImpl)
                .map(tile -> (Tile) tile).toList();

        while (monsterNum > 0) {
            final Tile t = tiles.get(this.rnd.nextInt(tiles.size()));
            final Monster monster = this.getRandomMonster(t.getPosition(), floor);
            if (base.addMonster(monster)) {
                monsterNum = monsterNum - 1;
            }
        }
    }

    private int maxOccupation(final Room base) {
        return base.getArea() - base.getObjectsInRoom().size() - base.getArea()/2;
    }

    private int maxItemNum(final Room room, final int floor) {
        int maxNumItems = (int)(room.getArea() / Math.pow(MIN_ROOM_SIZE, 2)) + floor;
        maxNumItems = maxNumItems % this.maxOccupation(room);
        return maxNumItems == 0 ? 1 : maxNumItems;
    }

    private int maxMonstersNum(final Room room, final int floor) {
        int maxNumItems = (int)(room.getArea() / Math.pow(MIN_ROOM_SIZE, 2)) + floor;
        maxNumItems = (int)(maxNumItems * 0.6) % this.maxOccupation(room);
        return maxNumItems == 0 ? 1 : maxNumItems;
    }

    private Monster getRandomMonster(final Pair<Integer, Integer> pos, final int level) {
        final Set<Method> factoryMethods = Arrays.stream(MonsterGenerator.class.getMethods()).collect(Collectors.toSet());
        final Set<Method> objectMethods = Arrays.stream(Object.class.getMethods()).collect(Collectors.toSet());
        factoryMethods.removeAll(objectMethods);
        factoryMethods.removeIf(m -> m.getName().contains("Bomb")); // TODO: Remove after fix

        if (level < FIRST_LEVEL_RANGED) {
            factoryMethods.removeIf(m -> m.getName().contains("Ranged"));
        }
        if (level < FIRST_LEVEL_MAGE) {
            factoryMethods.removeIf(m -> m.getName().contains("Mage"));
        }

        try {
            return (Monster) factoryMethods.stream().toList()
                    .get(this.rnd.nextInt(factoryMethods.size()))
                    .invoke(this.monsterGen, pos);
        } catch (Exception e) {
            // Something went wrong in the random generation... returning a rat!
            return this.monsterGen.makeMeleeRat(pos);
        }
    }

}
