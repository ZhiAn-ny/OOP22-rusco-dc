package it.unibo.ruscodc.model.gamecommand;

import it.unibo.ruscodc.utils.Pair;

/**
 * Defines a set of method usefull to manage automatically a specific situation.
 * With this class the game can manage mobs
 */
public interface IAGameCommand {

    /**
     * //TODO - da rifinire con il Behaviour. 
     * @return
     */
    Pair<Integer, Integer> getActorPos();

    /**
     * //TODO - da rifinire con il Behaviour.
     * @param newPos
     */
    void setCursePos(Pair<Integer, Integer> newPos);
}
