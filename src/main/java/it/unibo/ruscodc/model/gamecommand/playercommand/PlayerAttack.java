package it.unibo.ruscodc.model.gamecommand.playercommand;

import java.util.Collection;
import java.util.Iterator;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import it.unibo.ruscodc.model.Entity;
import it.unibo.ruscodc.model.actors.Actor;
import it.unibo.ruscodc.model.actors.stat.StatImpl.StatName;
import it.unibo.ruscodc.model.effect.Effect;
import it.unibo.ruscodc.model.outputinfo.InfoPayload;
import it.unibo.ruscodc.model.outputinfo.InfoPayloadImpl;
import it.unibo.ruscodc.model.range.Range;
import it.unibo.ruscodc.utils.GameControl;
import it.unibo.ruscodc.utils.Pair;
import it.unibo.ruscodc.utils.Pairs;
import it.unibo.ruscodc.utils.exception.ModelException;
import it.unibo.ruscodc.utils.exception.Undo;

/**
 * Class that wrap an AttackCommand.
 */
public class PlayerAttack extends NoIACommand {
    private static final int CURSOR_DEPTH = 5;
    private static final String R_ERR = "The target is too far";
    private static final String AP_ERR = "Your AP is not sufficent";
    private final Range range;
    private final Range splash;
    private final Effect actionToPerform;
    private Pair<Integer, Integer> cursorPos;
    private boolean isReady;
    private boolean undo;

    /**
     * Defines some parts of the command, characterizing it.
     * @param range define where the attack begin is legal
     * @param splash define where the effect is applied
     * @param action define what effect is to apply
     */
    public PlayerAttack(final Range range, final Range splash, final Effect action) {
        this.range = range;
        this.splash = splash;
        this.actionToPerform = action;
    }

    private boolean moveCursor(final Pair<Integer, Integer> newPos) {
        if (this.getRoom().isInRoom(newPos)) {
            cursorPos = newPos;
            return true;
        } else {
            return false;
        }
    }

    /**
     * 
     */
    @Override
    public boolean modify(final GameControl input) {
        boolean mustUpdate = true;
        switch (input) {
            case MOVEUP: mustUpdate = moveCursor(Pairs.computeUpPair(cursorPos)); break;
            case MOVEDOWN: mustUpdate = moveCursor(Pairs.computeDownPair(cursorPos)); break;
            case MOVELEFT: mustUpdate = moveCursor(Pairs.computeLeftPair(cursorPos)); break;
            case MOVERIGHT: mustUpdate = moveCursor(Pairs.computeRightPair(cursorPos)); break;
            case CONFIRM: isReady = true; mustUpdate = false; break;
            case CANCEL: isReady = true; undo = true; mustUpdate = false; break;
            default: mustUpdate = false;
        }
        return mustUpdate;
    }

    /**
     * Get information about "range" to print to view.
     * @return an {@code}Iterator{@code} that iterate on this infos
     */
    private Iterator<Entity> getRange() {
        return range.getRange(this.getActorPos(), cursorPos, this.getRoom());
    }

    /**
     * Get information about "splash" to print to view.
     * @return an {@code}Iterator{@code} that iterate on this infos
     *  or {@value}null{@value} if the range is not valid (helps the player understand the correctness of the attack)
     */
    private Iterator<Entity> getSplash() {
        return splash.getRange(this.getActorPos(), cursorPos, this.getRoom());
    }

    /**
     * Compute the {@code}Entity{@code} that wrap for the view the cursor position.
     * @return the cursor position, abstracted into an Entity
     */
    private Entity getCurseAsEntity() {
        return new Entity() {

            @Override
            public String getPath() {
                return getCursorPath();
            }

            @Override
            public Pair<Integer, Integer> getPos() {
                return cursorPos;
            }

            @Override
            public int getID() {
                return CURSOR_DEPTH;
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
    public Iterator<Entity> getEntities() {
        final Iterator<Entity> splashRange = this.getSplash();
        final Iterator<Entity> rangeRange = this.getRange();
        return Stream.concat(
            Stream.concat(
                Stream.iterate(splashRange.next(), i -> splashRange.hasNext(), i -> splashRange.next()), 
                Stream.iterate(rangeRange.next(), i -> rangeRange.hasNext(), i -> rangeRange.next())),
            Stream.of(
                getCurseAsEntity()
            )
            ).iterator();
    }

    /**
     * 
     */
    @Override
    public Optional<InfoPayload> execute() throws ModelException {
        if (this.getRoom() == null || this.getActor() == null) {
            throw new IllegalStateException("");
        }

        if (undo) {
            throw new Undo("");
        }

        final Actor from = this.getActor();

        if (!range.isInRange(from.getPos(), cursorPos, cursorPos, this.getRoom())) {
            return Optional.of(new InfoPayloadImpl(getErrTitle(), R_ERR));
            //throw new NotInRange(R_ERR);
        }

        if (from.getStatActual(StatName.AP) < actionToPerform.getAPcost()) {
            return Optional.of(new InfoPayloadImpl(getErrTitle(), AP_ERR));
        } 
        from.modifyActualStat(StatName.AP, -actionToPerform.getAPcost());

        Set<Actor> targets = this.getRoom().getMonsters().stream()
            .filter(m -> splash.isInRange(from.getPos(), cursorPos, m.getPos(), this.getRoom()))
            .collect(Collectors.toSet());
        
        //Factory itemFactory = new FefjnijweniwufM;
        targets.forEach(m -> actionToPerform.applyEffect(from, m));
        targets.forEach(m -> {
            if (m.isAlive()) {
                //Set<Item> toDrop = itemFactory.getDrop(m);
            }
        });

        
        return Optional.empty();
    }

    /**
     * 
     */
    @Override
    public String toString() {
        return "Cost :" + actionToPerform.getAPcost() + "AP" 
            + "\nRange: " + range.toString() 
            + "\nSplash: " + splash.toString() 
            + "\nEffect: " + actionToPerform.toString() + "\n\n";
    }

}
