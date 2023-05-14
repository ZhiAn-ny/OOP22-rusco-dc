package it.unibo.ruscodc.model.gamecommand;

import it.unibo.ruscodc.model.actors.Actor;
import it.unibo.ruscodc.model.gamemap.Room;
import it.unibo.ruscodc.utils.exception.ModelException;

/**
 * Interface for game's command. Defines method that all builder's command must to implement
 */
public interface GameCommand extends IAGameCommand, HandlebleGameCommand {

    /**
     * Set into builder-command who summon this command.
     * Once setted, other call to this method will be ineffective
     * This method is indispensable to the future execution of the command
     * @param by the actor that summon this command
     */
    void setActor(Actor by);

    /**
     * Set into builder-command who summon this command.
     * Once setted, other call to this method will be ineffective
     * This method is indispensable to the future execution of the command
     * @param where the room where this action is executing
     */
    void setRoom(Room where);

    /**
     * Check if the wrapped command is logically ready to be executed.
     * @return the relative boolean value
     */
    boolean isReady();

    /**
     * Execute the wrapped command.
     * Even if the command can logically run, maybe there is a problem
     * For example, if the Actor wants to move up, but it cannot do it because there is a wall
     * @throws ModelException 
     */
    void execute() throws ModelException;
}
