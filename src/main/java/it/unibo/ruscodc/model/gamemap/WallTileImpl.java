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
    public WallTileImpl(Pair<Integer, Integer> position, WallType side) {
        super(position, false);
        this.side = side;
    }

    @Override
    public boolean put(final Entity obj) {
        // You cannot put an object in the wall!
        return false;
    }

    @Override
    public String getInfo() {
        Random rnd = new Random();
        return switch (rnd.nextInt(0, 5)) {
            case 0 -> "Someone has written \"You stink!\" on the wall..." +
                    " I guess living here can have that effect on people...";
            case 1 -> "Nothing to see here.";
            case 3 -> "I wonder where the best trash is...";
            default -> "It's just a wall...";
        };
    }

    @Override
    public String getPath() {
        return "file:src/main/resources/it/unibo/ruscodc/map_res/WallTile/"
                + this.side.name();
    }

}
