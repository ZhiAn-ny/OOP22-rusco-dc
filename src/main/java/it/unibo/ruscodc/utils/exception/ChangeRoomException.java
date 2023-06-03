package it.unibo.ruscodc.utils.exception;

import it.unibo.ruscodc.utils.Pair;

/**
 * The <code>ChangeRoomException</code> exception triggers the room change event.
 */
public class ChangeRoomException extends ModelException {

    static final long serialVersionUID = 7001L;

    private final Pair<Integer, Integer> door;
    /**
     * Create this type of exception.
     * @param mess the message that advices that the room is changing
     * @param selectedDoor the position of the door used to change room
     */
    public ChangeRoomException(final String mess, final Pair<Integer, Integer> selectedDoor) {
        this.door = selectedDoor;
    }

    /**
     * Returns the position of the door with which the interaction was made.
     * @return the position of the door
     */
    public Pair<Integer, Integer> getDoorPos() {
        return door;
    }


}
