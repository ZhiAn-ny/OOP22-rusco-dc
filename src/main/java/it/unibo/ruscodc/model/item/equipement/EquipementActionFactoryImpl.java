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
import it.unibo.ruscodc.model.range.Range;
import it.unibo.ruscodc.model.range.SingleRange;
import it.unibo.ruscodc.model.range.SingleSplash;
import it.unibo.ruscodc.model.range.SquareRange;

/**
 * Implementation of EquipementAction Factory that returns GameCommands for various Equipement.
 */
public class EquipementActionFactoryImpl implements EquipementActionFactory {

    private static final int BASIC_MELEE_COST = 0;
    private static final Range BASIC_MELEE_RANGE = new SquareRange(1, new SingleRange());
    private static final Range BASIC_MELEE_SPLASH = new SingleSplash();

    private static final int BASIC_RANGED_COST = 0;
    private static final Range BASIC_RANGED_RANGE = new GlobalRange(new SingleRange());
    private static final Range BASIC_RANGED_SPLASH = new SingleSplash();

    private static final int CONE_ATTACK_COST = 0;
    private static final Range CONE_ATTACK_RANGE = new CircleRange(1, new SingleRange());
    private static final Range CONE_ATTACK_SPLASH = new ConeRange(5, new SingleSplash());

    private static final int ROW_ATTACK_COST = 0;
    private static final Range ROW_ATTACK_RANGE = new CircleRange(3, new SingleRange());
    private static final Range ROW_ATTACK_SPLASH = new DirectRowRange(new SingleSplash());

    /**
     * 
     */
    @Override
    public GameCommand createBasicMelee() {
        return new PlayerAttack(
            BASIC_MELEE_RANGE,
            BASIC_MELEE_SPLASH,
            new EffectAbs(BASIC_MELEE_COST) {
                @Override
                public void applyEffect(final Actor from, final Actor to) {
                    final int damage = from.getStatActual(StatName.DMG) + from.getStatActual(StatName.STR);
                    to.modifyActualStat(StatName.HP, -damage);
                }

                @Override
                public String toString() {
                    return "A short range normal Melee Attack";
                }
            }
        );
    }

    /**
     * 
     */
    @Override
    public GameCommand createBasicRanged() {
        return new PlayerAttack(
            BASIC_RANGED_RANGE,
            BASIC_RANGED_SPLASH,
            new EffectAbs(BASIC_RANGED_COST) {
                @Override
                public void applyEffect(final Actor from, final Actor to) {
                    final int damage = from.getStatActual(StatName.DMG) + from.getStatActual(StatName.STR);
                    to.modifyActualStat(StatName.HP, -damage);
                }

                @Override
                public String toString() {
                    return "A long range attack";
                }
            }
        );
    }

    /**
     * 
     */
    @Override
    public GameCommand createConeAttack() {
        return new PlayerAttack(
            CONE_ATTACK_RANGE,
            CONE_ATTACK_SPLASH,
            new EffectAbs(CONE_ATTACK_COST) {
                @Override
                public void applyEffect(final Actor from, final Actor to) {
                    final int damage = from.getStatActual(StatName.DMG) + from.getStatActual(StatName.STR);
                    to.modifyActualStat(StatName.HP, -damage);
                }

                @Override
                public String toString() {
                    return "A cone shaped attack";
                }
            }
        );
    }

    /**
     * 
     */
    @Override
    public GameCommand createRowAttack() {
        return new PlayerAttack(
            ROW_ATTACK_RANGE,
            ROW_ATTACK_SPLASH,
            new EffectAbs(ROW_ATTACK_COST) {
                @Override
                public void applyEffect(final Actor from, final Actor to) {
                    final int damage = from.getStatActual(StatName.DMG) + from.getStatActual(StatName.STR);
                    to.modifyActualStat(StatName.HP, -damage);
                }

                @Override
                public String toString() {
                    return "A straight line attack";
                }
            }
        );
    }
}
