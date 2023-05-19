package it.unibo.ruscodc.model.gamemap;

import it.unibo.ruscodc.model.Entity;
import it.unibo.ruscodc.model.effect.SingleTargetEffect;
import it.unibo.ruscodc.model.interactable.Interactable;
import it.unibo.ruscodc.utils.Pair;

import java.util.Optional;

/**
 * The <code>Tile</code> interface represents a single unit of the <code>Room</code>.
 */
public interface Tile {

    /**
     * Returns a <code>Pair</code> describing the position of the tile.
     * @return a <code>Pair</code> describing the tile's position.
     */
    Pair<Integer, Integer> getPosition();

    /**
     * Return if whether the tile can be accessed by the player.
     * @return <code>True</code> if the tile is accessible to the player.
     */
    boolean isAccessible();

    /**
     * Returns whether the <code>Tile</code> is a trap or not.
     * @return <code>True</code> if the <code>Tile</code> is a trap, <code>False</code> otherwise
     */
    boolean isTrap();

    /**
     * Returns the effect produced by stepping on the <code>Tile</code>.
     * @return a <code>SingleTargetEffect</code> representing the effect
     */
    SingleTargetEffect getEffect();

    /**
     * Places an <code>Entity</code> on the tile.
     * @param obj the <code>Entity</code> to place
     * @return <code>True</code> if the <code>Entity</code> has been placed correctly,
     * <code>False</code> otherwise.
     */
    boolean put(Interactable obj);

    /**
     * Returns an <code>Optional</code> containing the <code>Entity</code>
     * placed on the tile, otherwise returns an empty Optional.
     *
     * @return an <code>Optional</code> with a present value if the tile has
     * something on it, otherwise an empty <code>Optional</code>
     */
    Optional<Interactable> get();

    /**
     * Removes the <code>Entity</code> that was placed on the tile.
     *
     * @return an <code>Optional</code> with a present value if the tile has
     * something on it, otherwise an empty <code>Optional</code>
     */
    Optional<Interactable> empty();

}
