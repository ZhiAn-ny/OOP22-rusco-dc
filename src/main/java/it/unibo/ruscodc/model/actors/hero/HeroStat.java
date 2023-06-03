package it.unibo.ruscodc.model.actors.hero;

import it.unibo.ruscodc.model.actors.stat.StatImpl;
import it.unibo.ruscodc.utils.Pair;

/**
 * Class that creates Stat for the Hero
 */
public class HeroStat extends StatImpl {

    private static final int BASIC_HP = 25;
    private static final int BASIC_AP = 15;
    private static final int BASIC_DMG = 5;
    private static final int BASIC_STAT = 5;

    /**
     * Constructor for HeroStat
     */
    public HeroStat() {
        super();
        this.setStat(StatName.HP, new Pair<Integer, Integer>(BASIC_HP, BASIC_HP));
        this.setStat(StatName.AP, new Pair<Integer, Integer>(BASIC_AP, BASIC_AP));
        this.setStat(StatName.DMG, new Pair<Integer, Integer>(BASIC_DMG, BASIC_DMG));
        this.setStat(StatName.STR, new Pair<Integer, Integer>(BASIC_STAT, BASIC_STAT));
        this.setStat(StatName.DEX, new Pair<Integer, Integer>(BASIC_STAT, BASIC_STAT));
        this.setStat(StatName.INT, new Pair<Integer, Integer>(BASIC_STAT, BASIC_STAT));
    }
}
