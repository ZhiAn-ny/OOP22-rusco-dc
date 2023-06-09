package it.unibo.ruscodc.model.gamecommand.iacommand;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import it.unibo.ruscodc.model.actors.Actor;
import it.unibo.ruscodc.model.effect.Effect;
import it.unibo.ruscodc.model.range.Range;
import it.unibo.ruscodc.utils.Pair;
import it.unibo.ruscodc.utils.exception.ModelException;
import it.unibo.ruscodc.utils.outputinfo.InfoPayload;

/**
 * Class that wrap an IA attack (so an attack that game can manage and use, for example, for mobs).
 */
public class IAAttack extends NoPlayerCommand {

    private final Range range;
    private final Range splash;
    private final Effect actionToPerform;
    private Pair<Integer, Integer> cursor;
    private List<Actor> targets;

    /**
     * Create this type of object (see documetation about class).
     * @param r the Range obj that define the range for this obj
     * @param s the Range obj that define the spalsh for this obj
     * @param eff the Effect to apply to future targets
     */
    public IAAttack(final Range r, final Range s, final Effect eff) {
        this.range = r;
        this.splash = s;
        this.actionToPerform = eff;
    }

    /**
     * 
     */
    @Override
    public boolean isReady() {
        return true;
    }

    /**
     * 
     */
    @Override
    public boolean isTargetInRange(final Actor target) {
        return this.range.isInRange(this.getActor().getPos(), target.getPos(), target.getPos(), this.getRoom());
    }


    /**
     * 
     */
    @Override
    public int getAPCost() {
        return actionToPerform.getEffectAPcost();
    }

    /**
     * 
     */
    @Override
    public void setCursorPos(final Pair<Integer, Integer> toFocus) {
        this.cursor = toFocus;
    }

    /**
     * 
     */
    @Override
    public void setTarget(final List<Actor> targettableActors) {
        this.targets = new ArrayList<>(targettableActors);
    }

    /**
     * 
     */
    @Override
    public Optional<InfoPayload> execute() throws ModelException {
        if (this.targets == null || this.targets.isEmpty()) {
            return Optional.empty();
        }

        final Actor from = this.getActor();
        final Set<Actor> effectiveTargets = this.targets.stream()
            .filter(a -> splash.isInRange(cursor, from.getPos(), a.getPos(), this.getRoom()))
            .collect(Collectors.toSet());

        effectiveTargets.remove(from);
        effectiveTargets.forEach(a -> actionToPerform.applyEffect(from, a));
        return Optional.empty();
    }

    /**
     * 
     */
    @Override
    public String toString() {
        return "If a Hero is into a " + range.toString() + ", it can be targetable";
    }
}
