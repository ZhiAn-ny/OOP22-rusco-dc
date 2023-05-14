package it.unibo.ruscodc.model.gamemap;

import it.unibo.ruscodc.model.Entity;
import it.unibo.ruscodc.utils.Pair;

import java.util.Random;

/**
 * The <code>WallTileImpl</code> class represents the basic implementation of
 * the concept of wall.
 */
public class WallTileImpl extends AbstractTile {
    private final WallType side;

    /**
     * Creates a <code>Tile</code> at the specified position and sets its accessibility.
     *
     * @param position      the position of the tile in the room
     */
    public WallTileImpl(final Pair<Integer, Integer> position, final WallType side) {
        super(position, false);
        this.side = side;
    }

    @Override
    public boolean put(final Entity obj) {
        // You cannot put an object in the wall!
        return false;
    }

    @Override
    public String getID() {
        return "wall";
    }

    @Override
    public String getPath() {
        return "file:src/main/resources/it/unibo/ruscodc/map_res/WallTile/"
                + this.side.name();
    }

}
