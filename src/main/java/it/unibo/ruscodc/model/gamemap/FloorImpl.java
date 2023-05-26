package it.unibo.ruscodc.model.gamemap;

import it.unibo.ruscodc.utils.Direction;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

/**
 * The <code>FloorImpl</code> class represents the basic implementation of the <code>Floor</code> interface.
 */
public class FloorImpl implements Floor {
    private Room currentRoom;
    private int unusedDoors;
    private boolean readyForNextFloor;
    private final int floorNum;
    private final RoomFactory roomFactory = new RoomFactoryImpl();
    private final Random rnd = new Random();
    private final List<Room> rooms = new ArrayList<>();
    private static final int ENTRANCE_SIZE = 5;
    private static final int MAX_ROOMS_NUMBER = 20;

    /**
     * Constructs a <code>Floor</code> with only an initial of size 5.
     * @param floorNum the level of the floor
     */
    public FloorImpl(final int floorNum) {
        this.floorNum = floorNum;
        this.currentRoom = this.roomFactory.emptySquareRoom(ENTRANCE_SIZE);
        this.roomFactory.addDoors(this.currentRoom);

        this.manageDoors();

        this.rooms.add(this.currentRoom);
    }

    private void manageDoors() {
        final int roomUnusedDoors = this.currentRoom.getConnectedRooms()
                .values().stream()
                .filter(Objects::isNull)
                .toList().size();
        this.unusedDoors += roomUnusedDoors;

        if (this.unusedDoors == 0 && !this.readyForNextFloor) {
            this.roomFactory.addDoors(this.currentRoom);
            this.manageDoors();
        }
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
        if (dir == Direction.UNDEFINED) {
            return;
        }
        if (this.currentRoom.getConnectedRoom(dir).isPresent()) {
            this.currentRoom = this.currentRoom.getConnectedRoom(dir).get();
            return;
        }

        final Room next = this.getNextRoom();
        this.populateRoom(next);
        if (this.currentRoom.addConnectedRoom(dir, next)) {
            this.unusedDoors = this.unusedDoors - 1;
            this.rooms.add(next);
            this.currentRoom = next;
            this.manageDoors();
        }
    }

    private Room getNextRoom() {
        final int minRooms = MAX_ROOMS_NUMBER - 10;
        if (this.getNRoomExplored() < minRooms) {
            return this.roomFactory.randomRoom();
        }

        final int left = MAX_ROOMS_NUMBER - this.getNRoomExplored();
        final int prob = this.rnd.nextInt(0, left + 1);
        if (prob == 0) {
            this.readyForNextFloor = true;
            return this.roomFactory.stairsRoom();
        }
        return this.roomFactory.randomRoomNoTraps();
    }

    private void populateRoom(final Room base) {
        this.roomFactory.addItems(base, this.floorNum);
        this.roomFactory.addMonsters(base, this.floorNum);
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return "FLOOR: " + this.floorNum + "\nUNUSED DOORS: " + this.unusedDoors;
    }
}
