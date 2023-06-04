package it.unibo.ruscodc.model.outputinfo;

import it.unibo.ruscodc.model.Entity;

/**
 * Defines a set of method that complete the Entity's method to print to the schreen some 
 * info usefull for the player.
 */
public interface Portrait extends Entity {

    /**
     * Get the percentage of Heal Points of the actual hero.
     * @return this value
     */
    double getHPcoeff();

    /**
     * Get the percentage of Action Points of the actual hero.
     * @return this value
     */
    double getAPcoeff();

}
