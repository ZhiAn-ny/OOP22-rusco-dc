package it.unibo.ruscodc.model.range;

import it.unibo.ruscodc.utils.Pair;

public class CircleRange extends AbstractRange {

    private final int size;

    public CircleRange(Pair<Integer, Integer> actPos, int size){
        super(actPos);
        this.size = size;
    }

    @Override
    public boolean isInRange(Pair<Integer, Integer> pos) {
        Pair<Integer, Integer> actPos = getActPos();
        return (Math.abs(actPos.getX() - pos.getX()) + Math.abs(actPos.getY() - pos.getY())) <= size;
    }
    
}
