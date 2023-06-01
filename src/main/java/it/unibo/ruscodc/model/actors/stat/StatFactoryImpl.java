package it.unibo.ruscodc.model.actors.stat;

import it.unibo.ruscodc.model.actors.stat.StatImpl.StatName;
import it.unibo.ruscodc.utils.Pair;

/**
 * The implementation of the Stat Factory interface that for each method returns the specific Stat.
 */
public class StatFactoryImpl implements StatFactory {

    private static int none = 0;
    private static int low = 1;
    private static int medium = 3;
    private static int high = 5;
    private static int max = 1;

    private static int ratHP = 5;
    private static int ratAP = 3;
    private static int ratDMG = 2;

    private static int opossumHP = 10;
    private static int opossumAP = 7;
    private static int opossumDMG = 4;

    private static int seagullHP = 7;
    private static int seagullAP = 4;
    private static int seagullDMG = 3;

    private static int cockroachHP = 2;
    private static int cockroachAP = 0;
    private static int cockroachDMG = 15;

    @Override
    public Stat ratStat() {
        Stat ratStats = new StatImpl();
        ratStats.setStat(StatName.HP, new Pair<Integer, Integer>(ratHP, ratHP));
        ratStats.setStat(StatName.AP, new Pair<Integer, Integer>(ratAP, ratAP));
        ratStats.setStat(StatName.DMG, new Pair<Integer, Integer>(ratDMG, ratDMG));
        ratStats.setStat(StatName.STR, new Pair<Integer, Integer>(low, low));
        ratStats.setStat(StatName.DEX, new Pair<Integer, Integer>(medium, medium));
        ratStats.setStat(StatName.INT, new Pair<Integer, Integer>(none, none));
        return ratStats;
    }

    @Override
    public Stat opossumStat() {
        Stat ratStats = new StatImpl();
        ratStats.setStat(StatName.HP, new Pair<Integer,Integer>(opossumHP, opossumHP));
        ratStats.setStat(StatName.AP, new Pair<Integer,Integer>(opossumAP,opossumAP));
        ratStats.setStat(StatName.DMG, new Pair<Integer,Integer>(opossumDMG, opossumDMG));
        ratStats.setStat(StatName.STR, new Pair<Integer,Integer>(medium, medium));
        ratStats.setStat(StatName.DEX, new Pair<Integer,Integer>(high, high));
        ratStats.setStat(StatName.INT, new Pair<Integer,Integer>(low, low));
        return ratStats;
    }

    @Override
    public Stat seagullStat() {
        Stat ratStats = new StatImpl();
        ratStats.setStat(StatName.HP, new Pair<Integer,Integer>(seagullHP, seagullHP));
        ratStats.setStat(StatName.AP, new Pair<Integer,Integer>(seagullAP, seagullAP));
        ratStats.setStat(StatName.DMG, new Pair<Integer,Integer>(seagullDMG, seagullDMG));
        ratStats.setStat(StatName.STR, new Pair<Integer,Integer>(medium, medium));
        ratStats.setStat(StatName.DEX, new Pair<Integer,Integer>(medium, medium));
        ratStats.setStat(StatName.INT, new Pair<Integer,Integer>(none, none));
        return ratStats;
    }

    @Override
    public Stat cockroachStat() {
        Stat ratStats = new StatImpl();
        ratStats.setStat(StatName.HP, new Pair<Integer,Integer>(cockroachHP, cockroachHP));
        ratStats.setStat(StatName.AP, new Pair<Integer,Integer>(cockroachAP, cockroachAP));
        ratStats.setStat(StatName.DMG, new Pair<Integer,Integer>(cockroachDMG, cockroachDMG));
        ratStats.setStat(StatName.STR, new Pair<Integer,Integer>(none, none));
        ratStats.setStat(StatName.DEX, new Pair<Integer,Integer>(none, none));
        ratStats.setStat(StatName.INT, new Pair<Integer,Integer>(none, none));
        return ratStats;
    }

    
    
}
