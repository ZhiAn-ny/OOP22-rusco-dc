package it.unibo.ruscodc.model.gamecommand;

import it.unibo.ruscodc.model.actors.Actor;
import it.unibo.ruscodc.model.gamemap.Room;

public abstract class BasicGameCommand implements GameCommand {

    private final static String GLOBAL_ERR_MESS = "Cannot execute this method on this object";

    private Actor actActor;
    private Room where;

    protected BasicGameCommand(){
    }

    @Override
    public void setActor(Actor by) {
        if (this.actActor == null) {
            actActor = by;
        }
    }

    @Override
    public void setRoom(Room where) {
        if (this.where == null) {
            this.where = where;
        }
    }

    /**
     * For avoid DRY, other classes that extends this class get who summon the command by this method.
     * @return who summon the command
     */
    protected Actor getActor() {
        return this.actActor;
    }

    /**
     * For avoid DRY, other classes that extends this class get where the command was summoned by this method.
     * @return where the command was summoned
     */
    protected Room getRoom() {
        return this.where;
    }
    
    protected String getGlobalErrMess() {
        return GLOBAL_ERR_MESS;
    }
    
}