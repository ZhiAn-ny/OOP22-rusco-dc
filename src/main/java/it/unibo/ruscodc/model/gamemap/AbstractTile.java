package it.unibo.ruscodc.model.gamemap;

import it.unibo.ruscodc.model.Entity;
import it.unibo.ruscodc.utils.Pair;

import java.util.Optional;

/**
 * The <code>BaseFloorTileImpl</code> creates a base implementation of a tile.
 */
public abstract class AbstractTile implements Tile, Entity {
    private final Pair<Integer, Integer> position;
    private final boolean isAccessible;
    private Entity content;

    /**
     * Creates a <code>Tile</code> at the specified position and sets its accessibility.
     * @param position the position of the tile in the room
     * @param accessibility whether the tile can be accessed or not by the player
     */
    public AbstractTile(final Pair<Integer, Integer> position, final boolean accessibility) {
        this.position = position;
        this.isAccessible = accessibility;
    }

    @Override
    public Pair<Integer, Integer> getPosition() {
        return this.position;
    }

    @Override
    public boolean isAccessible() {
        return this.isAccessible;
    }

    @Override
    public boolean put(final Entity obj) {
        if (this.content != null) {
            return false;
        }
        this.content = obj;
        return true;
    }

    @Override
    public Optional<Entity> get() {
        return Optional.ofNullable(this.content);
    }

    @Override
    public Optional<Entity> empty() {
        final Optional<Entity> content = this.get();
        this.content = null;
        return content;
    }

    @Override
    public Pair<Integer, Integer> getPos() {
        return this.position;
    }

}
