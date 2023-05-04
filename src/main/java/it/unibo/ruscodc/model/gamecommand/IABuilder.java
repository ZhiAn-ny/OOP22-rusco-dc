package it.unibo.ruscodc.model.gamecommand;

import it.unibo.ruscodc.model.effect.Effect;
import it.unibo.ruscodc.model.range.Range;

public class IABuilder implements IAGameCommand{

    private ComplexObserver observer;
    private final Range range;
    private final Range splash;
    private final Effect actionToPerform;

    public IABuilder(Range r, Range s, Effect eff) {
        this.range = r;
        this.splash = s;
        this.actionToPerform = eff;
    }

    @Override
    public void setObserver(ComplexObserver observer) {
        if (this.observer == null){
            this.observer = observer;
        }
    }

    @Override
    public Range getRange() {
        return this.range;
    }
    
}
