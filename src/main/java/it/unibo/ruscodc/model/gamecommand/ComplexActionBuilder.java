package it.unibo.ruscodc.model.gamecommand;

import it.unibo.ruscodc.model.actors.Actor;
import it.unibo.ruscodc.model.effect.Effect;
import it.unibo.ruscodc.model.gamemap.Room;
import it.unibo.ruscodc.model.range.Range;

/**
 * This concrete class help to build a specific builder that will help the game
 * to manage all type of decision.
 * For example, if is a hero turn, so it is the player turn, and player decide to make an attack
 * this object create a builder that will take all next input usefull to manage who attack and
 * check if the attack is legal
 */
public class ComplexActionBuilder extends BuilderGameCommandImpl implements ComplexObserver {
    private final Range r;
    private final Range s;
    private final Effect eff;

    /**
     * Use this constructor if you need to build a builder that don't need other informations.
     */
    public ComplexActionBuilder() {
        this.r = null;
        this.s = null;
        this.eff = null;
    }

    /**
     * Use this constructor if you need to build a builder that need the parameters.
     * @param range a "range" concept
     * @param splash a "splash" concept
     * @param eff an effect to apply into splash area
     */
    public ComplexActionBuilder(final Range range, final Range splash, final Effect eff) {
        this.r = range;
        this.s = splash;
        this.eff = eff;
    }


    /**
     * Help to check some thing for all build methods.
     */
    private void globalChecks() {
        if (this.getActor() == null || this.getRoom() == null) {
            throw new IllegalStateException();
        }
    }

    /**
     * When all is ready, build the object that will manage a situation
     * for the player.
     * @return this type of builder-command
     */ 
    public HandlebleGameCommand buildForPlayer() {
        globalChecks();
        if (r == null || s == null || eff == null) {
            throw new IllegalStateException();
        }
        return new PlayerBuilder(r, s, eff);
    }

    /**
     * When all is ready, build the object that will manage a situation
     * for a mob of the game.
     * @return this type of builder-command
     */ 
    public IAGameCommand buildForIA() {
        globalChecks();
        throw new UnsupportedOperationException("Unimplemented method 'buildForIA'");
    }

    
    @Override
    public Room getOriginalRoom() {
        return this.getRoom();
    }

    @Override
    public Actor getOriginalActor() {
        return this.getActor();
    }


}
