package it.unibo.ruscodc.model.gamecommand.iacommand;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import it.unibo.ruscodc.model.actors.Actor;
import it.unibo.ruscodc.model.effect.Effect;
import it.unibo.ruscodc.model.outputinfo.InfoPayload;
import it.unibo.ruscodc.model.range.Range;
import it.unibo.ruscodc.utils.Pair;
import it.unibo.ruscodc.utils.exception.ModelException;

/**
 * TODO - documentazione!.
 */
public class IAAttack extends NoPlayerCommand {

    private final Range range;
    private final Range splash;
    private final Effect actionToPerform;
    private Pair<Integer, Integer> cursor;
    private List<Actor> targes;

    /**
     * TODO - documentazione!.
     * @param r TODO - documentazione!.
     * @param s TODO - documentazione!.
     * @param eff TODO - documentazione!. 
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
    public boolean isTargetInRange(final Actor target) {
        //System.out.println("$$$ " + this.getActor().getName());
        System.out.print(" @ " 
            + this.range.isInRange(this.getActor().getPos(), target.getPos(), target.getPos(), this.getRoom()));
        return this.range.isInRange(this.getActor().getPos(), target.getPos(), target.getPos(), this.getRoom());
    }


    /**
     * 
     */
    @Override
    public int getAPCost() {
        return actionToPerform.getAPcost();
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
        this.targes = new ArrayList<>(targettableActors);
    }

    /**
     * 
     */
    @Override
    public Optional<InfoPayload> execute() throws ModelException {
        final Actor from = this.getActor();
        final Set<Actor> targets = this.targes.stream()
            .filter(a -> splash.isInRange(cursor, from.getPos(), a.getPos(), this.getRoom()))
            .collect(Collectors.toSet());
        System.out.println("##########################");
        targets.forEach(t -> System.out.println(t.getName() + " " + t.getPos()));
        System.out.println("##########################");
        targets.remove(from);
        System.out.println(targets);
        targets.forEach(a -> actionToPerform.applyEffect(from, a));
        //targets.forEach(m -> System.out.println("LM: D " + m.getStatActual(StatName.HP)));
        return Optional.empty();
    }

    /**
     * 
     */
    @Override
    public String toString() {
        return "If a Hero is into a " + range.toString() + ", it can be targetable";
    }

    // @Override
    // public boolean equals(Object obj) {
    //     if (this == obj)
    //         return true;
    //     if (obj == null)
    //         return false;
    //     if (getClass() != obj.getClass())
    //         return false;
    //     IAAttack other = (IAAttack) obj;
    //     if (range == null) {
    //         if (other.range != null)
    //             return false;
    //     } else if (!range.equals(other.range))
    //         return false;
    //     if (splash == null) {
    //         if (other.splash != null)
    //             return false;
    //     } else if (!splash.equals(other.splash))
    //         return false;
    //     return true;
    // }
}
