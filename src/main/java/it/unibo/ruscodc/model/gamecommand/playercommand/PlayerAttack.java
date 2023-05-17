package it.unibo.ruscodc.model.gamecommand.playercommand;

import java.util.Iterator;
import java.util.stream.Stream;

import it.unibo.ruscodc.model.Entity;
import it.unibo.ruscodc.model.actors.Actor;
import it.unibo.ruscodc.model.effect.Effect;
import it.unibo.ruscodc.model.range.CircleRange;
import it.unibo.ruscodc.model.range.DirectRowRange;
import it.unibo.ruscodc.model.range.Range;
import it.unibo.ruscodc.model.range.SingleRange;
import it.unibo.ruscodc.utils.GameControl;
import it.unibo.ruscodc.utils.Pair;
import it.unibo.ruscodc.utils.exception.ModelException;
import it.unibo.ruscodc.utils.exception.NotInRange;

/**
 * Class that wrap an AttackCommand.
 */
public class PlayerAttack extends NoIACommand {

    /*
    SkillType
    List<Actor>
    Skill
    */
    private static final String R_ERR = "The target is too far";
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
    public PlayerAttack(final Range range, final Range splash, final Effect action) {
        this.range = range;
        this.splash = splash;
        this.actionToPerform = action;
    }

    /**
     * 
     */
    @Override
    public boolean modify(final GameControl input) {
        return false;
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
        final Actor from = this.getActor();
        if (!range.isInRange(from.getPos(), cursePos, cursePos, this.getRoom())) {
            throw new NotInRange(R_ERR);
        }
        //TODO - implement application of effect
    }

}
