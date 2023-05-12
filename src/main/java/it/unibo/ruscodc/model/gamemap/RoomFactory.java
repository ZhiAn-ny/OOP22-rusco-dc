package it.unibo.ruscodc.model.gamemap;

public interface RoomFactory {

    /**
     * Creates a randomly generated <code>Room</code> of the specified size.
     * @param size the side of the square
     * @return a square room
     */
    Room squareRoom(int size);

    /**
     * Creates a randomly generated <code>Room</code>.
     * @return
     */
    Room randomRoom();

    /**
     * Creates a randomly generated <code>Room</code> in the shape of a rectangle.
     * @return a randomly generated rectangle room
     */
    Room rectangleRoom(int width, int height);
}
