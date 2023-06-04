package it.unibo.ruscodc.utils.exception;

import it.unibo.ruscodc.utils.Pair;

/**
 * The <code>ChangeRoomException</code> exception triggers the room change event.
 */
public class ChangeRoomException extends ModelException {

    private static final long serialVersionUID = 7001L;

    private final transient Pair<Integer, Integer> door;

    /**
     * Create this type of exception.
     * @param selectedDoor the position of the door used to change room
     */
    public ChangeRoomException(final Pair<Integer, Integer> selectedDoor) {
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
