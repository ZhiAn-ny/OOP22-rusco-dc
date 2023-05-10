package it.unibo.ruscodc.model.gamecommand;

import it.unibo.ruscodc.model.effect.Effect;
import it.unibo.ruscodc.model.range.Range;
import it.unibo.ruscodc.utils.Pair;
import it.unibo.ruscodc.utils.exception.ModelException;

public class IABuilder extends NoPlayerCommand {

    private final Range range;
    private final Range splash;
    private final Effect actionToPerform;

    public IABuilder(Range r, Range s, Effect eff) {
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
    public void setCursePos(Pair<Integer, Integer> newPos) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setCursePos'");
    }

    @Override
    public void execute() throws ModelException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'execute'");
    }
    
}
