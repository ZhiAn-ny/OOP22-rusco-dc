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

    private static final long serialVersionUID = 3001L;

    private final Direction doorDirection;
    private String name;
    private String path;
    private String info;
    //private final DoorType doorType;

    /**
     * The constructor of this class build a door.
     * @param pos is a position where the door spawn.
     */
    public Door(final Pair<Integer, Integer> pos, final Direction doorDirection ){
        super(pos);
        this.name = "Door";
        this.doorDirection = doorDirection;
    }


    /**
     *
     */
    @Override
    public String getPath() {
        return "file:src/main/resources/it/unibo/ruscodc/map_res/DoorTile/"
                + this.doorDirection.name();
    }

    /**
     *
     */
    @Override
    public String getName() {
        return null;
    }

    /**
     *
     */
    @Override
    public GameCommand interact() {
        return new ChangeRoom(getPos());
    }

    @Override
    public boolean isTransitable() {
        return true;
    }
}
