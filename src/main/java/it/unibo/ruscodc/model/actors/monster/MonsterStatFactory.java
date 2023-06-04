package it.unibo.ruscodc.model.actors.monster;

import it.unibo.ruscodc.model.actors.stat.Stat;

/**
 * Interface for a Factory that creates Stats for the Monsters.
 */
public interface MonsterStatFactory {

    /**
     * @return the Stats for a Rat
     */
    Stat ratStat();

    /**
     * @return the Stats for an Opossum
     */
    Stat opossumStat();

    /**
     * @return the Stats for a Seacgull
     */
    Stat seagullStat();

    /**
     * @return the Stats for a Cockroach
     */
    Stat cockroachStat();
}
