package it.unibo.ruscodc.model.outputinfo;

import java.util.Collections;
import java.util.List;

import it.unibo.ruscodc.model.actors.Actor;
import it.unibo.ruscodc.utils.GameControl;
import it.unibo.ruscodc.utils.Pair;

/**
 * Wrapper-class that lead info about the actual hero by Model to View
 */
public class PortraitImpl implements Portrait {

    private static final long serialVersionUID = 10000L;

    private final Actor actor;
    private final double percentageHp;
    private final double percentageAp;

    /**
     * Create this object
     * @param actor the actor by pick some info
     * @param hpC its coefficent of HealPoint
     * @param apC its coefficent of ActionPoint
     */
    public PortraitImpl(final Actor actor, final double hpC, final double apC) {
        this.actor = Collections.nCopies(1, actor).get(0);
        this.percentageHp = hpC;
        this.percentageAp = apC;
    }

    /**
     * 
     */
    @Override
    public int getID() {
        return actor.getID();
    }

    /**
     * 
     */
    @Override
    public String getPath() {
        return actor.getPath();
    }

    /**
     * 
     */
    @Override
    public Pair<Integer, Integer> getPos() {
        return actor.getPos();
    }

    /**
     * 
     */
    @Override
    public double getHPcoeff() {
        return percentageHp;
    }

    /**
     * 
     */
    @Override
    public double getAPcoeff() {
        return percentageAp;
    }
}
