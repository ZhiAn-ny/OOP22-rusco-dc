package it.unibo.ruscodc.model.actors.monster;

import it.unibo.ruscodc.model.actors.stat.Stat;
import it.unibo.ruscodc.model.actors.stat.StatImpl;
import it.unibo.ruscodc.model.actors.stat.StatImpl.StatName;
import it.unibo.ruscodc.utils.Pair;

/**
 * The implementation of the Stat Factory interface that for each method returns the specific Stat.
 */
public class MonsterStatFactoryImpl implements MonsterStatFactory {

    private static final int NONE = 0;
    private static final int LOW = 1;
    private static final int MEDIUM = 3;
    private static final int HIGH = 5;

    private static final int RAT_HP = 5;
    private static final int RAT_AP = 3;
    private static final int RAT_DMG = 2;

    private static final int OPOSSUM_HP = 10;
    private static final int OPOSSUM_AP = 7;
    private static final int OPOSSUM_DMG = 4;

    private static final int SEAGULL_HP = 7;
    private static final int SEAGULL_AP = 4;
    private static final int SEAGULL_DMG = 3;

    private static final int COCKROACH_HP = 2;
    private static final int COCKROACH_AP = 0;
    private static final int COCKROACH_DMG = 15;

    /**
     * 
     */
    @Override
    public Stat ratStat() {
        Stat ratStats = new StatImpl();
        ratStats.setStat(StatName.HP, new Pair<Integer, Integer>(RAT_HP, RAT_HP));
        ratStats.setStat(StatName.AP, new Pair<Integer, Integer>(RAT_AP, RAT_AP));
        ratStats.setStat(StatName.DMG, new Pair<Integer, Integer>(RAT_DMG, RAT_DMG));
        ratStats.setStat(StatName.STR, new Pair<Integer, Integer>(LOW, LOW));
        ratStats.setStat(StatName.DEX, new Pair<Integer, Integer>(MEDIUM, MEDIUM));
        ratStats.setStat(StatName.INT, new Pair<Integer, Integer>(NONE, NONE));
        return ratStats;
    }

    /**
     * 
     */
    @Override
    public Stat opossumStat() {
        Stat ratStats = new StatImpl();
        ratStats.setStat(StatName.HP, new Pair<Integer, Integer>(OPOSSUM_HP, OPOSSUM_HP));
        ratStats.setStat(StatName.AP, new Pair<Integer, Integer>(OPOSSUM_AP, OPOSSUM_AP));
        ratStats.setStat(StatName.DMG, new Pair<Integer, Integer>(OPOSSUM_DMG, OPOSSUM_DMG));
        ratStats.setStat(StatName.STR, new Pair<Integer, Integer>(MEDIUM, MEDIUM));
        ratStats.setStat(StatName.DEX, new Pair<Integer, Integer>(HIGH, HIGH));
        ratStats.setStat(StatName.INT, new Pair<Integer, Integer>(LOW, LOW));
        return ratStats;
    }

    /**
     * 
     */
    @Override
    public Stat seagullStat() {
        Stat ratStats = new StatImpl();
        ratStats.setStat(StatName.HP, new Pair<Integer, Integer>(SEAGULL_HP, SEAGULL_HP));
        ratStats.setStat(StatName.AP, new Pair<Integer, Integer>(SEAGULL_AP, SEAGULL_AP));
        ratStats.setStat(StatName.DMG, new Pair<Integer, Integer>(SEAGULL_DMG, SEAGULL_DMG));
        ratStats.setStat(StatName.STR, new Pair<Integer, Integer>(MEDIUM, MEDIUM));
        ratStats.setStat(StatName.DEX, new Pair<Integer, Integer>(MEDIUM, MEDIUM));
        ratStats.setStat(StatName.INT, new Pair<Integer, Integer>(NONE, NONE));
        return ratStats;
    }

    /**
     * 
     */
    @Override
    public Stat cockroachStat() {
        Stat ratStats = new StatImpl();
        ratStats.setStat(StatName.HP, new Pair<Integer, Integer>(COCKROACH_HP, COCKROACH_HP));
        ratStats.setStat(StatName.AP, new Pair<Integer, Integer>(COCKROACH_AP, COCKROACH_AP));
        ratStats.setStat(StatName.DMG, new Pair<Integer, Integer>(COCKROACH_DMG, COCKROACH_DMG));
        ratStats.setStat(StatName.STR, new Pair<Integer, Integer>(NONE, NONE));
        ratStats.setStat(StatName.DEX, new Pair<Integer, Integer>(NONE, NONE));
        ratStats.setStat(StatName.INT, new Pair<Integer, Integer>(NONE, NONE));
        return ratStats;
    }
}
