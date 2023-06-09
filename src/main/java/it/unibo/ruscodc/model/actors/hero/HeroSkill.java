package it.unibo.ruscodc.model.actors.hero;

import it.unibo.ruscodc.model.actors.Actor;
import it.unibo.ruscodc.model.actors.skill.SkillImpl;
import it.unibo.ruscodc.model.actors.stat.StatImpl.StatName;
import it.unibo.ruscodc.model.effect.EffectAbs;
import it.unibo.ruscodc.model.effect.SingleTargetEffect;
import it.unibo.ruscodc.model.gamecommand.playercommand.Interact;
import it.unibo.ruscodc.model.gamecommand.playercommand.OpenInventory;
import it.unibo.ruscodc.model.gamecommand.playercommand.PlayerAttack;
import it.unibo.ruscodc.model.gamecommand.quickcommand.DoNothing;
import it.unibo.ruscodc.model.gamecommand.quickcommand.SelfEffect;
import it.unibo.ruscodc.model.range.CircleRange;
import it.unibo.ruscodc.model.range.GlobalRange;
import it.unibo.ruscodc.model.range.Range;
import it.unibo.ruscodc.model.range.SingleRange;
import it.unibo.ruscodc.model.range.SingleSplash;
import it.unibo.ruscodc.model.range.SquareRange;
import it.unibo.ruscodc.utils.GameControl;

/**
 * Custom extension of SkillImpl used to create the Hero skill set.
 */
public class HeroSkill extends SkillImpl {
    private static final int BASEATTACK_AP = 0;
    private static final Range BASEATTACK_RANGE = new SquareRange(1, new SingleRange());
    private static final Range BASEATTACK_SPLASH = new SingleSplash();

    private static final int ATTACK1_AP = 3;
    private static final Range ATTACK1_RANGE = new SquareRange(1, new SingleRange());
    private static final Range ATTACK1_SPLASH = new SingleSplash();

    private static final int ATTACK2_AP = 1;
    private static final Range ATTACK2_RANGE = new GlobalRange(new SingleRange());
    private static final Range ATTACK2_SPLASH = new SingleSplash();

    private static final int ATTACK3_AP = 5;
    private static final double ATTACK3_PERCENT = 20.0 / 100.0;

    private static final int ATTACK4_AP = 10;
    private static final Range ATTACK4_RANGE = new GlobalRange(new SingleRange());
    private static final Range ATTACK4_SPLASH = new CircleRange(2, new SingleSplash());

    /**
     * Custom constructor that instaciate the Hero skills.
     */
    public HeroSkill() {
        super();

        super.setAction(
            GameControl.BASEATTACK,
            new PlayerAttack(
                BASEATTACK_RANGE,
                BASEATTACK_SPLASH,
                new EffectAbs(BASEATTACK_AP) {
                    @Override
                    public void applyEffect(final Actor from, final Actor to) {
                        final int damage = from.getStatActual(StatName.DMG);
                        to.modifyActualStat(StatName.HP, -damage);
                    }

                    @Override
                    public String toString() {
                        return "Hero default attack";
                    }
                }
            )
        );

        super.setAction(
            GameControl.ATTACK1,
            new PlayerAttack(
                ATTACK1_RANGE,
                ATTACK1_SPLASH,
                new EffectAbs(ATTACK1_AP) {
                    @Override
                    public void applyEffect(final Actor from, final Actor to) {
                        final int damage = from.getStatActual(StatName.DMG) + from.getStatActual(StatName.STR);
                        to.modifyActualStat(StatName.HP, -damage);
                    }

                    @Override
                    public String toString() {
                        return "Hero Heavy melee attack";
                    }
                }
            )
        );

        super.setAction(
            GameControl.ATTACK2,
            new PlayerAttack(
                ATTACK2_RANGE,
                ATTACK2_SPLASH,
                new EffectAbs(ATTACK2_AP) {
                    @Override
                    public void applyEffect(final Actor from, final Actor to) {
                        final int damage = from.getStatActual(StatName.DEX);
                        to.modifyActualStat(StatName.HP, -damage);
                    }

                    @Override
                    public String toString() {
                        return "The Hero throws some piece of junk to attack from distance";
                    }
                }
            )
        );

        super.setAction(
            GameControl.ATTACK3,
            new SelfEffect(
                new SingleTargetEffect() {

                    @Override
                    public void applyEffect(final Actor target) {
                        target.modifyActualStat(
                            StatName.HP,
                            (int) (target.getStatMax(StatName.HP) * ATTACK3_PERCENT)
                        );
                    }

                    @Override
                    public String toString() {
                        return "The Hero take some time to patch his wounds";
                    }
                },
                ATTACK3_AP
            )
        );

        super.setAction(
            GameControl.ATTACK4,
            new PlayerAttack(
                ATTACK4_RANGE,
                ATTACK4_SPLASH,
                new EffectAbs(ATTACK4_AP) {
                    @Override
                    public void applyEffect(final Actor from, final Actor to) {
                        final int damage = from.getStatActual(StatName.DMG) + from.getStatActual(StatName.STR);
                        to.modifyActualStat(StatName.HP, -damage);
                    }

                    @Override
                    public String toString() {
                        return "The Hero toss a handmade bomb that deals damage in a big area";
                    }
                }
            )
        );

        super.setAction(GameControl.INVENTORY, new OpenInventory());
        super.setAction(GameControl.INTERACT, new Interact());
        super.setAction(GameControl.DONOTHING, new DoNothing());
    }
}
