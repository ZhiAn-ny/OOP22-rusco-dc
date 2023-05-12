package it.unibo.ruscodc.model.gamemap;

import it.unibo.ruscodc.utils.Direction;
import it.unibo.ruscodc.utils.Pair;

/**
 * The <code>TileFactoryImpl</code> class implements the <code>TileFactory</code>
 * interface and can be used to create different types of <code>Tile</code>.
 */
public class TileFactoryImpl implements TileFactory {

    @Override
    public Tile createBaseFloorTile(final int x, final int y) {
        return new FloorTileImpl(new Pair<>(x, y), true);
    }

    @Override
    public Tile createBaseWallTile(final int x, final int y, final Pair<Integer, Integer> size) {
        return new WallTileImpl(new Pair<>(x, y), this.getWallType(x, y, size));
    }

    @Override
    public Tile createBaseFloorTile(int x, int y, Direction side) {
        return new DoorTileImpl(new Pair<>(x, y), side);
    }

    /**
     * Calculates the type of the room on which the wall will be placed.
     * @param x the coordinate along the x-axis at which place the <code>Tile</code>
     * @param y the coordinate along the y-axis at which place the <code>Tile</code>
     * @param size the size of the room
     * @return the type of wall to create
     */
    private WallType getWallType(final int x, final int y, final Pair<Integer, Integer> size) {
        if (y == 0) {
            return this.getTopWallType(x, size);
        }
        if (y == size.getY()) {
            return this.getBottomWallType(x, size);
        }

        if (x == 0) {
            return WallType.LEFT;
        }
        if (x == size.getX()) {
            return WallType.RIGHT;
        }

        return WallType.UNDEFINED;
    }

    private WallType getTopWallType(final int x, final Pair<Integer, Integer> size) {
        if (x == 0) {
            return WallType.TOP_LEFT;
        }
        if (x == size.getX()) {
            return WallType.TOP_RIGHT;
        }
        return WallType.TOP;
    }

    private WallType getBottomWallType(final int x, final Pair<Integer, Integer> size) {
        if (x == 0) {
            return WallType.BOTTOM_LEFT;
        }
        if (x == size.getX()) {
            return WallType.BOTTOM_RIGHT;
        }
        return WallType.BOTTOM;
    }

}
