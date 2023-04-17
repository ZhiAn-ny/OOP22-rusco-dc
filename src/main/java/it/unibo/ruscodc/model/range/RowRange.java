package it.unibo.ruscodc.model.range;

import it.unibo.ruscodc.utils.Pair;

public final class RowRange extends AbstractRange{

    public RowRange(Pair<Integer, Integer> actPos) {
        super(actPos);
    }

    @Override
    public boolean isInRange(Pair<Integer, Integer> pos) {
        Pair<Integer, Integer> actPos = getActPos();
        return actPos.getX() == pos.getX() || actPos.getY() == actPos.getY()
            || Math.abs(actPos.getX() - pos.getX()) == Math.abs(actPos.getY() - pos.getY());
    }
    
}
