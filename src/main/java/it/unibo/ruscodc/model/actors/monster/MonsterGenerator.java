package it.unibo.ruscodc.model.actors.monster;

import it.unibo.ruscodc.utils.Pair;

public interface MonsterGenerator {
    Monster makeMeleeRat(String name, Pair<Integer, Integer> pos);
    Monster makeRangedRat(String name, Pair<Integer, Integer> pos);
}
