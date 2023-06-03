package it.unibo.ruscodc.model.actors.monster.drop;

import it.unibo.ruscodc.model.actors.Actor;
import it.unibo.ruscodc.utils.Pair;

/**
 * Generate a DropManager by giving a specific set of information.
 */
public interface DropFactory {

    /**
     * Create a basic DropManager.
     * @param by the Actor to take info that help to create the drop
     * @return the computed DropManager
     */
    DropManager createGenericBasicDrop(Actor by);

    /**
     * As "createGenericBasicDrop", but the drop will be higher.
     * @param by the Actor to take info that help to create the drop
     * @return the computed DropManager
     */
    DropManager createGenericRichDrop(Actor by);

    /**
     * As "createGenericPoorDrop", but the drop will be lower.
     * @param by the Actor to take info that help to create the drop
     * @return the computed DropManager
     */
    DropManager createGenericPoorDrop(Actor by);

    /**
     * Create a DropManager usefull for the spawing of room.
     * @param roomSize the dimensions of a room
     * @param floorDepth the depth of the floor where the room is contained
     * @return the computed DropManager
     */
    DropManager createDropForRoom(Pair<Integer, Integer> roomSize, int floorDepth);

}
