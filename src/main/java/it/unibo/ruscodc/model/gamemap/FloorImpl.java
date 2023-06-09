package it.unibo.ruscodc.model.gamemap;

import it.unibo.ruscodc.model.actors.monster.Monster;
import it.unibo.ruscodc.utils.Direction;
import it.unibo.ruscodc.utils.Pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Random;

/**
 * The <code>FloorImpl</code> class represents the basic implementation of the <code>Floor</code> interface.
 */
public class FloorImpl implements Floor {
    private static final int ENTRANCE_SIZE = 5;
    private static final int MAX_ROOMS_NUMBER = 20;

    private final transient Random rnd;
    private final transient RoomFactory roomFactory;

    private Room currentRoom;
    private int unusedDoors;
    private boolean readyForNextFloor;
    private final int floorNum;
    private final List<Room> rooms = new ArrayList<>();

    /**
     * Constructs a <code>Floor</code> with only an initial of size 5.
     * @param floorNum the level of the floor
     */
    public FloorImpl(final int floorNum) {
        this.floorNum = floorNum;
        this.rnd = new Random();
        this.roomFactory = new RoomFactoryImpl();
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
        return Collections.nCopies(1, this.currentRoom).get(0);
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

    /** {@inheritDoc} */
    @Override
    public void eliminateMonster(final Monster monster) {
        this.currentRoom.eliminateMonster(monster);
    }

    /** {@inheritDoc} */
    @Override
    public void clearArea(final Pair<Integer, Integer> pos, final int radius) {
        this.currentRoom.clearArea(pos, radius);
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

}
