package it.unibo.ruscodc.model.gamecommand;

import java.util.Iterator;

import it.unibo.ruscodc.model.Entity;
import it.unibo.ruscodc.model.actors.Actor;
import it.unibo.ruscodc.model.effect.Effect;
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
    private ComplexObserver observer;
    private final Range range;
    private final Range splash;
    private final Effect actionToPerform;
    private Pair<Integer, Integer> cursePos;
    private boolean isReady = false;

    /**
     * Defines some parts of the command, characterizing it.
     * @param range define where the attack begin is legal
     * @param splash define where the effect is applied
     * @param action define what effect is to apply
     */
    public PlayerBuilder(final Range range, final Range splash, final Effect action) {
        this.range = range;
        this.splash = splash;
        this.actionToPerform = action;
        //TODO - inizializzare una posizione per il cursore (magari mirata)
    }

    /**
     * 
     */
    @Override
    public void setObserver(final ComplexObserver observer) {
        if (this.observer == null) {
            this.observer = observer;
        }
    }

    /**
     * 
     */
    @Override
    public void modify(final int input) {
        //TODO - aspetto il cambio da input a GameCommand
        throw new UnsupportedOperationException("Unimplemented method 'modify'");
    }

    /**
     * 
     */
    @Override
    public Iterator<Entity> getRange() {
        return range.getRange(observer.getOriginalActor().getPos());
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
        if (this.observer == null) {
            throw new UnsupportedOperationException("Bad costruction of this object");
        }
        //Room r = observer.getOriginalRoom();
        final Actor a = observer.getOriginalActor();
        if (!range.isInRange(a.getPos(), cursePos)) {
            throw new NotInRange(R_ERR);
        }
        //TODO - implement application of effect
    }

}
