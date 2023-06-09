package it.unibo.ruscodc.model.actors.monster;

import it.unibo.ruscodc.model.actors.Actor;
import it.unibo.ruscodc.model.actors.stat.StatImpl.StatName;
import it.unibo.ruscodc.model.effect.EffectAbs;
import it.unibo.ruscodc.model.gamecommand.GameCommand;
import it.unibo.ruscodc.model.gamecommand.iacommand.IAAttack;
import it.unibo.ruscodc.model.range.CircleRange;
import it.unibo.ruscodc.model.range.GlobalRange;
import it.unibo.ruscodc.model.range.SingleRange;
import it.unibo.ruscodc.model.range.SingleSplash;
import it.unibo.ruscodc.model.range.SquareRange;

/**
 * Implementation of the Factory that creates the GameCommand for the Monsters skill.
 */
public class MonsterActionFactoryImpl implements MonsterActionFactory {

    /**
     * 
     */
    @Override
    public GameCommand basicMeleeAttack() {
        return new IAAttack(
            new SquareRange(1, new SingleRange()),
            new SingleSplash(),
            new EffectAbs(0) {
                @Override
                public void applyEffect(final Actor from, final Actor to) {
                    final int damage = from.getStatActual(StatName.DMG) + from.getStatActual(StatName.STR);
                    to.modifyActualStat(StatName.HP, -damage);
                }

                @Override
                public String toString() {
                    return "The Monster attacks with its fangs, claws or weapons";
                }
            }
        );
    }

    /**
     * 
     */
    @Override
    public GameCommand heavyMeleeAttack() {
        return new IAAttack(
            new SquareRange(1, new SingleRange()),
            new SingleSplash(),
            new EffectAbs(3) {
                @Override
                public void applyEffect(final Actor from, final Actor to) {
                    final int damage = from.getStatActual(StatName.DMG) + from.getStatActual(StatName.STR) * 2;
                    to.modifyActualStat(StatName.HP, -damage);
                }

                @Override
                public String toString() {
                    return "The Monster attacks with all its Strengh using fangs, claws or weapons";
                }
            }
        );
    }

    /**
     * 
     */
    @Override
    public GameCommand basicRangedAttack() {
        return new IAAttack(
            new GlobalRange(new SingleRange()),
            new SingleSplash(),
            new EffectAbs(0) {
                @Override
                public void applyEffect(final Actor from, final Actor to) {
                    final int damage = from.getStatActual(StatName.DMG);
                    to.modifyActualStat(StatName.HP, -damage);
                }

                @Override
                public String toString() {
                    return "The Monster use everything it can to attack from the discance";
                }
            }
        );
    }

    /**
     * 
     */
    @Override
    public GameCommand ratMagic() {
        return new IAAttack(
            new GlobalRange(new SingleRange()),
            new SingleSplash(),
            new EffectAbs(0) {
                @Override
                public void applyEffect(final Actor from, final Actor to) {
                    final int damage = from.getStatActual(StatName.DMG) + from.getStatActual(StatName.INT);
                    to.modifyActualStat(StatName.HP, -damage);
                }

                @Override
                public String toString() {
                    return "Some kind of nauseous arcane magic used by Rats";
                }
            }
        );
    }

    /**
     * 
     */
    @Override
    public GameCommand badSmell() {
        return new IAAttack(
            new GlobalRange(new SingleRange()),
            new CircleRange(2, new SingleSplash()),
            new EffectAbs(3) {
                @Override
                public void applyEffect(final Actor from, final Actor to) {
                    final int damage = from.getStatActual(StatName.DMG) + from.getStatActual(StatName.INT);
                    to.modifyActualStat(StatName.HP, -damage);
                }

                @Override
                public String toString() {
                    return "A nauseous stink that cause damage in a small damage";
                }
            }
        );
    }

    /**
     * 
     */
    @Override
    public GameCommand backstab() {
        return new IAAttack(
            new SquareRange(1, new SingleRange()),
            new SingleSplash(),
            new EffectAbs(4) {
                @Override
                public void applyEffect(final Actor from, final Actor to) {
                    final int damage = from.getStatActual(StatName.DMG) + from.getStatActual(StatName.DEX);
                    to.modifyActualStat(StatName.HP, -damage);
                }

                @Override
                public String toString() {
                    return "The Monster ambush the hero with a well placed attack in it's weakspot";
                }
            }
        );
    }

    /**
     * 
     */
    @Override
    public GameCommand disgustingDemise() {
        return new IAAttack(
            new SquareRange(1, new SingleSplash()),
            new SquareRange(1, new SingleSplash()),
            new EffectAbs(0) {
                @Override
                public void applyEffect(final Actor from, final Actor to) {
                    final int damage = from.getStatActual(StatName.DMG);
                    to.modifyActualStat(StatName.HP, -damage);
                }

                @Override
                public String toString() {
                    return "The Moster jumps and explodes dealing damage to everyone nearby";
                }
            }
        );
    }
}
