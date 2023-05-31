package it.unibo.ruscodc.model.actors.monster;

import it.unibo.ruscodc.utils.Pair;

public interface MonsterGenerator {
    Monster makeMeleeRat(Pair<Integer, Integer> pos);
    Monster makeRangedRat(Pair<Integer, Integer> pos);
    Monster makeMageRat(Pair<Integer, Integer> pos);
    Monster makeRogueOpossum(Pair<Integer, Integer> pos);
    Monster makeRangedOpossum(Pair<Integer, Integer> pos);
    Monster makeMeleeSeagull(Pair<Integer, Integer> pos);
    Monster makeRangedSeagull(Pair<Integer, Integer> pos);
    Monster makeBombCockroach(Pair<Integer, Integer> pos);
}
