package it.unibo.ruscodc.model.gamemap;

import it.unibo.ruscodc.utils.Direction;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * The <code>FloorImpl</code> class represents the basic implementation of the <code>Floor</code> interface.
 */
public class FloorImpl implements Floor {
    private Room currentRoom;
    private final RoomFactory roomFactory = new RoomFactoryImpl();
    private final List<Room> rooms = new ArrayList<>();
    private static final int ENTRANCE_SIZE = 5;
    private static final int MAX_ROOMS_NUMBER = 20;

    /**
     * Constructs a <code>Floor</code> with only an initial of size 5.
     */
    public FloorImpl() {
        this.currentRoom = this.roomFactory.squareRoom(ENTRANCE_SIZE);
        //this.currentRoom.addDoor(Direction.UNDEFINED); // TODO:
        this.rooms.add(this.currentRoom);
    }

    /** {@inheritDoc} */
    @Override
    public Room getCurrentRoom() {
        return this.currentRoom;
    }

    /** {@inheritDoc} */
    @Override
    public int getNRoomExplored() {
        return this.rooms.size();
    }

    /** {@inheritDoc} */
    @Override
    public void goToRoom(final Direction dir) {
        if (this.currentRoom.getConnectedRoom(dir).isPresent()) {
            this.currentRoom = this.currentRoom.getConnectedRoom(dir).get();
            return;
        }

        final Room next = this.getNextRoom();
        this.rooms.add(next);
        this.currentRoom.addConnectedRoom(dir, next);
    }

    private Room getNextRoom() {
        final int minRooms = MAX_ROOMS_NUMBER - 10;
        if (this.getNRoomExplored() < minRooms) {
            return this.roomFactory.randomRoom();
        }

        final int left = MAX_ROOMS_NUMBER - this.getNRoomExplored();
        final int prob = new Random().nextInt(0, left + 1);
        if (prob == 0) {
            return this.roomFactory.stairsRoom();
        }
        return this.roomFactory.randomRoom();
    }

}
