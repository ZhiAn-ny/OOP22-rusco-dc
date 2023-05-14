package it.unibo.ruscodc.model.gamecommand;

import it.unibo.ruscodc.utils.exception.ChangeRoomException;
import it.unibo.ruscodc.utils.exception.ModelException;

public class ChangeRoom extends ChangeSituation {


    @Override
    public void execute() throws ModelException{
        throw new ChangeRoomException("The room is changing");
    }


}
