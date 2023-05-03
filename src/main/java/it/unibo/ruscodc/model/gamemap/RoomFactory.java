package it.unibo.ruscodc.model.gamemap;

public interface RoomFactory {

    /**
     * Creates a randomly generated <code>Room</code> in the shape of a square.
     * @return a randomly generated square room
     */
    Room randomSquareRoom();

    /**
     * Creates a randomly generated <code>Room</code> in the shape of a rectangle.
     * @return a randomly generated rectangle room
     */
    Room randomRectangleRoom();
}
