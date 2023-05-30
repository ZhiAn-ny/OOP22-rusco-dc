package it.unibo.ruscodc.model.gamecommand.playercommand;

import it.unibo.ruscodc.model.actors.Actor;
import it.unibo.ruscodc.model.gamecommand.ComplexActionAbs;
import it.unibo.ruscodc.utils.Pair;

/**
 * All command that
 * <ul>
 * <li> are not logically ready 
 * <li> must manage input player
 * </ul>
 * must extend this class, that define witch method cannot be callable for this type of command.
 */
public abstract class NoIACommand extends ComplexActionAbs {

    private static final String CURSOR_PATH = "file:src/main/resources/it/unibo/ruscodc/range_res/cursor";
    private static final int CURSOR_DEPTH = 5;

    /**
     * 
     */
    @Override
    public Pair<Integer, Integer> getActorPos() {
        throw new UnsupportedOperationException(this.getGlobalErrMess());
    }

    /**
     * 
     */
    @Override
    public void setCursorPos(final Pair<Integer, Integer> newPos) {
        throw new UnsupportedOperationException(this.getGlobalErrMess());
    }

    /**
     * 
     */
    @Override
    public boolean isTargetInRange(final Actor target) {
        throw new UnsupportedOperationException(this.getGlobalErrMess());
    }

    /**
     * 
     */
    @Override
    public int getAPCost() {
        throw new UnsupportedOperationException(this.getGlobalErrMess());
    }

    /**
     * Identify the path of the cursor's image.
     * @return cursor's image's path, coded as String
     */
    protected String getCursorPath() {
        return CURSOR_PATH;
    }

     /**
     * Identify the depth render of the cursor
     * @return this ID
     */
    protected int getCursorDepth() {
        return CURSOR_DEPTH;
    }

}
