package it.unibo.ruscodc.model.actors;

import it.unibo.ruscodc.model.actors.StatImpl.StatName;
import it.unibo.ruscodc.model.gamecommand.GameCommand;
import it.unibo.ruscodc.model.gamecommand.iacommand.IAAttack;
import it.unibo.ruscodc.model.range.CircleRange;
import it.unibo.ruscodc.model.range.SingleRange;
import it.unibo.ruscodc.model.range.SingleSplash;

public class MonsterActionFactoryImpl implements MonsterActionFactory {

    @Override
    public GameCommand basicMeleeAttack() {
        return new IAAttack(
            new CircleRange(1, new SingleRange()),
            new SingleSplash(),
            (from, to) -> {
                int damage = from.getStatInfo(StatName.DMG);
                to.modifyStat(StatName.HP, - damage);
            });
    }

    @Override
    public GameCommand heavyMeleeAttack() {
        return new IAAttack(
            new CircleRange(1, new SingleRange()),
            new SingleSplash(),
            (from, to) -> {
                int damage = from.getStatInfo(StatName.DMG) + from.getStatInfo(StatName.STR)*2;
                to.modifyStat(StatName.HP, - damage);
            });
    }
    
}
