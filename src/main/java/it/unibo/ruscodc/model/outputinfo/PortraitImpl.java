package it.unibo.ruscodc.model.outputinfo;

import java.util.List;

import it.unibo.ruscodc.model.actors.Actor;
import it.unibo.ruscodc.utils.GameControl;
import it.unibo.ruscodc.utils.Pair;

/**
 * TODO documetazione!.
 */
public class PortraitImpl implements Portrait {

    private static final long serialVersionUID = 10000L;

    private final Actor actor;
    private final double percentageHp;
    private final double percentageAp;

    /* private final static String ATT_P = null;
    private final static String USE_P = null; */

    /**
     * TODO documetazione!.
     * @param actor TODO documetazione!.
     * @param hpC TODO documetazione!.
     * @param apC TODO documetazione!.
     */
    public PortraitImpl(final Actor actor, final double hpC, final double apC) {
        this.actor = actor;
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

    /**
     * 
     */
    @Override
    public List<Pair<String, String>> getAttackInfo() {
        final List<GameControl> attackControl = GameControl.getAttackControls();
        return attackControl
            .stream()
            .map(gc -> actor.getSkills().getAction(gc))
            .filter(gc -> gc.isPresent())
            .map(gc -> gc.get())
            .map(gc -> new Pair<>("", gc.toString()))
            .toList();
    }
}
