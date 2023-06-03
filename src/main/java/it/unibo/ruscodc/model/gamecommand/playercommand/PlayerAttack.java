package it.unibo.ruscodc.model.gamecommand.playercommand;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
    private boolean isFirstTime = true;

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
        return super.commonModify(input);
    }

    /**
     * Get information about "range" to print to view.
     * @return an {@code}Iterator{@code} that iterate on this infos
     */
    private Set<Entity> getRange() {
        return range.getRange(
            this.getActor().getPos(), 
            super.getCursorPos(), 
            this.getRoom());
    }

    /**
     * Get information about "splash" to print to view.
     * @return an {@code}Iterator{@code} that iterate on this infos
     *  or {@value}null{@value} if the range is not valid (helps the player understand the correctness of the attack)
     */
    private Set<Entity> getSplash() {
        return splash.getRange(
            super.getCursorPos(), 
            this.getActor().getPos(), 
            this.getRoom());
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
                return getCursorPos();
            }

            @Override
            public int getID() {
                return getCursorDepth();
            }

        };
    }

    private boolean isCursorInRange() {
        return this.range.isInRange(
            this.getActor().getPos(), 
            super.getCursorPos(), 
            super.getCursorPos(), 
            this.getRoom());
    }

    /**
     * 
     */
    @Override
    public Set<Entity> getEntities() {
        if (isFirstTime) {
            reset();
        }
        final Set<Entity> toPrint = new HashSet<>();
        toPrint.add(getCursorAsEntity());
        if (this.isCursorInRange()) {
            toPrint.addAll(this.getSplash());
        }
        if (isFirstTime) {
            toPrint.addAll(this.getRange());
            isFirstTime = false;
        }
        final int min = toPrint.stream().min(Comparator.comparingInt(e -> e.getID())).get().getID();
        System.out.println("JP : " + min);
        return toPrint;
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
        attempCommand();

        if (this.getRoom() == null || this.getActor() == null) {
            throw new IllegalStateException("This type of command cannot know these information!");
        }

        if (super.mustAbortCommand()) {
            isFirstTime = true;
            reset();
            throw new Undo();
        }

        final Actor from = this.getActor();
        final Pair<Integer, Integer> tmp = getCursorPos();

        if (!range.isInRange(from.getPos(), tmp, tmp, this.getRoom())) {
            return Optional.of(new InfoPayloadImpl(getErrTitle(), R_ERR));
        }

        System.out.println("\n\n\n" + from.getStatActual(StatName.AP) + "\n\n\n");

        if (from.getStatActual(StatName.AP) < actionToPerform.getEffectAPcost()) {
            return Optional.of(new InfoPayloadImpl(getErrTitle(), AP_ERR));
        }

        from.modifyActualStat(StatName.AP, -actionToPerform.getEffectAPcost());

        final Set<Actor> targets = this.getRoom().getMonsters().stream()
            .filter(m -> splash.isInRange(tmp, from.getPos(), m.getPos(), this.getRoom()))
            .collect(Collectors.toSet());

        targets.forEach(m -> System.out.println("LM: P " + m.getStatActual(StatName.HP)));
        targets.forEach(m -> actionToPerform.applyEffect(from, m));
        targets.forEach(m -> System.out.println("LM: D " + m.getStatActual(StatName.HP)));

        final Random dice = new Random();
        final List<Actor> deadMonsters = targets.stream().filter(m -> !(m.isAlive())).collect(Collectors.toList());
        final List<DropManager> drops = deadMonsters.stream().map(a -> createMonsterDrop(a)).toList();
        IntStream.range(0, deadMonsters.size())
            .filter(i -> dice.nextBoolean())
            .forEach(i -> this.getRoom().put(
                deadMonsters.get(i).getPos(), 
                new Drop(new HashSet<>(drops.get(i).generateRandomDrop()), deadMonsters.get(i).getPos())));
        isFirstTime = true;
        super.reset();
        return Optional.empty();
    }

    /**
     * 
     */
    @Override
    public String toString() {
        return "Cost : " + actionToPerform.getEffectAPcost() + " AP" 
            + "\nRange: " + range.toString() 
            + "\nSplash: " + splash.toString() 
            + "\nEffect: " + actionToPerform.toString() + "\n\n";
    }

}
