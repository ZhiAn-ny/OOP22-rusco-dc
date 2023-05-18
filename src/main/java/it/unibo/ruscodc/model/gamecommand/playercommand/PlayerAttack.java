package it.unibo.ruscodc.model.gamecommand.playercommand;

import java.util.Iterator;
import java.util.stream.Stream;

import it.unibo.ruscodc.model.Entity;
import it.unibo.ruscodc.model.actors.Actor;
import it.unibo.ruscodc.model.actors.stat.StatImpl.StatName;
import it.unibo.ruscodc.model.effect.Effect;
import it.unibo.ruscodc.model.gamecommand.GameCommand;
import it.unibo.ruscodc.model.range.CircleRange;
import it.unibo.ruscodc.model.range.DirectRowRange;
import it.unibo.ruscodc.model.range.Range;
import it.unibo.ruscodc.model.range.SingleRange;
import it.unibo.ruscodc.model.range.SingleSplash;
import it.unibo.ruscodc.utils.GameControl;
import it.unibo.ruscodc.utils.Pair;
import it.unibo.ruscodc.utils.Pairs;
import it.unibo.ruscodc.utils.exception.ModelException;
import it.unibo.ruscodc.utils.exception.NotInRange;
import it.unibo.ruscodc.utils.exception.Undo;

/**
 * Class that wrap an AttackCommand.
 */
public class PlayerAttack extends NoIACommand {

    private static final String R_ERR = "The target is too far";
    private final Range range;
    private final Range splash;
    private final Effect actionToPerform;
    private Pair<Integer, Integer> cursePos;
    private boolean isReady = false;
    private boolean undo = false;

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

    private boolean moveCursor(Pair<Integer, Integer> newPos) {
        if (this.getRoom().isInRoom(newPos)) {
            cursePos = newPos;
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
            case MOVEUP: mustUpdate = moveCursor(Pairs.computeUpPair(cursePos)); break;
            case MOVEDOWN: mustUpdate = moveCursor(Pairs.computeDownPair(cursePos)); break;
            case MOVELEFT: mustUpdate = moveCursor(Pairs.computeLeftPair(cursePos)); break;
            case MOVERIGHT: mustUpdate = moveCursor(Pairs.computeRightPair(cursePos)); break;
            case CONFIRM: isReady = true; mustUpdate = false;
            case CANCEL: isReady = true; undo = true; mustUpdate = false;
            default: mustUpdate = false;
        }
        return mustUpdate;
    }

    /**
     * Get information about "range" to print to view.
     * @return an {@code}Iterator{@code} that iterate on this infos
     */
    private Iterator<Entity> getRange() {
        return range.getRange(this.getActorPos(), cursePos, this.getRoom());
    }

    /**
     * Get information about "splash" to print to view.
     * @return an {@code}Iterator{@code} that iterate on this infos
     *  or {@value}null{@value} if the range is not valid (helps the player understand the correctness of the attack)
     */
    private Iterator<Entity> getSplash() {
        return splash.getRange(this.getActorPos(), cursePos, this.getRoom());
    }

    /**
     * Compute the {@code}Entity{@code} that wrap for the view the cursor position
     * @return the cursor position, abstracted into an Entity
     */
    private Entity getCurseAsEntity() {
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
    public Iterator<Entity> getEntities() {
        Iterator<Entity> splashRange = this.getSplash();
        Iterator<Entity> rangeRange = this.getRange();
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
    public void execute() throws ModelException {

        if (this.getRoom() == null || this.getActor() == null) {
            throw new IllegalStateException("");
        }
        if (undo) {
            throw new Undo("");
        }

        final Actor from = this.getActor();
        if (!range.isInRange(from.getPos(), cursePos, cursePos, this.getRoom())) {
            throw new NotInRange(R_ERR);
        }
        
        // if (from.getStatInfo(StatName.AP) < actionToPerform.getAPcost()) {
        //     throw new Undo("Il tuo AP non è sufficente");
        // } //TODO - incoming
        //from.modifyStat(StatName.AP, -actionToPerform.getAPcost());

        this.getRoom().getMonsters().stream()
            .filter(m -> splash.isInRange(from.getPos(), cursePos, m.getPos(), this.getRoom()))
            .forEach(m -> actionToPerform.applyEffect(from, m));
    }

}
