package it.unibo.ruscodc.model;

import it.unibo.ruscodc.utils.Pair;

public interface Actor extends Entity {
    
    Pair<Integer, Integer> getPos();
    void setPos(Pair<Integer, Integer> newPos);
    Skill getSkills();
    String getName();
    Stat geStats();
    void load();
}
