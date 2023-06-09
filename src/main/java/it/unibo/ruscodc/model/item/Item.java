package it.unibo.ruscodc.model.item;

import it.unibo.ruscodc.utils.outputinfo.InfoPayload;

/**
 * Interface for the Items of the game.
 */
public interface Item {

    /**
     * @return the Name of the Item
     */
    String getName();

    /**
     * @return the PAth to info related to the Item
     */
    String getPath();

    /**
     * @return the InfoPayload related to the Item
     */
    InfoPayload getInfo();

    /**
     * @return if the Item is Werable or not
     */
    boolean isWearable();
}
