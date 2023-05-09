package it.unibo.ruscodc.model.gamecommand;

import java.util.Iterator;

import it.unibo.ruscodc.model.Entity;
import it.unibo.ruscodc.utils.GameControl;
import it.unibo.ruscodc.utils.Pair;
import it.unibo.ruscodc.utils.exception.ModelException;

/**
 * This abstract class defines that all the other class that extend this class will wrap an
 * action that tipically can do without any type of control (neither by player or IA).
 * So logically the wrapped command can be executed
 */ //TODO-a gradle non piace l'andata a capo
public abstract class QuickActionBuilder extends BasicGameCommandImpl implements HandlebleGameCommand, IAGameCommand  {

    /**
     * 
     */
    @Override
    public boolean isReady() {
        return true;
    }

    /**
     * 
     */
    @Override
    public abstract void execute() throws ModelException;

    @Override
    public Pair<Integer, Integer> getActorPos() {
        throw new UnsupportedOperationException("Unimplemented method 'getActorPos'");
    }

    @Override
    public void setCursePos(Pair<Integer, Integer> newPos) {
        throw new UnsupportedOperationException("Unimplemented method 'setCursePos'");
    }

    @Override
    public boolean modify(GameControl input) {
        throw new UnsupportedOperationException("Unimplemented method 'modify'");
    }

    @Override
    public Iterator<Entity> getEntities() {
        throw new UnsupportedOperationException("Unimplemented method 'getEntities'");
    }

}
