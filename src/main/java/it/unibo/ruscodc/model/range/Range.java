package it.unibo.ruscodc.model.range;

import java.util.Iterator;

import it.unibo.ruscodc.model.Entity;
import it.unibo.ruscodc.utils.Pair;

public interface Range {
    
    boolean isInRange(Pair<Integer, Integer> by, Pair<Integer, Integer> toCheck);

    Iterator<Entity> getRange(Pair<Integer, Integer> by);
}
