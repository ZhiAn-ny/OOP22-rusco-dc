package it.unibo.ruscodc.model.gamemap;

import it.unibo.ruscodc.model.Entity;
import it.unibo.ruscodc.utils.Direction;
import it.unibo.ruscodc.utils.Pair;

import java.util.Random;

public class DoorTileImpl extends AbstractTile {
    private final Direction side;

    /**
     * Creates a <code>Tile</code> at the specified position and sets its accessibility.
     *
     * @param position      the position of the tile in the room
     */
    public DoorTileImpl(final Pair<Integer, Integer> position, final Direction side) {
        super(position, true);
        this.side = side;
    }

    @Override
    public boolean put(final Entity obj) {
        // You cannot put an object in the door!
        return false;
    }

    @Override
    public String getInfo() {
        return "The door is not locked, we can go to the next room!";
    }

    @Override
    public String getPath() {
        return "file:src/main/resources/it/unibo/ruscodc/map_res/DoorTile/"
                + this.side.name();
    }
}
