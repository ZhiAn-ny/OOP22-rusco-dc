package it.unibo.ruscodc.model.actors;

import it.unibo.ruscodc.model.Entity;
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
