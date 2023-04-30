package it.unibo.ruscodc.model.gamemap;

import java.util.ArrayList;
import java.util.List;

/**
 * The <code>FloorImpl</code> class represents the basic implementation of the <code>Floor</code> interface.
 */
public class FloorImpl implements Floor {
    private Room currentRoom;
    private final List<Room> rooms = new ArrayList<>();
    private static final int ENTRANCE_SIZE = 5;

    public FloorImpl() {
        this.currentRoom = new RectangleRoomImpl(ENTRANCE_SIZE, ENTRANCE_SIZE);
        this.rooms.add(this.currentRoom);
    }

    @Override
    public Room getCurrentRoom() {
        return this.currentRoom;
    }

    @Override
    public int getNRoomExplored() {
        return this.rooms.size();
    }
}
