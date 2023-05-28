package it.unibo.ruscodc.model.actors.monster;

import it.unibo.ruscodc.model.actors.Actor;
import it.unibo.ruscodc.model.actors.stat.StatImpl.StatName;
import it.unibo.ruscodc.model.effect.EffectAbs;
import it.unibo.ruscodc.model.gamecommand.GameCommand;
import it.unibo.ruscodc.model.gamecommand.iacommand.IAAttack;
import it.unibo.ruscodc.model.range.SingleRange;
import it.unibo.ruscodc.model.range.SingleSplash;
import it.unibo.ruscodc.model.range.SquareRange;

public class MonsterActionFactoryImpl implements MonsterActionFactory {

    @Override
    public GameCommand basicMeleeAttack() {
        return new IAAttack(
            new SquareRange(1, new SingleRange()),
            new SingleSplash(),
            new EffectAbs(0) {
                @Override
                public void applyEffect(Actor from, Actor to) {
                    int damage = from.getStatActual(StatName.DMG) + from.getStatActual(StatName.STR);
                    to.modifyActualStat(StatName.HP, - damage);
                }

                @Override
                public String toString() {
                    return "The Monster attacks with his fangs, claws or weapons";
                }
            }
        );
    }

    @Override
    public GameCommand heavyMeleeAttack() {
        return new IAAttack(
            new SquareRange(1, new SingleRange()),
            new SingleSplash(),
            new EffectAbs(3) {
                @Override
                public void applyEffect(Actor from, Actor to) {
                    int damage = from.getStatActual(StatName.DMG) + from.getStatActual(StatName.STR)*2;
                    to.modifyActualStat(StatName.HP, - damage);
                }

                @Override
                public String toString() {
                    return "The Monster attacks with all his Strengh using fangs, claws or weapons";
                }
            }
        );
    }
    
}
