package it.unibo.ruscodc.model.actors.monster;

import it.unibo.ruscodc.utils.Pair;

/**
 * Interface for a Factory that creates Monster.
 */
public interface MonsterGenerator {

    //RATS:
    /**
     * @param pos of the Monster
     * @return a Melee Rat
     */
    Monster makeMeleeRat(Pair<Integer, Integer> pos);

    /**
     * @param pos of the Monster
     * @return a Ranged Rat
     */
    Monster makeRangedRat(Pair<Integer, Integer> pos);

    /**
     * @param pos of the Monster
     * @return a Mage Rat
     */
    Monster makeMageRat(Pair<Integer, Integer> pos);

    //OPOSSUMS:
    /**
     * @param pos of the Monster
     * @return a Rogue Oposssum
     */
    Monster makeRogueOpossum(Pair<Integer, Integer> pos);

    /**
     * @param pos of the Monster
     * @return a Ranged Opossum
     */
    Monster makeRangedOpossum(Pair<Integer, Integer> pos);

    //SEAGULLS:
    /**
     * @param pos of the Monster
     * @return a Melee Seagull
     */
    Monster makeMeleeSeagull(Pair<Integer, Integer> pos);

    /**
     * @param pos of the Monster
     * @return a Ranged Seagull
     */
    Monster makeRangedSeagull(Pair<Integer, Integer> pos);

    //COCKROACHES:
    /**
     * @param pos of the Monster
     * @return a Bomb Cockroach
     */
    Monster makeBombCockroach(Pair<Integer, Integer> pos);
}
