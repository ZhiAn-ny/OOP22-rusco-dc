package it.unibo.ruscodc.model;

import it.unibo.ruscodc.utils.Pair;

public interface Actor extends Entity {
    

    /**
     * @param newPos
     */
    void setPos(Pair<Integer, Integer> newPos);
    
    /**
     * @return
     */
    Skill getSkills();
    
    /**
     * @return
     */
    String getName();
    
    /**
     * @return
     */
    Stat getStats();
    
    /**
     * 
     */
    void load();
}
