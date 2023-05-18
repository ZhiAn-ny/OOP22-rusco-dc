package it.unibo.ruscodc.model.gamecommand.quickcommand;

import it.unibo.ruscodc.utils.Pair;
import it.unibo.ruscodc.utils.exception.ChangeRoomException;
import it.unibo.ruscodc.utils.exception.ModelException;

public class ChangeRoom extends ChangeSituation {

    private final Pair<Integer, Integer> door;

    public ChangeRoom(final Pair<Integer, Integer> doorPos){
        this.door = doorPos;
    }

    @Override
    public void execute() throws ModelException{
        throw new ChangeRoomException("The room is changing", door);
    }


}
