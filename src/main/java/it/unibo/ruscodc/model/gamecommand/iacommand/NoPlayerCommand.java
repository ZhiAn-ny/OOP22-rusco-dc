package it.unibo.ruscodc.model.gamecommand.iacommand;

import java.util.Iterator;

import it.unibo.ruscodc.model.Entity;
import it.unibo.ruscodc.model.gamecommand.ComplexActionBuilder;
import it.unibo.ruscodc.utils.GameControl;

public abstract class NoPlayerCommand extends ComplexActionBuilder {

    /**
     * 
     */
    @Override
    public boolean modify(GameControl input) {
        throw new UnsupportedOperationException(this.getGlobalErrMess());
    }

    /**
     * 
     */
    @Override
    public Iterator<Entity> getEntities() {
        throw new UnsupportedOperationException(this.getGlobalErrMess());
    }
    
}
