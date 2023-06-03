package it.unibo.ruscodc.model.gamemap;

import it.unibo.ruscodc.model.actors.Actor;
import it.unibo.ruscodc.model.actors.hero.Hero;
import it.unibo.ruscodc.model.actors.stat.StatImpl;
import it.unibo.ruscodc.model.effect.SingleTargetEffect;
import it.unibo.ruscodc.model.interactable.Interactable;
import it.unibo.ruscodc.utils.Pair;

import java.io.Serial;

/**
 * The <code>FloorPuddleTileImpl</code> class represents a special <code>Tile</code>
 * containing restores 5 hp to the creature passing on it.
 * Works only on <code>Heros</code>.
 */
public class FloorPuddleTileImpl extends FloorTileImpl {
    @Serial
    private static final long serialVersionUID = 1L;
    private final int hpRestored = 5;

    /**
     * Creates a puddle on the floor that restores 5 hp.
     * @param pos the position at which place the <code>Tile</code>
     */
    public FloorPuddleTileImpl(final Pair<Integer, Integer> pos) {
        super(pos, true);
    }

    /** {@inheritDoc} */
    @Override
    public SingleTargetEffect getEffect() {
        return (Actor target) -> {
            if (target instanceof Hero) {
                target.modifyActualStat(StatImpl.StatName.HP, this.hpRestored);
            }
        };
    }

    /** {@inheritDoc} */
    @Override
    public boolean put(final Interactable obj) {
        return false;
    }

    /** {@inheritDoc} */
    @Override
    public String getPath() {
        return super.getPath() + "/puddle";
    }


    /** {@inheritDoc} */
    @Override
    public String toString() {
        return "[o]";
    }
}
