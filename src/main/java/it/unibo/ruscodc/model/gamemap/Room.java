package it.unibo.ruscodc.model.gamemap;


import it.unibo.ruscodc.model.Actor;
import it.unibo.ruscodc.model.Entity;
import it.unibo.ruscodc.utils.Pair;

import java.util.Set;

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
     * Places and entity on the specified position on the map.
     * @param pos the position at which place the entity.
     * @param obj the entity to place.
     * @return <code>True</code> if the entity has been placed correctly,
     * <code>False</code> otherwise.
     */
    boolean put(Pair<Integer, Integer> pos, Entity obj);

}
