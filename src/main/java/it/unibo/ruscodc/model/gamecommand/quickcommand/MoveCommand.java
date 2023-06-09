package it.unibo.ruscodc.model.gamecommand.quickcommand;

import java.util.Optional;

import it.unibo.ruscodc.model.actors.Actor;
import it.unibo.ruscodc.model.effect.SingleTargetEffect;
import it.unibo.ruscodc.model.gamecommand.GameCommand;
import it.unibo.ruscodc.model.gamemap.Room;
import it.unibo.ruscodc.model.gamemap.Tile;
import it.unibo.ruscodc.model.interactable.Interactable;
import it.unibo.ruscodc.utils.Pair;
import it.unibo.ruscodc.utils.exception.ModelException;
import it.unibo.ruscodc.utils.outputinfo.InfoPayload;
import it.unibo.ruscodc.utils.outputinfo.InfoPayloadImpl;

/**
 * Class that check the movement of a specific Actor in a specific Room.
 * Classes that extends this class must only define what will be the new position of the Actor
 */
public abstract class MoveCommand extends QuickActionAbs {

    private static final String ERR = "The position is already occupied or is out of the room";

    /**
     * Client must not create directly this object.
     */
    protected MoveCommand() { //NOPMD: if i don't add a comment to the costructor, 
            //checkstyle will generate an error. So i prefer document an empty constructor
    }

    /**
     * 
     */
    @Override
    public Optional<InfoPayload> execute() throws ModelException {
        final Room where = this.getRoom();
        final Pair<Integer, Integer> newPos = this.computeNewPos();
        if (where.getMonsters().stream().map(a -> a.getPos()).anyMatch(p -> p.equals(newPos)) 
            || !where.isAccessible(newPos)) {
            return Optional.of(new InfoPayloadImpl(getErrTitle(), ERR));
            //throw new UnreacheblePos(err);
        }

        final Actor actActor = this.getActor();
        actActor.setPos(newPos);

        final Optional<Tile> arrivedPos = where.get(newPos);
        if (arrivedPos.isPresent()) {
            final SingleTargetEffect tmp = arrivedPos.get().getEffect();
            tmp.applyEffect(actActor);
        }

        final Optional<Interactable> possibleInnerInteraction = arrivedPos.get().get();
        if (possibleInnerInteraction.isPresent()) {
            final GameCommand gc = possibleInnerInteraction.get().interact();
            gc.setActor(actActor);
            gc.setRoom(where);
            return gc.execute();
        }

        return Optional.empty();
    }

    /**
     * Compute the new position.
     * This work must be defined in other dedicated classes.
     * @return the new position, to be checked
     */
    protected abstract Pair<Integer, Integer> computeNewPos();

    /**
     * 
     */
    @Override
    public String toString() {
        return "Move " + this.getActor().getName();
    }
}
