package it.unibo.ruscodc.model.gamecommand;

import java.util.Iterator;

import it.unibo.ruscodc.model.Entity;

/**
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
     * @param input that modify the command
     */
    void modify(int input);

    /**
     * Help the player to understand where can move the begin of it's action.
     * @return an Iterator of some {@code}Entity{@code} that compose this area
     */
    Iterator<Entity> getRange();

    /**
     * Help the player to understand the space's region where its effect take effect.
     * @return an Iterator of some {@code}Entity{@code} that compose this area
     */
    Iterator<Entity> getSplash();

    /**
     * Help the player to understand the point where it's effect begin.
     * @return the {@code}Entity{@code} that rappresent this point
     */
    Entity getCursePos();
} 
