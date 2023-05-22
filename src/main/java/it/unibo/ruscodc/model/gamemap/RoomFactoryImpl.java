package it.unibo.ruscodc.model.gamemap;

import it.unibo.ruscodc.model.actors.monster.Monster;
import it.unibo.ruscodc.model.actors.monster.MonsterGenerator;
import it.unibo.ruscodc.model.actors.monster.MonsterGeneratorImpl;
import it.unibo.ruscodc.utils.Pair;

import java.util.Random;

/**
 * The <coode>RoomFactory</coode> class can be used to generate different
 * types of rooms in the game.
 */
public class RoomFactoryImpl implements RoomFactory {
    private final Random rnd = new Random();
    private static final int MIN_ROOM_SIZE = 3;
    private static final int MAX_ROOM_SIZE = 20;
    private static final int MAX_MONSTERS_NUM = 10;
    private final MonsterGenerator monsterGen = new MonsterGeneratorImpl();

    /** {@inheritDoc} */
    @Override
    public Room randomRoom() {
        return this.getRandomShapeRoom();
    }

    @Override
    public Room randomRoomWithTraps() {
        final Room base = this.randomRoom();
        final TileFactory tf = new TileFactoryImpl();

        base.getTilesAsEntity().forEach(tile -> {
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

// TODO:
//    /** {@inheritDoc} */
//    @Override
//    public Room stairsRoom() {
//        final Room base = this.randomRoom();
//        //base.addStairs(Direction.UNDEFINED);
//        return base;
//    }

}
