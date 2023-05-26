package it.unibo.ruscodc.model.gamemap;


import it.unibo.ruscodc.model.Entity;
import it.unibo.ruscodc.model.actors.monster.Monster;
import it.unibo.ruscodc.model.interactable.Interactable;
import it.unibo.ruscodc.utils.Direction;
import it.unibo.ruscodc.utils.Pair;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * The <code>Room</code> interface represents the generic concept of room in the game.
 *
 */
public interface Room {
    /**
     * Returns the inner size of the <code>Room</code>.
     * @return a <code>Pair</code> containing the width and the height of the room.
     */
    Pair<Integer, Integer> getSize();

    /**
     * Returns the internal area of the room
     * @return the area of the room
     */
    int getArea();

    /**
     * Gets the monsters in the room.
     * @return a set representing the monsters in the room
     */
    List<Monster> getMonsters();

    /**
     * Adds a monster to the room.
     * @param monster the monster to add
     * @return <code>true</code> is the monster has been added to the <code>Room</code>, <code>false</code> otherwise.
     */
    boolean addMonster(Monster monster);

    /**
     * Returns the objects in the room.
     * @return a set representing the objects in the room
     */
    List<Interactable> getObjectsInRoom();

    /**
     * Places and entity on the specified position on the map.
     * @param pos the position at which place the entity.
     * @param obj the entity to place.
     * @return <code>True</code> if the entity has been placed correctly,
     * <code>False</code> otherwise.
     */
    boolean put(Pair<Integer, Integer> pos, Interactable obj);

    /**
     * Returns the <code>Room</code>'s tiles as <code>Entity</code>.
     * @return a list of <code>Entity</code> representing the room's tiles
     */
    List<Entity> getTilesAsEntity();

    /**
     * Checks whether a specified position is part of the room.
     * @param pos the position check
     * @return <code>True</code> if the position is part of the room,
     * <code>False</code> otherwise
     */
    boolean isInRoom(Pair<Integer, Integer> pos);

    /**
     * Returns the <code>Tile</code> at the specified position.
     * @param pos the position of the <code>Tile</code> to retrieve
     * @return an <code>Optional</code> containing the tile at said position or an empty <code>Optional</code> if the position is out of the room
     */
    Optional<Tile> get(Pair<Integer, Integer> pos);

    /**
     * Returns whether the specified position is accessible to an actor.
     * @param pos the position in which move the actor
     * @return <code>True</code> if the tile at the specified position is
     * accessible, <code>False</code> otherwise.
     */
    boolean isAccessible(Pair<Integer, Integer> pos);

    /**
     * Replaces the <code>Tile</code> at the specified position with a new one.
     * @param pos the position of the <code>Tile</code> to replace
     * @param newTile the new <code>Tile</code>
     * @return <code>true</code> if the <code>Tile</code> has been successfully replaced, <code>false</code> otherwise.
     */
    boolean replaceTile(Pair<Integer, Integer> pos, Tile newTile);

    /**
     * Clears the area centered in the specified position.
     * @param pos the center of the area
     * @param rad the radius of the area
     */
    void clearArea(Pair<Integer, Integer> pos, int rad);

    /**
     * Returns the room connected to the current one on the specified side.
     * @param dir the side of the room on which stands the passage to the other room
     * @return an <code>Optional</code> containing the connected room if any,
     * otherwise returns an empty <code>Optional</code>
     */
    Optional<Room> getConnectedRoom(Direction dir);

    /**
     * Returns a map containing all the rooms connected to the current one.
     * @return a map containing the sides on which the room's doors are placed and,
     * the <code>Room</code> connected if any.
     */
    Map<Direction, Room> getConnectedRooms();

    /**
     * Connects two rooms between each other.
     * @param dir the side of the room to connect to the other
     * @param other the other room to connect
     * @return <code>True</code> if the two rooms have been connected correctly,
     * <code>False</code> otherwise.
     */
    boolean addConnectedRoom(Direction dir, Room other);

    /**
     * Adds a single door to the room on the specified side.
     * The position of the door along the wall is selected randomly.
     * @param dir the side of the <code>Room</code> on which the door will be added
     * @return <code>true</code> is the door was added correctly, <code>false</code> otherwise.
     */
    boolean addDoor(Direction dir);

    /**
     * Adds a ramp of stairs to the room.
     * @param dir the side of the <code>Room</code> on which the stairs will be added
     * @return <code>true</code> is the stairs were added correctly, <code>false</code> otherwise.
     */
    boolean addStairs(Direction dir);

    /**
     * Returns the door on the specified side of the room if any.
     * @param side the side of the room on which search the door
     * @return an <code>Optional</code> containing the door or an empty <code>Optional</code> if there was no door
     */
    Optional<Interactable> getDoorOnSide(Direction side);

}
