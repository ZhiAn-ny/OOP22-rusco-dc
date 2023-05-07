package it.unibo.ruscodc.model.gamemap;


import it.unibo.ruscodc.model.Entity;
import it.unibo.ruscodc.model.actors.Actor;
import it.unibo.ruscodc.utils.Direction;
import it.unibo.ruscodc.utils.Pair;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * The <code>Room</code> interface represents the generic concept of room in the game.
 *
 */
public interface Room {
    /**
     * Checks whether a specified position is part of the room.
     * @param pos the position check
     * @return <code>True</code> if the position is part of the room,
     * <code>False</code> otherwise
     */
    boolean isInRoom(Pair<Integer, Integer> pos);

    /**
     * Gets the monsters in the room.
     * @return a set representing the monsters in the room
     */
    Set<Actor> getMonsters();

    /**
     * Returns the objects in the room.
     * @return a set representing the objects in the room
     */
    Set<Entity> getObjectsInRoom();

    /**
     * Returns the <code>Room</code>'s tiles as <code>Entity</code>.
     * @return a list of <code>Entity</code> representing the room's tiles
     */
    List<Entity> getTilesAsEntity();

    /**
     * Places and entity on the specified position on the map.
     * @param pos the position at which place the entity.
     * @param obj the entity to place.
     * @return <code>True</code> if the entity has been placed correctly,
     * <code>False</code> otherwise.
     */
    boolean put(Pair<Integer, Integer> pos, Entity obj);

    /**
     * Returns whether the specified position is accessible to an actor.
     * @param pos the position in which move the actor
     * @return <code>True</code> if the tile at the specified position is
     * accessible, <code>False</code> otherwise.
     */
    boolean isAccessible(Pair<Integer,Integer> pos);

    /**
     * Returns the room connected to the current one on the specified side.
     * @param dir the side of the room on which stands the passage to the other room
     * @return an <code>Optional</code> containing the connected room if any,
     * otherwise returns an empty <code>Optional</code>
     */
    Optional<Room> getConnectedRoom(final Direction dir);

    /**
     * Connects two rooms between each other.
     * @param dir the side of the room to connect to the other
     * @param other the other room to connect
     * @return <code>True</code> if the two rooms have been connected correctly,
     * <code>False</code> otherwise.
     */
    boolean addConnectedRoom(final Direction dir, final Room other);

    /**
     * Adds a single door to the room.
     */
    void addDoor();

    /**
     * Returns the size of the <code>Room</code>.
     * @return a <code>Pair</code> containing the width and the height of the room.
     */
    Pair<Integer, Integer> getSize();
}
