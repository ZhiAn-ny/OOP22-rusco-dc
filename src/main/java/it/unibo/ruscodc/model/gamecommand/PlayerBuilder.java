package it.unibo.ruscodc.model.gamecommand;

import java.util.Iterator;

import it.unibo.ruscodc.model.Entity;
import it.unibo.ruscodc.model.actors.Actor;
import it.unibo.ruscodc.model.effect.Effect;
import it.unibo.ruscodc.model.gamemap.Room;
import it.unibo.ruscodc.model.range.Range;
import it.unibo.ruscodc.utils.Pair;
import it.unibo.ruscodc.utils.exception.ModelException;
import it.unibo.ruscodc.utils.exception.NotInRange;

/**
 * Class that wrap an AttackCommand.
 */
public class PlayerBuilder implements HandlebleGameCommand {

    /*
    SkillType
    List<Actor>
    Skill
    */

    private static final String R_ERR = "The target is too far";
    private Actor from;
    private Room where;
    private boolean isReady = false;
    private final Range range;
    private final Range splash;
    private final Effect actionToPerform;
    private Pair<Integer, Integer> cursePos;

    /**
     * @param range define where the attack begin is legal
     * @param splash define where the effect is applied
     * @param action define what effect is to apply
     */
    public PlayerBuilder(final Range range, final Range splash, final Effect action) {
        this.range = range;
        this.splash = splash;
        this.actionToPerform = action;
        //TODO: inizializzare una posizione per il cursore (magari mirata)
        //TODO: piccolo problema... non vedo la stanza nè l'attore!
    }

    /**
     * 
     */
    @Override
    public void modify(final int input) {
        //TODO: aspetto il cambio da input a GameCommand
        throw new UnsupportedOperationException("Unimplemented method 'modify'");
    }

    /**
     * 
     */
    @Override
    public Iterator<Entity> getRange() {
        return range.getRange(from.getPos());
    }

    /**
     * 
     */
    @Override
    public Iterator<Entity> getSplash() {
        return splash.getRange(cursePos);
    }

    /**
     * 
     */
    @Override
    public Entity getCursePos() {
        return new Entity() {

            @Override
            public String getInfo() {
                return "Let's do something that starts here";
            }

            @Override
            public String getPath() {
                return "";
            }

            @Override
            public Pair<Integer, Integer> getPos() {
                return cursePos;
            }
        };
    }

    /**
     * 
     */
    @Override
    public boolean isReady() {
        return isReady;
    }

    /**
     * 
     */
    @Override
    public void execute() throws ModelException {
        if (!range.isInRange(from.getPos(), cursePos)) {
            throw new NotInRange(R_ERR);
        }
        //TODO - implement application of effect
    }

}
