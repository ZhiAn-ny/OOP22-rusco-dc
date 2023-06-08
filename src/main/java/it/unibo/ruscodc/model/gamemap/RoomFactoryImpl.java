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

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
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
    private static final int FIRST_LEVEL_RANGED = 5;
    private static final int FIRST_LEVEL_MAGE = 15;
    private final MonsterGenerator monsterGen = new MonsterGeneratorImpl();
    private final DropFactory dropFactory = new DropFactoryImpl();

    /** {@inheritDoc} */
    @Override
    public Room randomRoomNoTraps() {
        final Room base = this.getRandomShapeRoom();
        this.addPuddle(base);

        return base;
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

    private void addPuddle(final Room base) {
        final int puddleTileProbability = 10;
        if (this.rnd.nextInt() % puddleTileProbability == 0) {
            final List<Tile> floor = base.getTilesAsEntity().stream()
                    .map(e -> (Tile) e)
                    .filter(t -> t instanceof FloorTileImpl).toList();
            final Pair<Integer, Integer> pos = floor.get(this.rnd.nextInt(floor.size())).getPosition();
            base.replaceTile(pos, new FloorPuddleTileImpl(pos));
        }
    }

    /** {@inheritDoc} */
    @Override
    public void addItems(final Room base, final int floor) {
        int chestNum = this.rnd.nextInt(this.maxItemNum(base, floor));
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
        return base.getArea() - base.getObjectsInRoom().size() - base.getArea() / 2;
    }

    private int maxItemNum(final Room room, final int floor) {
        final double reductionFactor = 0.8;
        int maxNumItems = (int) (room.getArea() / Math.pow(MIN_ROOM_SIZE, 2)) + floor;
        maxNumItems = (int) (maxNumItems * reductionFactor) % this.maxOccupation(room);
        maxNumItems = maxNumItems / MIN_ROOM_SIZE / 2;
        return maxNumItems == 0 ? 2 : maxNumItems + 1;
    }

    private int maxMonstersNum(final Room room, final int floor) {
        final double reductionFactor = 0.6;
        int maxNumMonster = (int) (room.getArea() / Math.pow(MIN_ROOM_SIZE, 2)) + floor;
        maxNumMonster = (int) (maxNumMonster * reductionFactor) % this.maxOccupation(room);
        maxNumMonster = maxNumMonster / MIN_ROOM_SIZE;
        return maxNumMonster == 0 ? 2 : maxNumMonster + 1;
    }

    private Monster getRandomMonster(final Pair<Integer, Integer> pos, final int level) {
        final Set<Method> factoryMethods = Arrays.stream(MonsterGenerator.class.getMethods()).collect(Collectors.toSet());
        final Set<Method> objectMethods = Arrays.stream(Object.class.getMethods()).collect(Collectors.toSet());
        factoryMethods.removeAll(objectMethods);

        if (level < FIRST_LEVEL_RANGED) {
            factoryMethods.removeIf(m -> m.getName().contains("Ranged"));
        }
        if (level < FIRST_LEVEL_MAGE) {
            factoryMethods.removeIf(m -> m.getName().contains("Mage") ||  m.getName().contains("Bomb"));
        }

        try {
            return (Monster) factoryMethods.stream().toList()
                    .get(this.rnd.nextInt(factoryMethods.size()))
                    .invoke(this.monsterGen, pos);
        } catch (IllegalAccessException | InvocationTargetException e) {
            return this.monsterGen.makeMeleeRat(pos);
        }
    }

}
