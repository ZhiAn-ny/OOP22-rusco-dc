package it.unibo.ruscodc.model.gamecommand;

import it.unibo.ruscodc.model.actors.Actor;
import it.unibo.ruscodc.model.gamemap.Room;

/**
 * Defines a set of method usefull for varios type of BuilderCommands.
 * The classes that implement this interface can be instantiate by Actor's Skill
 * @see Skill 
 * @see Actor
 */
public interface BuilderGameCommand extends GameCommand {
    /**
     * Set into builder-command who summon this command
     * Once setted, other call to this method will be ineffective
     * This method is indispensable to the future execution of the command
     * @param from the actor that summon this command
     */
    void setActor(Actor from);

    /**
     * Set into builder-command who summon this command
     * Once setted, other call to this method will be ineffective
     * This method is indispensable to the future execution of the command
     * @param where the room where this action is executing
     */
    void setRoom(Room where);
}
