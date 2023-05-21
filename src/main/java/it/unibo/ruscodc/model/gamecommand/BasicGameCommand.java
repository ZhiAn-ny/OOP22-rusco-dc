package it.unibo.ruscodc.model.gamecommand;

import it.unibo.ruscodc.model.actors.Actor;
import it.unibo.ruscodc.model.gamemap.Room;

/**
 * Initial implementation of GameCommand.
 * Generally each type of command must know at least two information:
 * <ul>
 * <li> who launch the command </li>
 * <li> where the command was launched </li>
 * </ul>
 * 
 * but these two information are unkonwed when the command-object is created.
 * So, to avoid DRY, i'll provide an basic implementation for all game command:
 * a class that implement two one-use setter, that set these two informations
 */
public abstract class BasicGameCommand implements GameCommand {

    private final static String GLOBAL_ERR_MESS = "Cannot execute this method on this object";
    private final static String ERR_TITLE = "Error during execution of command";

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

    protected String getErrTitle() {
        return ERR_TITLE;
    }
    
}
