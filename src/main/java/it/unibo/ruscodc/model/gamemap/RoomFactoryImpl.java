package it.unibo.ruscodc.model.gamemap;

import java.util.Random;

/**
 * The <coode>RoomFactory</coode> class can be used to generate different
 * types of rooms in the game.
 */
public class RoomFactoryImpl implements RoomFactory {
    private final Random rnd = new Random();
    private static final int MAX_ROOM_SIZE = 20;

    /** {@inheritDoc} */
    @Override
    public Room randomRoom() {
        return (this.rnd.nextInt() % 2 == 0) ?
                this.squareRoom(this.rnd.nextInt(MAX_ROOM_SIZE)) :
                this.rectangleRoom(this.rnd.nextInt(MAX_ROOM_SIZE), this.rnd.nextInt(MAX_ROOM_SIZE));
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
