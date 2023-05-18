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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'opossumStat'");
    }

    @Override
    public Stat seagullStat() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'seagullStat'");
    }
    
}
