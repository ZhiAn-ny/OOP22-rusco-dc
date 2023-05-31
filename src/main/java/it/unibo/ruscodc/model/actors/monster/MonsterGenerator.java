package it.unibo.ruscodc.model.actors.monster;

import it.unibo.ruscodc.utils.Pair;

public interface MonsterGenerator {
    Monster makeMeleeRat(String name, Pair<Integer, Integer> pos);
    Monster makeRangedRat(String name, Pair<Integer, Integer> pos);
    Monster makeMageRat(String name, Pair<Integer, Integer> pos);
    Monster makeRogueOpossum(String name, Pair<Integer, Integer> pos);
    Monster makeRangedOpossum(String name, Pair<Integer, Integer> pos);
    Monster makeMeleeSeagull(String name, Pair<Integer, Integer> pos);
    Monster makeRangedSeagull(String name, Pair<Integer, Integer> pos);
    Monster makeBombCockroach(String name, Pair<Integer, Integer> pos);
}
