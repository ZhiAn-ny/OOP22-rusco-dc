package it.unibo.ruscodc.model.gamemap;

import it.unibo.ruscodc.utils.Pair;

import java.util.Random;
import java.util.function.Predicate;

/**
 * The <code>TileFactoryImpl</code> class implements the <code>TileFactory</code>
 * interface and can be used to create different types of <code>Tile</code>.
 */
public class TileFactoryImpl implements TileFactory {
    private static final int MAX_TRAP_DMG = 10;
    private static final int TRAP_PROBABILITY = 5;

    /** {@inheritDoc} */
    @Override
    public Tile createSingleUseFloorTrap(final int x, final int y) {
        final FloorTrapTileImpl base = new FloorTrapTileImpl(new Pair<>(x, y));
        base.setPostTriggered(FloorTrapTileImpl::interact);
        base.setDamage(new Random().nextInt(1, MAX_TRAP_DMG + 1));
        return base;
    }

    /** {@inheritDoc} */
    @Override
    public Tile createFloorTrap(final int x, final int y) {
        final FloorTrapTileImpl base = new FloorTrapTileImpl(new Pair<>(x, y));
        base.setDamage(new Random().nextInt(1, MAX_TRAP_DMG + 1));
        return base;
    }

    /** {@inheritDoc} */
    @Override
    public Tile createRandomFloorTrap(final int x, final int y) {
        return (new Random().nextInt() % 2) == 0
                ? this.createSingleUseFloorTrap(x, y)
                : this.createFloorTrap(x, y);
    }

    @Override
    public Tile createPuddle(int x, int y) {
        return new FloorPuddleTileImpl(new Pair<>(x, y));
    }

    /** {@inheritDoc} */
    @Override
    public Tile createBaseFloorTile(final int x, final int y) {
        return new FloorTileImpl(new Pair<>(x, y), true);
    }

    /** {@inheritDoc} */
    @Override
    public Tile createRandomFloorTile(final int x, final int y) {
        return new Random().nextInt(100) < TRAP_PROBABILITY
                ? this.createRandomFloorTrap(x, y)
                : this.createBaseFloorTile(x, y);
    }

    /** {@inheritDoc} */
    @Override
    public Tile createBaseWallTile(final int x, final int y, final Pair<Integer, Integer> size) {
        if (size == null) {
            throw new IllegalArgumentException("Size must represent the room's size!");
        }
        return new WallTileImpl(new Pair<>(x, y), this.getWallType(x, y, size));
    }

    /**
     * Calculates the type of the room on which the wall will be placed.
     * @param x the coordinate along the x-axis at which place the <code>Tile</code>
     * @param y the coordinate along the y-axis at which place the <code>Tile</code>
     * @param size the size of the room
     * @return the type of wall to create
     */
    private WallType getWallType(final int x, final int y, final Pair<Integer, Integer> size) {
        final Predicate<Integer> xInRoom = xCoord -> xCoord >= 0 && xCoord <= size.getX() + 1;
        if (y == 0 && xInRoom.test(x)) {
            return this.getTopWallType(x, size);
        }
        if (y == size.getY() + 1 && xInRoom.test(x)) {
            return this.getBottomWallType(x, size);
        }

        final Predicate<Integer> yInRoom = yCoord -> yCoord >= 0 && yCoord <= size.getY() + 1;
        if (x == 0 && yInRoom.test(y)) {
            return WallType.LEFT;
        }
        if (x == size.getX() + 1 && yInRoom.test(y)) {
            return WallType.RIGHT;
        }

        return WallType.UNDEFINED;
    }

    private WallType getTopWallType(final int x, final Pair<Integer, Integer> size) {
        if (x == 0) {
            return WallType.TOP_LEFT;
        }
        if (x == size.getX() + 1) {
            return WallType.TOP_RIGHT;
        }
        return WallType.TOP;
    }

    private WallType getBottomWallType(final int x, final Pair<Integer, Integer> size) {
        if (x == 0) {
            return WallType.BOTTOM_LEFT;
        }
        if (x == size.getX() + 1) {
            return WallType.BOTTOM_RIGHT;
        }
        return WallType.BOTTOM;
    }

}
