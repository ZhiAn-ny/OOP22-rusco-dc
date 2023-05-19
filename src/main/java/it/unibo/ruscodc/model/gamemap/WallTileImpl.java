package it.unibo.ruscodc.model.gamemap;

import it.unibo.ruscodc.model.Entity;
import it.unibo.ruscodc.model.interactable.Interactable;
import it.unibo.ruscodc.utils.Pair;


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
     * @param side the side of the <code>Room</code> on which the wall will be placed
     */
    public WallTileImpl(final Pair<Integer, Integer> position, final WallType side) {
        super(position, false);
        this.side = side;
    }

    /** {@inheritDoc} */
    @Override
    public boolean put(final Interactable obj) {
        // You cannot put an object in the wall!
        return false;
    }

    /** {@inheritDoc} */
    @Override
    public String getID() {
        return "wall";
    }

    /** {@inheritDoc} */
    @Override
    public String getPath() {
        return "file:src/main/resources/it/unibo/ruscodc/map_res/WallTile/"
                + this.side.name();
    }

}
