package it.unibo.ruscodc.model.gamemap;

import it.unibo.ruscodc.model.actors.Actor;
import it.unibo.ruscodc.model.actors.stat.StatImpl;
import it.unibo.ruscodc.model.effect.SingleTargetEffect;
import it.unibo.ruscodc.model.gamecommand.GameCommand;
import it.unibo.ruscodc.model.gamecommand.quickcommand.DoNothing;
import it.unibo.ruscodc.model.interactable.Interactable;
import it.unibo.ruscodc.utils.Pair;

import java.util.Random;
import java.util.function.Consumer;

/**
 * The <code>FloorTrapTileImpl</code> class represents a special <code>Tile</code>
 * containing a trap placed on the floor.
 * By default, the trap can be triggered multiple times, producing 5 points
 * of damage each time and can be disabled with a simple interaction.
 */
public class FloorTrapTileImpl extends FloorTileImpl implements Interactable {
    private int damage = 5;
    private boolean isReady = true;
    private int disableSuccessRate = 100;
    private Consumer<FloorTrapTileImpl> postTriggered = (self) -> { };

    /**
     * Constructs a floor trap with 5 as default damage produced.
     * @param pos the position at which place the <code>Tile</code>
     */
    public FloorTrapTileImpl(final Pair<Integer, Integer> pos) {
        super(pos, true);
    }

    /**
     * Sets the trap's damage. The damage is set to 5 by default.
     * @param dmg the damage that the trap will inflict to the player.
     */
    public void setDamage(final int dmg) {
        this.damage = dmg;
    }

    /**
     * Sets the success rate for the interaction with the trap.
     * The interaction can be used to disable the trap.
     * @param successRate the new success rate
     */
    public void setDisableSuccessRate(final int successRate) {
        this.disableSuccessRate = successRate;
    }

    /** {@inheritDoc} */
    @Override
    public boolean isTrap() {
        return true;
    }

    /**
     * Set what happens after the trap has been triggered.
     * @param post a <code>Consumer</code> taking the current <code>FloorTrapTileImpl</code> as object consumed
     */
    public void setPostTriggered(final Consumer<FloorTrapTileImpl> post) {
        this.postTriggered = post;
    }

    /** {@inheritDoc} */
    @Override
    public SingleTargetEffect getEffect() {
        return (Actor target) -> {
            if (this.isReady) {
                target.modifyStat(StatImpl.StatName.HP, -this.damage);
                this.postTriggered.accept(this);
            }
        };
    }

    /** {@inheritDoc} */
    @Override
    public String getName() {
        return "It's a trap!";
    }

    /**
     * Disables the trap.
     * @return a <code>DoNothing</code> command.
     */
    @Override
    public GameCommand interact() {
        if (new Random().nextInt(100) < this.disableSuccessRate) {
            this.isReady = false;
        }
        return new DoNothing();
    }
}