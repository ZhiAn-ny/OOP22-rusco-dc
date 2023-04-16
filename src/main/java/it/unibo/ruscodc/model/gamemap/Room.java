package it.unibo.ruscodc.model.gamemap;


import it.unibo.ruscodc.model.Actor;
import it.unibo.ruscodc.utils.Pair;

import java.util.Set;

public interface Room {
    /**
     * Checks whether a specified position is part of the room.
     * @param pos the position check
     * @return <code>True</code> if the position is part of the room,
     * <code>False</code> otherwise
     */
    boolean isInRoom(Pair pos);

    /**
     * Gets the monsters in the room.
     * @return a set representing the monsters in the room
     */
    Set<Actor> getMonsters();

}
