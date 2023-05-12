package it.unibo.ruscodc.model;

import it.unibo.ruscodc.utils.Pair;

public interface Entity {

    /**
     * @return a string used as ID for the entity
     */
    String getID();
    
    /**
     * @return the Path to the directory where you can find the associated files
     */
    String getPath();
    
    /**
     * @return the current position of the entity
     */
    Pair<Integer, Integer> getPos();
}
