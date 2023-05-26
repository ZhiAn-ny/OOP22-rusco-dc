package it.unibo.ruscodc.model.actors.monster.drop;

import it.unibo.ruscodc.model.actors.Actor;
import it.unibo.ruscodc.utils.Pair;

/**
 * TODO.
 */
public interface DropFactory {

    /**
     * TODO.
     * @param by TODO
     * @return TODO
     */
    DropManager createGenericBasicDrop(Actor by);

    /**
     * TODO.
     * @param by TODO
     * @return TODO
     */
    DropManager createGenericRichDrop(Actor by);

    /**
     * TODO.
     * @param by TODO
     * @return TODO
     */
    DropManager createGenericPoorDrop(Actor by);

    /**
     * TODO.
     * @param roomSize TODO
     * @param floorDepth TODO
     * @return TODO
     */
    DropManager createDropForRoom(Pair<Integer, Integer> roomSize, int floorDepth);

}
