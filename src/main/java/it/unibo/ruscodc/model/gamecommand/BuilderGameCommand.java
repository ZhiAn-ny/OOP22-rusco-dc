package it.unibo.ruscodc.model.gamecommand;

import it.unibo.ruscodc.model.actors.Actor;
import it.unibo.ruscodc.model.gamemap.Room;

/**
 * Defines a set of method usefull for varios type of BuilderCommands.
 * The classes that implement this interface should be instantiate by Actor's Skill
 * @see Skill 
 * @see Actor
 */
public interface BuilderGameCommand {
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
}
