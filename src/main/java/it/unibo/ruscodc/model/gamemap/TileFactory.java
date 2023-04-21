package it.unibo.ruscodc.model.gamemap;

import it.unibo.ruscodc.utils.Pair;

public interface TileFactory {

    /**
     * Creates a new <code>Tile</code> representing the floor at the specified position.
     * @param pos the position at which place the <code>Tile</code>
     * @return the new <code>Tile</code>
     */
    Tile createBaseFloorTile(Pair<Integer, Integer> pos);

    /**
     * Creates a new <code>Tile</code> representing a wall at the specified position.
     * @param pos the position at which place the <code>Tile</code>
     * @return the new <code>Tile</code>
     */
    Tile createBaseWallTile(Pair<Integer, Integer> pos);

}
