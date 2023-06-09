package it.unibo.ruscodc.model.gamecommand;

import java.util.Collections;

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
 * a class that implement two setter, that set these two informations
 */
public abstract class BasicGameCommand implements GameCommand {

    private static final String GLOBAL_ERR_MESS = "Cannot execute this method on this object";
    private static final String ERR_TITLE = "Error during execution of command";

    private Actor actActor;
    private Room where;

    /**
     * Client must not create directily this objects.
     */
    protected BasicGameCommand() { //NOPMD: if i don't add a comment here, checkstyle will generate an error.
                        //So i prefer document an empty constructor
    }

    /**
     * 
     */
    @Override
    public void setActor(final Actor by) {
        //if (this.actActor == null) {
            actActor = Collections.nCopies(1, by).get(0);
            //actActor = by;
        //}
    }

    /**
     * 
     */
    @Override
    public void setRoom(final Room where) {
        //if (this.where == null) {
            this.where = Collections.nCopies(1, where).get(0);
            //this.where = where;
        //}
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

    /**
     * A message error useful for coders that advise the method is not invocable for this object.
     * (for example, for all QuickCommand objects, method like "modify" (specific of HandableGameCommand interface),
     * is not invocable).
     * @return this message error 
     */
    protected String getGlobalErrMess() {
        return GLOBAL_ERR_MESS;
    }

    /**
     * For avoid DRY, other classes that extends this class can get a standard error title.
     * @return the error title, something that advise that action is not terminated correctly
     */
    protected String getErrTitle() {
        return ERR_TITLE;
    }

}
