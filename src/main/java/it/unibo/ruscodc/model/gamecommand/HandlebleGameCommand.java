package it.unibo.ruscodc.model.gamecommand;

import java.util.Iterator;

import it.unibo.ruscodc.model.Entity;

public interface HandlebleGameCommand extends GameCommand{
    /**
     * 
     * @return
     */
    void modify(int input);
    /**
     * 
     * @return
     */
    Iterator<Entity> getRange();
    /**
     * 
     * @return
     */
    Iterator<Entity> getSplash();
    /**
     * 
     * @return
     */
    Entity getCursePos();
} 
