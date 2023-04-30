package it.unibo.ruscodc.model.gamecommand;

import it.unibo.ruscodc.model.actors.Actor;
import it.unibo.ruscodc.model.gamemap.Room;

/**
 * This class provide to the implementation of {@code}BuilderGameCommand{@code} interface,
 * expecially because they do the same thing for many class.
 */ //TODO gradle da un errore di andata a capo, ma altrimenti è illeggibile da console...
public class BuilderGameCommandImpl implements BuilderGameCommand {

    private Actor actActor;
    private Room where;

    /**
     * 
     */
    @Override
    public void setActor(final Actor by) {
        if (this.actActor == null) {
            actActor = by;
        }
    }

    /**
     * 
     */
    @Override
    public void setRoom(final Room where) {
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
}
