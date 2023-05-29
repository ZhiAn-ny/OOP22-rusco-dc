package it.unibo.ruscodc.utils.exception;

import it.unibo.ruscodc.utils.Pair;

public class ChangeRoomException extends ModelException{

    static final long serialVersionUID = 7001L;

    private final Pair<Integer, Integer> door;
    /**
     * Create this type of exception.
     * @param mess the message that advices that the room is changing
     */
    public ChangeRoomException(final String mess, final Pair<Integer, Integer> selectedDoor) {
        this.door =selectedDoor;
    }

    public Pair<Integer, Integer> getDoorPos() {
        return door;
    }


}
