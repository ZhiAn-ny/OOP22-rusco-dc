package it.unibo.ruscodc.model.gamecommand.iacommand;

import java.util.Set;

import it.unibo.ruscodc.model.Entity;
import it.unibo.ruscodc.model.gamecommand.ComplexActionAbs;
import it.unibo.ruscodc.utils.GameControl;

/**
 * All command that
 * <ul>
 * <li> are not logically ready 
 * <li> must be managed by game
 * </ul>
 * must extend this class, that define witch method cannot be callable for this type of command.
 */
public abstract class NoPlayerCommand extends ComplexActionAbs {

    /**
     * 
     */
    @Override
    public boolean modify(final GameControl input) {
        throw new UnsupportedOperationException(this.getGlobalErrMess());
    }

    /**
     * 
     */
    @Override
    public Set<Entity> getEntities() {
        throw new UnsupportedOperationException(this.getGlobalErrMess());
    }

}
