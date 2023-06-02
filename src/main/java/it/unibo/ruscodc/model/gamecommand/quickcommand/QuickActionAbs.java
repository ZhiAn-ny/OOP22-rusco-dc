package it.unibo.ruscodc.model.gamecommand.quickcommand;

import java.util.Iterator;
import java.util.Optional;
import java.util.Set;

import it.unibo.ruscodc.model.Entity;
import it.unibo.ruscodc.model.actors.Actor;
import it.unibo.ruscodc.model.gamecommand.BasicGameCommand;
import it.unibo.ruscodc.model.outputinfo.InfoPayload;
import it.unibo.ruscodc.utils.GameControl;
import it.unibo.ruscodc.utils.Pair;
import it.unibo.ruscodc.utils.exception.ModelException;

/**
 * This abstract class defines that all the other class that extend this class will wrap an
 * action that tipically can do without any type of control (neither by player or IA).
 * So logically the wrapped command can be executed
 */
public abstract class QuickActionAbs extends BasicGameCommand  {

    /**
     * Client must not create this object directily.
     */
    protected QuickActionAbs() { //NOPMD: if I don't add a comment to the costructor, 
    //checkstyle will generate an error. So i prefer document an empty constructor
    }

    /**
     * 
     */
    @Override
    public boolean isReady() {
        return true;
    }

    /**
     * 
     */
    @Override
    public abstract Optional<InfoPayload> execute() throws ModelException;

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

}
