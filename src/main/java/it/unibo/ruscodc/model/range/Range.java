package it.unibo.ruscodc.model.range;

import it.unibo.ruscodc.utils.Pair;

public interface Range {
    
    boolean isInRange(Pair<Integer, Integer> p);
}
