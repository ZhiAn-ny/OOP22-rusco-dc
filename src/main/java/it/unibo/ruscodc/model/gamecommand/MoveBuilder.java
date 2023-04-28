package it.unibo.ruscodc.model.gamecommand;

import it.unibo.ruscodc.model.actors.Actor;
import it.unibo.ruscodc.model.gamemap.Room;
import it.unibo.ruscodc.model.range.Range;
import it.unibo.ruscodc.utils.Pair;
import it.unibo.ruscodc.utils.exception.ModelException;
import it.unibo.ruscodc.utils.exception.UnreacheblePos;

/**
 * //TODO
 */
public abstract class MoveBuilder implements BuilderGameCommand {

    private static final String ERR = "is already occupied or is out of the room";
    private Actor actActor = null;
    private Room where = null;

    public MoveBuilder() {

    }

    @Override
    public void setActor(final Actor from) {
        if (this.actActor == null) {
            this.actActor = from;
        }
    }

    @Override
    public void setRoom(final Room where) {
        if (this.where == null) {
            this.where = where;
        }
    }

    @Override
    public boolean isReady() {
        return true;
    }

    @Override
    public void modify(final int input) {
    }

    @Override
    public Range getRange() {
        return null;
    }

    @Override
    public Pair<Integer, Integer> getCursePos() {
        return null;
    }

    @Override
    public void execute() throws ModelException {
        Pair<Integer, Integer> newPos = computeNewPos();
        if (this.where.getMonsters().stream().map(a -> a.getPos()).anyMatch(p -> p.equals(newPos)) || !where.isInRoom(newPos)) {
            throw new UnreacheblePos("The position " + newPos.toString() + " " + ERR);
        }
        actActor.setPos(newPos);
    }

    /**
     * //TODO
     * @return
     */
    protected Pair<Integer, Integer> getActPos() {
        return actActor.getPos();
    }

    /**
     * //TODO
     * @return
     */
    protected abstract Pair<Integer, Integer> computeNewPos();
}
