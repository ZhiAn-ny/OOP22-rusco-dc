package it.unibo.ruscodc.model.item.equipement;

import it.unibo.ruscodc.model.actors.Actor;
import it.unibo.ruscodc.model.actors.stat.StatImpl.StatName;
import it.unibo.ruscodc.model.effect.EffectAbs;
import it.unibo.ruscodc.model.gamecommand.GameCommand;
import it.unibo.ruscodc.model.gamecommand.playercommand.PlayerAttack;
import it.unibo.ruscodc.model.range.CircleRange;
import it.unibo.ruscodc.model.range.ConeRange;
import it.unibo.ruscodc.model.range.DirectRowRange;
import it.unibo.ruscodc.model.range.GlobalRange;
import it.unibo.ruscodc.model.range.SingleRange;
import it.unibo.ruscodc.model.range.SingleSplash;
import it.unibo.ruscodc.model.range.SquareRange;

public class EquipementActionFactoryImpl implements EquipementActionFactory {

    @Override
    public GameCommand createBasicMelee() {
        return new PlayerAttack(
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
                    return "A short range normal Melee Attack";
                }
            }
        );
    }

    @Override
    public GameCommand createBasicRanged() {
        return new PlayerAttack(
            new GlobalRange(new SingleRange()),
            new SingleSplash(),
            new EffectAbs(0) {
                @Override
                public void applyEffect(Actor from, Actor to) {
                    int damage = from.getStatActual(StatName.DMG) + from.getStatActual(StatName.STR);
                    to.modifyActualStat(StatName.HP, - damage);
                }

                @Override
                public String toString() {
                    return "A long range attack";
                }
            }
        );
    }

    @Override
    public GameCommand createConeAttack() {
        return new PlayerAttack(
            new CircleRange(1, new SingleRange()),
            new ConeRange(5, new SingleSplash()),
            new EffectAbs(0) {
                @Override
                public void applyEffect(Actor from, Actor to) {
                    int damage = from.getStatActual(StatName.DMG) + from.getStatActual(StatName.STR);
                    to.modifyActualStat(StatName.HP, - damage);
                }

                @Override
                public String toString() {
                    return "A cone shaped attack";
                }
            }
        );
    }

    @Override
    public GameCommand createRowAttack() {
        return new PlayerAttack(
            new CircleRange(1, new SingleRange()),
            new DirectRowRange(new SingleSplash()),
            new EffectAbs(0) {
                @Override
                public void applyEffect(Actor from, Actor to) {
                    int damage = from.getStatActual(StatName.DMG) + from.getStatActual(StatName.STR);
                    to.modifyActualStat(StatName.HP, - damage);
                }

                @Override
                public String toString() {
                    return "A straight line attack";
                }
            }    
        );
    }
    
}
