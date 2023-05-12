package it.unibo.ruscodc.model.gamemap;

import it.unibo.ruscodc.utils.Direction;
import it.unibo.ruscodc.utils.Pair;

/**
 * The <code>TileFactory</code> interfaces provides useful methods to create
 * different types of <code>Tile</code>
 */
public interface TileFactory {

    /**
     * Creates a new <code>Tile</code> representing the floor at the specified position.

     * @return the new <code>Tile</code>
     */
    Tile createBaseFloorTile(int x, int y);

    /**
     * Creates a new <code>Tile</code> representing a wall at the specified position.
     * @param x the coordinate along the x-axis at which place the <code>Tile</code>
     * @param y the coordinate along the y-axis at which place the <code>Tile</code>
     * @param size the size of the room
     * @return the new <code>Tile</code>
     */
    Tile createBaseWallTile(int x, int y, Pair<Integer, Integer> size);

    /**
     * Creates a new <code>Tile</code> representing a door at the specified position.
     * @param x the coordinate along the x-axis at which place the <code>Tile</code>
     * @param y the coordinate along the y-axis at which place the <code>Tile</code>
     * @param side the side of the <code>Room</code> on which the door will be placed
     * @return the new <code>Tile</code>
     */
    Tile createBaseFloorTile(int x, int y, Direction side);

}
