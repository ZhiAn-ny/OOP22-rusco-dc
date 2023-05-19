package it.unibo.ruscodc.model.gamemap;

import it.unibo.ruscodc.utils.Pair;

/**
 * The <code>TileFactory</code> interfaces provides useful methods to create
 * different types of <code>Tile</code>
 */
public interface TileFactory {

    /**
     * Creates a new trap tile at the specified position.
     * The returned trap can be triggered only once.
     * @param x the x-axis coordinate at which the <code>Tile</code> will be placed
     * @param y the y-axis coordinate at which the <code>Tile</code> will be placed
     * @return the new <code>Tile</code>
     */
    Tile createSingleUseFloorTrap(int x, int y);

    /**
     * Creates a new trap tile at the specified position.
     * @param x the x-axis coordinate at which the <code>Tile</code> will be placed
     * @param y the y-axis coordinate at which the <code>Tile</code> will be placed
     * @return the new <code>Tile</code>
     */
    Tile createFloorTrap(int x, int y);

    /**
     * Creates a new random trap tile at the specified position.
     * @param x the x-axis coordinate at which the <code>Tile</code> will be placed
     * @param y the y-axis coordinate at which the <code>Tile</code> will be placed
     * @return the new <code>Tile</code>
     */
    Tile createRandomFloorTrap(int x, int y);

    /**
     * Creates a new <code>Tile</code> representing the floor at the specified position.
     * @param x the x-axis coordinate at which the <code>Tile</code> will be placed
     * @param y the y-axis coordinate at which the <code>Tile</code> will be placed
     * @return the new <code>Tile</code>
     */
    Tile createBaseFloorTile(int x, int y);

    /**
     * Creates a new <code>Tile</code> representing the floor at the specified position.
     * @param x the x-axis coordinate at which the <code>Tile</code> will be placed
     * @param y the y-axis coordinate at which the <code>Tile</code> will be placed
     * @return the new <code>Tile</code>
     */
    Tile createRandomFloorTile(int x, int y);

    /**
     * Creates a new <code>Tile</code> representing a wall at the specified position.
     * @param x the coordinate along the x-axis at which place the <code>Tile</code>
     * @param y the coordinate along the y-axis at which place the <code>Tile</code>
     * @param size the size of the room
     * @return the new <code>Tile</code>
     */
    Tile createBaseWallTile(int x, int y, Pair<Integer, Integer> size);

}
