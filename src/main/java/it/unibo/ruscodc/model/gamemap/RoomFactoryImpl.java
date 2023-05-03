package it.unibo.ruscodc.model.gamemap;

import java.util.Random;

public class RoomFactoryImpl implements RoomFactory {
    private final Random rnd = new Random();
    private final int maxRoomSize = 20;

    @Override
    public Room randomSquareRoom() {
        int side = this.rnd.nextInt(this.maxRoomSize);
        return new RectangleRoomImpl(side, side);
    }

    @Override
    public Room randomRectangleRoom() {
        return new RectangleRoomImpl(
                this.rnd.nextInt(this.maxRoomSize),
                this.rnd.nextInt(this.maxRoomSize)
        );
    }

}
