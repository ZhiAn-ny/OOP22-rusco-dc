package it.unibo.ruscodc.model;

import javafx.util.Pair;

public interface Actor extends Entity {
    Pair<Integer, Integer> getPos();
    void act();
}
