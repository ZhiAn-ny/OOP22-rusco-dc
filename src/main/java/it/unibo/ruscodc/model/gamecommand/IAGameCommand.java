package it.unibo.ruscodc.model.gamecommand;

import it.unibo.ruscodc.model.range.Range;

/**
 * Defines a set of method usefull to manage automatically a specific situation.
 * With this class the game can manage mobs
 */
public interface IAGameCommand {
    /**
     * Set into this builder a reference to the previous class for getting some infos.
     * @param observer the builder that build the command-object
     */
    void setObserver(ComplexObserver observer);
    //TODO-è ancora da pensare

    Range getRange();
}
