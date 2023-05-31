package it.unibo.ruscodc.model.actors.stat;

import it.unibo.ruscodc.model.actors.stat.StatImpl.StatName;
import it.unibo.ruscodc.utils.Pair;

public class StatFactoryImpl implements StatFactory {

    @Override
    public Stat ratStat() {
        Stat ratStats = new StatImpl();
        ratStats.setStat(StatName.HP, new Pair<Integer,Integer>(5, 5));
        ratStats.setStat(StatName.AP, new Pair<Integer,Integer>(3, 3));
        ratStats.setStat(StatName.DMG, new Pair<Integer,Integer>(2, 2));
        ratStats.setStat(StatName.STR, new Pair<Integer,Integer>(2, 2));
        ratStats.setStat(StatName.DEX, new Pair<Integer,Integer>(3, 3));
        ratStats.setStat(StatName.INT, new Pair<Integer,Integer>(0, 0));
        return ratStats;
    }

    @Override
    public Stat opossumStat() {
        Stat ratStats = new StatImpl();
        ratStats.setStat(StatName.HP, new Pair<Integer,Integer>(10, 10));
        ratStats.setStat(StatName.AP, new Pair<Integer,Integer>(7, 7));
        ratStats.setStat(StatName.DMG, new Pair<Integer,Integer>(4, 4));
        ratStats.setStat(StatName.STR, new Pair<Integer,Integer>(2, 2));
        ratStats.setStat(StatName.DEX, new Pair<Integer,Integer>(4, 4));
        ratStats.setStat(StatName.INT, new Pair<Integer,Integer>(1, 1));
        return ratStats;
    }

    @Override
    public Stat seagullStat() {
        Stat ratStats = new StatImpl();
        ratStats.setStat(StatName.HP, new Pair<Integer,Integer>(7, 7));
        ratStats.setStat(StatName.AP, new Pair<Integer,Integer>(4, 4));
        ratStats.setStat(StatName.DMG, new Pair<Integer,Integer>(3, 3));
        ratStats.setStat(StatName.STR, new Pair<Integer,Integer>(3, 3));
        ratStats.setStat(StatName.DEX, new Pair<Integer,Integer>(3, 3));
        ratStats.setStat(StatName.INT, new Pair<Integer,Integer>(0, 0));
        return ratStats;
    }

    @Override
    public Stat cockroachStat() {
        Stat ratStats = new StatImpl();
        ratStats.setStat(StatName.HP, new Pair<Integer,Integer>(2, 2));
        ratStats.setStat(StatName.AP, new Pair<Integer,Integer>(0, 0));
        ratStats.setStat(StatName.DMG, new Pair<Integer,Integer>(15, 15));
        ratStats.setStat(StatName.STR, new Pair<Integer,Integer>(0, 0));
        ratStats.setStat(StatName.DEX, new Pair<Integer,Integer>(3, 3));
        ratStats.setStat(StatName.INT, new Pair<Integer,Integer>(4, 4));
        return ratStats;
    }

    
    
}
