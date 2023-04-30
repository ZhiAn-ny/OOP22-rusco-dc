package it.unibo.ruscodc.model.gamecommand;

import it.unibo.ruscodc.model.actors.Actor;
import it.unibo.ruscodc.model.gamemap.Room;
import it.unibo.ruscodc.utils.Pair;
import it.unibo.ruscodc.utils.exception.ModelException;
import it.unibo.ruscodc.utils.exception.UnreacheblePos;

/**
 * Class that check the movement of a specific Actor in a specific Room.
 * Classes that extends this class must only define what will be the new position of the Actor
 */
public abstract class MoveBuilder extends QuickActionBuilder {

    private static final String ERR = "The position ";
    private static final String ERR2 = " is already occupied or is out of the room";

    /**
     * 
     */
    @Override
    public void execute() throws ModelException {
        final Room where = this.getRoom();
        final Actor actActor = this.getActor();
        final Pair<Integer, Integer> newPos = this.computeNewPos();
        if (where.getMonsters().stream().map(a -> a.getPos()).anyMatch(p -> p.equals(newPos)) 
            || !where.isInRoom(newPos)) {
            throw new UnreacheblePos(ERR + newPos.toString() + ERR2);
        }
        actActor.setPos(newPos);
    }

    /**
     * Compute the new position.
     * This work must be defined in other dedicated classes.
     * @return the new position, to be checked
     */
    protected abstract Pair<Integer, Integer> computeNewPos();
}
