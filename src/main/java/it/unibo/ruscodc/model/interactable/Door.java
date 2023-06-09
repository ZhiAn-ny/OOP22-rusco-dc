package it.unibo.ruscodc.model.interactable;

import it.unibo.ruscodc.model.gamecommand.GameCommand;
import it.unibo.ruscodc.model.gamecommand.quickcommand.ChangeRoom;
import it.unibo.ruscodc.utils.Direction;
import it.unibo.ruscodc.utils.Pair;

/**
 * This class represents the door of the room.
 * It is used to move from one room to another.
 */
public class Door extends InteractableAbs {

    private final Direction doorDirection;
    private final String name;

    /**
     * The constructor of this class.
    * @param pos where spawn the door.
    * @param doorDirection the direction of the door.
    */
    public Door(final Pair<Integer, Integer> pos, final Direction doorDirection) {
        super(pos);
        this.name = "Door";
        this.doorDirection = doorDirection;
    }


    /**
     *
     */
    @Override
    public String getPath() {
        return "it/unibo/ruscodc/map_res/DoorTile/"
                + this.doorDirection.name();
    }

    /**
     *
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     *
     */
    @Override
    public GameCommand interact() {
        return new ChangeRoom(getPos());
    }

    /**
     *
     */
    @Override
    public boolean isTransitable() {
        return true;
    }
}
