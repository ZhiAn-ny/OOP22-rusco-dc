package it.unibo.ruscodc.model.gamemap;

import it.unibo.ruscodc.model.Entity;
import it.unibo.ruscodc.model.actors.Actor;
import it.unibo.ruscodc.model.effect.SingleTargetEffect;
import it.unibo.ruscodc.model.interactable.Interactable;
import it.unibo.ruscodc.utils.Pair;

import java.util.Optional;

/**
 * The <code>BaseFloorTileImpl</code> creates a base implementation of a tile.
 */
public abstract class AbstractTile implements Tile, Entity {
    private final Pair<Integer, Integer> position;
    private final boolean isAccessible;
    private Interactable content;
    private static final int MAP_RENDERING_LEVEL = 1;

    /**
     * Creates a <code>Tile</code> at the specified position and sets its accessibility.
     * @param position the position of the tile in the room
     * @param accessibility whether the tile can be accessed or not by the player
     */
    public AbstractTile(final Pair<Integer, Integer> position, final boolean accessibility) {
        if (position == null) {
            throw new IllegalArgumentException("Position cannot be null!");
        }
        this.position = position;
        this.isAccessible = accessibility;
    }

    /** {@inheritDoc} */
    @Override
    public Pair<Integer, Integer> getPosition() {
        return this.position;
    }

    /** {@inheritDoc} */
    @Override
    public boolean isAccessible() {
        if (this.get().isPresent()) {
            return this.isAccessible && this.get().get().isTransitable();
        }
        return this.isAccessible;
    }

    /** {@inheritDoc} */
    @Override
    public boolean isTrap() {
        return false;
    }

    /** {@inheritDoc} */
    @Override
    public boolean put(final Interactable obj) {
        if (this.content != null || obj instanceof Tile) {
            return false;
        }
        this.content = obj;
        return this.content != null;
    }

    /** {@inheritDoc} */
    @Override
    public Optional<Interactable> get() {
        return Optional.ofNullable(this.content);
    }

    /** {@inheritDoc} */
    @Override
    public Optional<Interactable> empty() {
        final Optional<Interactable> content = this.get();
        this.content = null;
        return content;
    }

    /** {@inheritDoc} */
    @Override
    public SingleTargetEffect getEffect() {
        return (Actor a) -> { };
    }

    /** {@inheritDoc} */
    @Override
    public Pair<Integer, Integer> getPos() {
        return this.position;
    }

    /** {@inheritDoc} */
    @Override
    public int getID() {
        return MAP_RENDERING_LEVEL;
    }

}
