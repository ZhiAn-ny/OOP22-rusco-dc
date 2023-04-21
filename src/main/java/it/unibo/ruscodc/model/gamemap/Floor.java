package it.unibo.ruscodc.model.gamemap;

import it.unibo.ruscodc.view.Drawable;

public interface Floor {

    /**
     * @return the room containing the PCs.
     */
    Room getCurrentRoom();

    /**
     * @return the number of rooms explored on this floor.
     */
    int getNRoomExplored();

}
