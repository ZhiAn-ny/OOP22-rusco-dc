package it.unibo.ruscodc.model.gamemap;

import it.unibo.ruscodc.model.actors.Actor;
import it.unibo.ruscodc.model.actors.stat.StatImpl;
import it.unibo.ruscodc.model.effect.SingleTargetEffect;
import it.unibo.ruscodc.model.gamecommand.GameCommand;
import it.unibo.ruscodc.model.gamecommand.quickcommand.DoNothing;
import it.unibo.ruscodc.model.interactable.Interactable;
import it.unibo.ruscodc.utils.Pair;

import java.util.function.Consumer;
import java.util.function.Function;

/**
 * The <code>FloorTrapTileImpl</code> class represents a special <code>Tile</code>
 * containing a trap placed on the floor.
 */
public class FloorTrapTileImpl extends FloorTileImpl implements Interactable {
    private int damage = 5;
    private boolean isReady = true;
    private Consumer<FloorTrapTileImpl> postTriggered = (self) -> { };

    /**
     * Constructs a floor trap with 5 as default damage produced.
     * @param pos the position at which place the <code>Tile</code>
     */
    public FloorTrapTileImpl(final Pair<Integer, Integer> pos) {
        super(pos, true);
    }

    /**
     *
     * @param pos the position at which place the <code>Tile</code>
     * @param damage the damage inflicted by the trap
     */
    public FloorTrapTileImpl(final Pair<Integer, Integer> pos, final int damage) {
        super(pos, true);
        this.damage = damage;
    }

    @Override
    public boolean isTrap() {
        return true;
    }

    /**
     * Set what happens after the trap has been triggered.
     * @param post
     */
    public void setPostTriggered(Consumer<FloorTrapTileImpl> post) {
        this.postTriggered = post;
    }

    @Override
    public SingleTargetEffect getEffect() {
        return (Actor target) -> {
            if (this.isReady) {
                target.modifyStat(StatImpl.StatName.HP, -this.damage);
                this.postTriggered.accept(this);
            }
        };
    }

    @Override
    public String getName() {
        return "It's a trap!";
    }

    @Override
    public GameCommand interact() {
        this.isReady = false;
        return new DoNothing();
    }
}
