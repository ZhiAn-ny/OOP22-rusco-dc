package it.unibo.ruscodc.model.gamecommand;

import java.util.Iterator;

import it.unibo.ruscodc.model.Entity;
import it.unibo.ruscodc.utils.GameControl;

/**
 * Defines a set of method usefull to manage manually a specific situation
 * With this class the player can manage a specific situation. 
 */
public interface HandlebleGameCommand extends GameCommand {
    /**
     * Modify the internal state of the builder, after getting an input.
     * For example, with this method, the player can:
     * <ul>
     * <li>move the cursor</li>
     * <li>abort the command</li>
     * <li>execute the command</li>
     * </ul>
     * 
     * @param input that modify the command
     * @return true if input have effectly change the builder state, false otherwise
     */
    boolean modify(GameControl input); 

    /**
     * An iterable collection of thing to be printed, updated at the actual moment
     * @return an Iterator of some {@code}Entity{@code}
     */
    Iterator<Entity> getEntities();
} 
