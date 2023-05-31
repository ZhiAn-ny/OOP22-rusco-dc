package it.unibo.ruscodc.model.gamecommand.iacommand;

import java.util.Optional;

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

    public IAAttack(Range r, Range s, Effect eff) {
        this.range = r;
        this.splash = s;
        this.actionToPerform = eff;
    }

    /**
     * 
     */
    @Override
    public Pair<Integer, Integer> getActorPos() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getActorPos'");
    }

    /**
     * 
     */
    @Override
    public void setCursorPos(Pair<Integer, Integer> newPos) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setCursePos'");
    }

    /**
     * 
     */
    @Override
    public boolean isTargetInRange(Actor target) {
        return this.range.isInRange(this.getActor().getPos(), target.getPos(), target.getPos(), this.getRoom());
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
        // TODO - da fare
        throw new UnsupportedOperationException("Unimplemented method 'execute'");
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
