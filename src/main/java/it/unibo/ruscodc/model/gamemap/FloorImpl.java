package it.unibo.ruscodc.model.gamemap;

import java.util.ArrayList;
import java.util.List;

/**
 * The <code>FloorImpl</code> class represents the basic implementation of the <code>Floor</code> interface.
 */
public class FloorImpl implements Floor {
    private Room currentRoom = null;
    private final List<Room> rooms = new ArrayList<>();

    public FloorImpl() {
        int entranceSize = 6;
        this.currentRoom = new RectangleRoomImpl(entranceSize, entranceSize);
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
