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
    private final int maxRoomsNumber = 20;

    public FloorImpl() {
        this.currentRoom = this.roomFactory.squareRoom(ENTRANCE_SIZE);
        this.currentRoom.addDoor(Direction.UNDEFINED);
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

    @Override
    public void goToRoom(Direction dir) {
        if (this.currentRoom.getConnectedRoom(dir).isPresent()) {
            this.currentRoom = this.currentRoom.getConnectedRoom(dir).get();
            return;
        }

        Room next = this.getNextRoom();
        this.rooms.add(next);
        this.currentRoom.addConnectedRoom(dir, next);
    }

    private Room getNextRoom() {
        int minRooms = this.maxRoomsNumber - 10;
        final Random rnd = new Random();
        int left = this.maxRoomsNumber - this.getNRoomExplored();
        int prob = rnd.nextInt(0, left + 1);

        if (this.getNRoomExplored() < minRooms) {
            return this.roomFactory.randomRoom();
        }
        if (prob == 0) {
            return this.roomFactory.stairsRoom();
        }
        return this.roomFactory.randomRoom();
    }

}
