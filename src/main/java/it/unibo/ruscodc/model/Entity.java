package it.unibo.ruscodc.model;

import it.unibo.ruscodc.utils.Pair;

public interface Entity {
    String getInfo();
    String getPath();
    Pair<Integer, Integer> getPos();
}
