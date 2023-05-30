package it.unibo.ruscodc.model.outputinfo;

import java.util.List;

import it.unibo.ruscodc.model.Entity;
import it.unibo.ruscodc.model.actors.Actor;
import it.unibo.ruscodc.utils.GameControl;
import it.unibo.ruscodc.utils.Pair;

public class PortraitImpl implements Portrait {

    private final Actor actor;
    private final double HP_Percentage;
    private final double AP_Percentage;

    /* private final static String ATT_P = null;
    private final static String USE_P = null; */

    public PortraitImpl(final Actor actor, final double hpC, final double apC){
        this.actor = actor;
        this.HP_Percentage = hpC;
        this.AP_Percentage = apC;
    }

    @Override
    public int getID() {
        return actor.getID();
    }

    @Override
    public String getPath() {
        return actor.getPath();
    }

    @Override
    public Pair<Integer, Integer> getPos() {
        return actor.getPos();
    }

    @Override
    public double getHPcoeff() {
        return HP_Percentage;
    }

    @Override
    public double getAPcoeff() {
        return AP_Percentage;
    }

    @Override
    public List<Pair<String, String>> getAttackInfo() {
        List<GameControl> attackControl = List.of(
            GameControl.BASEATTACK,
            GameControl.ATTACK1,
            GameControl.ATTACK2,
            GameControl.ATTACK3,
            GameControl.ATTACK4
        );
        
        return attackControl
            .stream()
            .map(gc -> actor.getSkills().getAction(gc))
            .filter(gc -> gc.isPresent())
            .map(gc -> gc.get())
            .map(gc -> new Pair<>("", gc.toString()))
            .toList();
    }
}
