package it.unibo.ruscodc.model;

import it.unibo.ruscodc.utils.Pair;

public interface Entity {

    /**
     * @return
     */
    String getInfo();
    
    /**
     * @return
     */
    String getPath();
    
    /**
     * @return
     */
    Pair<Integer, Integer> getPos();
}
