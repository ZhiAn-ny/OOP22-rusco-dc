package it.unibo.ruscodc.model.gamecommand.quickcommand;

import java.util.Optional;

import it.unibo.ruscodc.model.actors.Actor;
import it.unibo.ruscodc.model.gamemap.Room;
import it.unibo.ruscodc.model.outputinfo.InfoPayload;
import it.unibo.ruscodc.model.outputinfo.InfoPayloadImpl;
import it.unibo.ruscodc.utils.Pair;
import it.unibo.ruscodc.utils.exception.ModelException;

/**
 * Class that check the movement of a specific Actor in a specific Room.
 * Classes that extends this class must only define what will be the new position of the Actor
 */
public abstract class MoveCommand extends QuickActionAbs {

    private static final String ERR = "The position ";
    private static final String ERR2 = " is already occupied or is out of the room";

    /**
     * Client must not create directly this object.
     */
    protected MoveCommand() {
    }

    /**
     * 
     */
    @Override
    public Optional<InfoPayload> execute() throws ModelException {
        final Room where = this.getRoom();
        final Actor actActor = this.getActor();
        final Pair<Integer, Integer> newPos = this.computeNewPos();
        if (where.getMonsters().stream().map(a -> a.getPos()).anyMatch(p -> p.equals(newPos)) 
            || !where.isInRoom(newPos)) {
            final String err = ERR + newPos.toString() + ERR2;
            return Optional.of(new InfoPayloadImpl(getErrTitle(), err));
            //throw new UnreacheblePos(err);
        }
        actActor.setPos(newPos);
        return Optional.empty();
    }

    /**
     * Compute the new position.
     * This work must be defined in other dedicated classes.
     * @return the new position, to be checked
     */
    protected abstract Pair<Integer, Integer> computeNewPos();

    @Override
    public String toString() {
        return "Move " + this.getActor().getName();
    }   
}
