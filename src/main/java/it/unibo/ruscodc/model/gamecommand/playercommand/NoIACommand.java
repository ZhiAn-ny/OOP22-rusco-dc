package it.unibo.ruscodc.model.gamecommand.playercommand;

import java.util.List;

import it.unibo.ruscodc.model.actors.Actor;
import it.unibo.ruscodc.model.gamecommand.ComplexActionAbs;
import it.unibo.ruscodc.utils.GameControl;
import it.unibo.ruscodc.utils.Pair;
import it.unibo.ruscodc.utils.Pairs;

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
    private static final int CURSOR_DEPTH = 6;
    private boolean isReady; // = false;
    private boolean undo; // = false;
    private Pair<Integer, Integer> cursorPos;


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
     * 
     */
    @Override
    public void setCursorPos(final Pair<Integer, Integer> toFocus) {
        throw new UnsupportedOperationException(this.getGlobalErrMess());
    }

    /**
     * 
     */
    @Override
    public void setTarget(final List<Actor> targettableActors) {
        throw new UnsupportedOperationException(this.getGlobalErrMess());
    }

     /**
     * 
     */
    @Override
    public boolean isReady() {
        return isReady;
    }

    /**
     * Identify the path of the cursor's image.
     * @return cursor's image's path, coded as String
     */
    protected String getCursorPath() {
        return CURSOR_PATH;
    }

     /**
     * Identify the depth render of the cursor.
     * @return this ID
     */
    protected int getCursorDepth() {
        return CURSOR_DEPTH;
    }

    private boolean commonMoveCursor(final Pair<Integer, Integer> newPos) {
        //System.out.println(newPos);
        if (this.getRoom().isInRoom(newPos)) {
            cursorPos = newPos;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Basic implementation of modify.
     * @param input command to compute.
     * @return if controller must upload view.
     */
    protected boolean commonModify(final GameControl input) {
        boolean mustUpdate = true;
        switch (input) {
            case MOVEUP: mustUpdate = commonMoveCursor(Pairs.computeUpPair(cursorPos)); break;
            case MOVEDOWN: mustUpdate = commonMoveCursor(Pairs.computeDownPair(cursorPos)); break;
            case MOVELEFT: mustUpdate = commonMoveCursor(Pairs.computeLeftPair(cursorPos)); break;
            case MOVERIGHT: mustUpdate = commonMoveCursor(Pairs.computeRightPair(cursorPos)); break;
            case CONFIRM: isReady = true; mustUpdate = false; break;
            case CANCEL: isReady = true; undo = true; mustUpdate = false; break;
            default: mustUpdate = false;
        }
        return mustUpdate;
    }

    /**
     * Help to understand if a player desire to abort a command.
     * @return true if the player want init a new interaction, false otherwise
     */
    protected boolean mustAbortCommand() {
        return undo;
    }

    /**
     * Get the actual position of the cursor.
     * @return cursor pos
     */
    protected Pair<Integer, Integer> getCursorPos() {
        return cursorPos;
    }

    /**
     * Reset the status of a command.
     */
    protected void reset() {
        cursorPos = this.getActor().getPos();
        undo = false;
        isReady = false;
    }

    /**
     * Try to execute command, so flag "isReady" must to return false.
     */
    protected void attempCommand() {
        this.isReady = false;
    }

}
