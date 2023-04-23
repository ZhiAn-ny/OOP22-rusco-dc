package it.unibo.ruscodc.model.gamecommand;

import it.unibo.ruscodc.model.actors.Actor;
import it.unibo.ruscodc.model.gamemap.Room;
import it.unibo.ruscodc.model.range.Range;
import it.unibo.ruscodc.utils.Pair;
import it.unibo.ruscodc.utils.exception.ModelException;
import it.unibo.ruscodc.utils.exception.UnreacheblePos;

public abstract class MoveBuilder implements BuilderGameCommand {

    private final static String ERR = "is already occupied or is out of the room";
    private Actor actActor = null;
    private Room where;

    public MoveBuilder(){

    }

    @Override
    public void setActor(Actor from) {
        if(actActor == null){
            this.actActor = from;
        }
    }

    @Override
    public void setRoom(Room where) {
        if(where == null){
            this.where = where;
        }
    }

    @Override
    public boolean isReady() {
        return true;
    }

    @Override
    public void modify(int input) {
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
        Pair<Integer,Integer> newPos = computeNewPos();
        if(where.getMonsters().stream().map(a -> a.getPos()).anyMatch(p -> p.equals(newPos)) || where.isInRoom(newPos)){
            throw new UnreacheblePos("The position " + newPos.toString() + " " + ERR);
        }
        actActor.setPos(newPos);
    }

    protected Pair<Integer, Integer> getActPos(){
        return actActor.getPos();
    }

    protected abstract Pair<Integer, Integer> computeNewPos();
    
}
