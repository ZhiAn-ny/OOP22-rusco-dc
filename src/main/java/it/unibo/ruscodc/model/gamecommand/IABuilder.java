package it.unibo.ruscodc.model.gamecommand;

import it.unibo.ruscodc.model.effect.Effect;
import it.unibo.ruscodc.model.range.Range;
import it.unibo.ruscodc.utils.Pair;

public class IABuilder extends BasicGameCommandImpl implements IAGameCommand{

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
    
}
