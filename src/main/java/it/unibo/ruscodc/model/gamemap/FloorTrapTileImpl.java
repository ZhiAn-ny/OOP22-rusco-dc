package it.unibo.ruscodc.model.gamemap;

import it.unibo.ruscodc.model.actors.Actor;
import it.unibo.ruscodc.model.actors.stat.StatImpl;
import it.unibo.ruscodc.model.effect.SingleTargetEffect;
import it.unibo.ruscodc.model.gamecommand.GameCommand;
import it.unibo.ruscodc.model.gamecommand.quickcommand.DoNothing;
import it.unibo.ruscodc.model.interactable.Interactable;
import it.unibo.ruscodc.utils.Pair;

import java.util.Optional;
import java.util.Random;
import java.util.function.Consumer;

/**
 * The <code>FloorTrapTileImpl</code> class represents a special <code>Tile</code>
 * containing a trap placed on the floor.
 * By default, the trap can be triggered multiple times, producing 5 points
 * of damage each time and can be disabled with a simple interaction.
 */
public class FloorTrapTileImpl extends FloorTileImpl implements Interactable {
    private static final int INTERACTABLE_OBJECTS_RENDERING_LEVEL = 2;
    private static final int DEFAULT_DAMAGE = 5;
    private int damage;
    private boolean isReady = true;
    private final Random rnd = new Random();
    private int disableSuccessRate = 100;
    private transient Consumer<FloorTrapTileImpl> postTriggered;

    /**
     * Constructs a floor trap with 5 as default damage produced.
     * @param pos the position at which place the <code>Tile</code>
     */
    public FloorTrapTileImpl(final Pair<Integer, Integer> pos) {
        super(pos, true);
        this.damage = DEFAULT_DAMAGE;
        this.postTriggered = (self) -> { };
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
     * The interaction can be used to disable the trap. The success rate is set
     * to 100 by default.
     * <br>
     * If the specified value is less than 1 or greater than 100 the action will be ignored.
     * @param successRate the new success rate, must be integer between 1 and 100
     */
    public void setDisableSuccessRate(final int successRate) {
        if (successRate < 1 || successRate > 100) {
            return;
        }
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
                target.modifyActualStat(StatImpl.StatName.HP, -this.damage);
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
        if (this.rnd.nextInt(100) < this.disableSuccessRate) {
            this.isReady = false;
        }
        return new DoNothing();
    }

    /** {@inheritDoc} */
    @Override
    public boolean isTransitable() {
        return true;
    }

    /** {@inheritDoc} */
    @Override
    public Optional<Interactable> get() {
        return Optional.of(this);
    }

    /** {@inheritDoc} */
    @Override
    public Optional<Interactable> empty() {
        return this.get();
    }

    /** {@inheritDoc} */
    @Override
    public boolean put(final Interactable obj) {
        return false;
    }

    /** {@inheritDoc} */
    @Override
    public String getPath() {
        return super.getPath() + "/trap";
    }

    /** {@inheritDoc} */
    @Override
    public int getID() {
        return INTERACTABLE_OBJECTS_RENDERING_LEVEL;
    }
}
