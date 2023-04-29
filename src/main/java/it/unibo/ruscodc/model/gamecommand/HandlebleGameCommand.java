package it.unibo.ruscodc.model.gamecommand;

import java.util.Iterator;

import it.unibo.ruscodc.model.Entity;

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
     */
    void modify(int input); //TODO int diventerrà GameController, in questo branch ancora ingnoto

    /**
     * Help to understand where can move the begin of the wrapped effect.
     * @return an Iterator of some {@code}Entity{@code} that compose this area
     */
    Iterator<Entity> getRange();

    /**
     * Help to understand the space's region where the wrapped effect take effect.
     * @return an Iterator of some {@code}Entity{@code} that compose this area
     */
    Iterator<Entity> getSplash();

    /**
     * Help to understand the point where the wrapped effect begin.
     * @return the {@code}Entity{@code} that rappresent this point
     */
    Entity getCursePos();
} 
