package it.unibo.ruscodc.model.gamemap;


/**
 * The <code>Floor</code> interface represent the base concept of level in the game.
 * Each <code>Floor</code> is composed by multiple instance of <code>Room</code> interconnected between each other.
 */
public interface Floor {

    /**
     * @return the room containing the PCs.
     */
    Room getCurrentRoom();

    /**
     * @return the number of rooms explored on this floor.
     */
    int getNRoomExplored();

}
