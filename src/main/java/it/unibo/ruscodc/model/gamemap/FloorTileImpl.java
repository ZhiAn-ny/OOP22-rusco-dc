package it.unibo.ruscodc.model.gamemap;

import it.unibo.ruscodc.utils.Pair;


/**
 * The <code>FloorTileImpl</code> class represents the basic implementation of
 * the concept of floor.
 */
public class FloorTileImpl extends AbstractTile {

    /**
     * Creates a <code>FloorTile</code> at the specified position and sets its accessibility.
     *
     * @param position      the position of the tile in the room
     * @param accessibility whether the tile can be accessed or not by the player
     */
    public FloorTileImpl(final Pair<Integer, Integer> position, final boolean accessibility) {
        super(position, accessibility);
    }


    /** {@inheritDoc} */
    @Override
    public String getPath() {
        return "file:src/main/resources/it/unibo/ruscodc/map_res/FloorTile";
    }

}
