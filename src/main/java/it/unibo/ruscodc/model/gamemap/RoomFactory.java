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
     * @return a randomly generated room
     */
    Room randomRoom();

    /**
     * Creates a randomly generated <code>Room</code> in the shape of a rectangle.
     * @return a randomly generated rectangle room
     */
    Room rectangleRoom(int width, int height);

    /**
     * Creates a randomly generated <code>Room</code> containing the stairs to
     * the next level.
     * @return a randomly generated room to the next level
     */
    Room stairsRoom();
}
