package it.unibo.ruscodc.model.actors;

import java.util.Map;

import it.unibo.ruscodc.model.gamecommand.BuilderGameCommand;
import it.unibo.ruscodc.utils.GameControl;

/**
 * Interface of the Skill used by Actors.
 */
public interface Skill {

    /**
     * @param key
     * @param action
     */
    void setAction(int key, BuilderGameCommand action);

    /**
     * @param key
     * @return the Builder from the correct input
     */
    BuilderGameCommand getAction(int key);

    /**
     * @return the Map of <Key,Action>
     */
    Map<Integer, BuilderGameCommand> getSkills();
}
