package it.unibo.ruscodc.model.gamemap;

import it.unibo.ruscodc.utils.Pair;

import java.util.Random;

public class FloorTileImpl extends AbstractTile {

    /**
     * Creates a <code>FloorTile</code> at the specified position and sets its accessibility.
     *
     * @param position      the position of the tile in the room
     * @param accessibility whether the tile can be accessed or not by the player
     */
    public FloorTileImpl(Pair<Integer, Integer> position, boolean accessibility) {
        super(position, accessibility);
    }

    @Override
    public String getInfo() {
        Random rnd = new Random();
        return switch (rnd.nextInt(0, 5)) {
            case 0 -> "This floor is so dirty!";
            case 1 -> "Nothing to see here.";
            case 2 -> "Dust bunnies where are you?";
            case 3 -> "I wonder where the best trash is...";
            case 4 -> "No banana peels here! The path is clear!";
            default -> "Just some tiles...";
        };
    }

    @Override
    public String getPath() {
        return "file:src/main/resources/it/unibo/ruscodc/map_res/FloorTile.jpg";
    }

}
