package it.unibo.ruscodc.model.gamecommand.iacommand;

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

public class IAAttack extends NoPlayerCommand {

    private final Range range;
    private final Range splash;
    private final Effect actionToPerform;
    private Actor target;

    public IAAttack(Range r, Range s, Effect eff) {
        this.range = r;
        this.splash = s;
        this.actionToPerform = eff;
    }

    /**
     * 
     */
    @Override
    public boolean isTargetInRange(Actor target) {
        //System.out.println("$$$ " + this.getActor().getName());
        System.out.print(" @ " + this.range.isInRange(this.getActor().getPos(), target.getPos(), target.getPos(), this.getRoom()));
        return this.range.isInRange(this.getActor().getPos(), target.getPos(), target.getPos(), this.getRoom());
    }

    /**
     * 
     */
    @Override
    public void setTarget(Actor toFocus) {
        this.target = toFocus;
    }

    /**
     * 
     */
    @Override
    public int getAPCost() {
        return actionToPerform.getAPcost();
    }

    @Override
    public Optional<InfoPayload> execute() throws ModelException {
        final Actor m = this.getActor();
        final Set<Actor> targets = this.getRoom().getMonsters().stream()
            .filter(a -> splash.isInRange(target.getPos(), m.getPos(), a.getPos(), this.getRoom()))
            .collect(Collectors.toSet());
        System.out.println("##########################");
        targets.forEach(t -> System.out.println(t.getName() + " " + t.getPos()));
        System.out.println("##########################");
        targets.remove(m);
        System.out.println(target);
        targets.forEach(a -> actionToPerform.applyEffect(m, a));
        //targets.forEach(m -> System.out.println("LM: D " + m.getStatActual(StatName.HP)));
        return Optional.empty();
    }

    @Override
    public String toString() {
        return "If a Hero is into a " + range.toString() + ", it can be targetable";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        IAAttack other = (IAAttack) obj;
        if (range == null) {
            if (other.range != null)
                return false;
        } else if (!range.equals(other.range))
            return false;
        if (splash == null) {
            if (other.splash != null)
                return false;
        } else if (!splash.equals(other.splash))
            return false;
        return true;
    }

    
}
