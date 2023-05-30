package it.unibo.ruscodc.model.gamecommand.playercommand;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import it.unibo.ruscodc.model.Entity;
import it.unibo.ruscodc.model.actors.Actor;
import it.unibo.ruscodc.model.actors.monster.drop.DropFactory;
import it.unibo.ruscodc.model.actors.monster.drop.DropFactoryImpl;
import it.unibo.ruscodc.model.actors.monster.drop.DropManager;
import it.unibo.ruscodc.model.actors.stat.StatImpl.StatName;
import it.unibo.ruscodc.model.effect.Effect;
import it.unibo.ruscodc.model.interactable.Drop;
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
    private static final Random DICE  = new Random();
    private static final DropFactory DROP_G = new DropFactoryImpl();
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
    private Set<Entity> getRange() {
        return range.getRange(this.getActorPos(), cursorPos, this.getRoom());
    }

    /**
     * Get information about "splash" to print to view.
     * @return an {@code}Iterator{@code} that iterate on this infos
     *  or {@value}null{@value} if the range is not valid (helps the player understand the correctness of the attack)
     */
    private Set<Entity> getSplash() {
        return splash.getRange(this.getActorPos(), cursorPos, this.getRoom());
    }

    /**
     * Compute the {@code}Entity{@code} that wrap for the view the cursor position.
     * @return the cursor position, abstracted into an Entity
     */
    private Entity getCursorAsEntity() {
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
                return getCursorDepth();
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
    public Set<Entity> getEntities() {
        final Set<Entity> splashRange = this.getSplash();
        final Set<Entity> rangeRange = this.getRange();

        return Stream.concat(
                    Stream.concat(
                        splashRange.stream(),
                        rangeRange.stream()), 
                    Stream.of(
                        getCursorAsEntity()
                    )).collect(Collectors.toSet());

        // return Stream.concat(
        //         Stream.concat(
        //             splashRange.stream(),
        //             rangeRange.stream()),
        //         Stream.of(
        //             getCursorAsEntity()
        //         )
        //     ).iterator();
    }

    private DropManager createMonsterDrop(final Actor by) {
        final int upperBound = 10;
        final List<Integer> bounds = List.of(3, 7);
        final int extracted = DICE.nextInt(upperBound);
        if (extracted < bounds.get(0)) {
            return DROP_G.createGenericPoorDrop(by);
        } else if (extracted < bounds.get(1)) {
            return DROP_G.createGenericBasicDrop(by);
        } else {
            return DROP_G.createGenericRichDrop(by);
        }
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

        final Set<Actor> targets = this.getRoom().getMonsters().stream()
            .filter(m -> splash.isInRange(from.getPos(), cursorPos, m.getPos(), this.getRoom()))
            .collect(Collectors.toSet());

        targets.forEach(m -> actionToPerform.applyEffect(from, m));
        final Random dice = new Random();
        final List<Actor> deadMonsters = targets.stream().filter(m -> !(m.isAlive())).collect(Collectors.toList());
        final List<DropManager> drops = deadMonsters.stream().map(a -> createMonsterDrop(a)).toList();
        IntStream.range(0, deadMonsters.size())
            .filter(i -> dice.nextBoolean())
            .forEach(i -> this.getRoom().put(
                deadMonsters.get(i).getPos(), 
                new Drop(new HashSet<>(drops.get(i).generateRandomDrop()), deadMonsters.get(i).getPos())));

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
