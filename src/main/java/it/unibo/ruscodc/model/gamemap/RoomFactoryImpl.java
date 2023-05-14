package it.unibo.ruscodc.model.gamemap;

import it.unibo.ruscodc.utils.Direction;

import java.util.Random;

public class RoomFactoryImpl implements RoomFactory {
    private final Random rnd = new Random();
    private final int maxRoomSize = 20;

    @Override
    public Room randomRoom() {
        if (this.rnd.nextInt() % 2 == 0) {
            return this.squareRoom(this.rnd.nextInt(this.maxRoomSize));
        };
        return this.rectangleRoom(
                this.rnd.nextInt(this.maxRoomSize),
                this.rnd.nextInt(this.maxRoomSize)
        );
    }

    @Override
    public Room squareRoom(int size) {
        return new RectangleRoomImpl(size, size);
    }

    @Override
    public Room rectangleRoom(final int width, final int height) {
        return new RectangleRoomImpl(width, height);
    }

    @Override
    public Room stairsRoom() {
        Room base = this.randomRoom();
        //base.addStairs(Direction.UNDEFINED);
        return base;
    }

}
