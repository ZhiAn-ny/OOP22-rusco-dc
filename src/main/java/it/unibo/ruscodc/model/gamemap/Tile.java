package it.unibo.ruscodc.model.gamemap;

import it.unibo.ruscodc.model.Entity;

import java.util.Optional;

public interface Tile {

    /**
     * Return if whether the tile can be accessed by the player.
     * @return <code>True</code> if the tile
     */
    boolean isAccessible();

    boolean put(Entity obj);

    Optional<Entity> get();

}
