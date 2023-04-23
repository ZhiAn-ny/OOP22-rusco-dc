package it.unibo.ruscodc.model.gamecommand;

import it.unibo.ruscodc.model.actors.Actor;
import it.unibo.ruscodc.model.gamemap.Room;
import it.unibo.ruscodc.model.range.Range;
import it.unibo.ruscodc.utils.Pair;
import it.unibo.ruscodc.utils.exception.ModelException;

public interface BuilderGameCommand {
    /**
     * 
     * @param from
     */
    void setActor(Actor from);
    /**
     * 
     * @param where
     */
    void setRoom(Room where);
    /**
     * 
     * @return
     */
    boolean isReady();
    /**
     * 
     * @param input
     */
    void modify(int input);
    /**
     * 
     * @return
     */
    Range getRange();
    /**
     * 
     * @return
     */
    Pair<Integer, Integer> getCursePos();
    /**
     * 
     * @throws ModelException
     */
    void execute() throws ModelException;
}
