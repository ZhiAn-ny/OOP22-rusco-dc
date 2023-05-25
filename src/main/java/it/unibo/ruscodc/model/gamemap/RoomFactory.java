package it.unibo.ruscodc.model.gamemap;

/**
 * The <code>RoomFactory</code> interface can be used to generate different
 * types of rooms in the game.
 */
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
     * Creates a randomly generated <code>Room</code> containing traps.
     * @return a randomly generated room
     */
    Room randomRoomWithTraps();

    /**
     * Creates a randomly generated <code>Room</code> in the shape of a rectangle.
     * @param width the width of the <code>Room</code>
     * @param height the height of the <code>Room</code>
     * @return a randomly generated rectangle room
     */
    Room rectangleRoom(int width, int height);

    /**
     * Creates a randomly generated <code>Room</code> containing the stairs to
     * the next level.
     * @return a randomly generated room to the next level
     */
    Room stairsRoom(); // TODO:

    // ROOM UTILITIES

    /**
     * Adds items to the specified room.
     * @param room the room on which the operation will be executed
     * @param floor the floor reached
     */
    void addItems(final Room room, final int floor);

    /**
     * Adds items to the specified room.
     * @param room the room on which the operation will be executed
     * @param floor the floor reached
     */
    void addMonsters(final Room room, final int floor);
}
