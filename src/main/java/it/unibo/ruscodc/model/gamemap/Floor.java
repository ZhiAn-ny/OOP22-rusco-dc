package it.unibo.ruscodc.model.gamemap;


import it.unibo.ruscodc.model.actors.monster.Monster;
import it.unibo.ruscodc.utils.Direction;
import it.unibo.ruscodc.utils.Pair;

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

    /**
     * Go to the room adjacent to the current one on the specified direction.
     * @param dir the direction of the two rooms are connected
     */
    void goToRoom(Direction dir);

    /**
     * Eliminates a monster and removes it from the map.
     * @param monster the monster to eliminate
     */
    void eliminateMonster(Monster monster);

    /**
     * Clear the area around a specified position.
     * @param pos the center of the area
     * @param radius the radius of the area
     */
    void clearArea(Pair<Integer, Integer> pos, int radius);
}
