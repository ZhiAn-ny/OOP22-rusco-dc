package it.unibo.ruscodc.model.actors.hero;

import it.unibo.ruscodc.model.actors.Actor;
import it.unibo.ruscodc.model.actors.skill.SkillImpl;
import it.unibo.ruscodc.model.actors.stat.StatImpl.StatName;
import it.unibo.ruscodc.model.effect.EffectAbs;
import it.unibo.ruscodc.model.gamecommand.playercommand.OpenInventory;
import it.unibo.ruscodc.model.gamecommand.playercommand.PlayerAttack;
import it.unibo.ruscodc.model.gamecommand.quickcommand.SelfEffect;
import it.unibo.ruscodc.model.range.CircleRange;
import it.unibo.ruscodc.model.range.GlobalRange;
import it.unibo.ruscodc.model.range.SingleRange;
import it.unibo.ruscodc.model.range.SingleSplash;
import it.unibo.ruscodc.model.range.SquareRange;
import it.unibo.ruscodc.utils.GameControl;

public class HeroSkill extends SkillImpl {
    
    public HeroSkill(){
        super();

        super.setAction(
            GameControl.BASEATTACK,
            new PlayerAttack(
                new SquareRange(1, new SingleRange()),
                new SingleSplash(),
                new EffectAbs(0) {
                    @Override
                    public void applyEffect(Actor from, Actor to) {
                        int damage = from.getStatActual(StatName.DMG);
                        to.modifyActualStat(StatName.HP, - damage);
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
                new SquareRange(1, new SingleRange()),
                new SingleSplash(),
                new EffectAbs(3) {
                    @Override
                    public void applyEffect(Actor from, Actor to) {
                        int damage = from.getStatActual(StatName.DMG) + from.getStatActual(StatName.STR);
                        to.modifyActualStat(StatName.HP, - damage);
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
                new GlobalRange(new SingleRange()),
                new SingleSplash(),
                new EffectAbs(0) {
                    @Override
                    public void applyEffect(Actor from, Actor to) {
                        int damage = from.getStatActual(StatName.DEX);
                        to.modifyActualStat(StatName.HP, - damage);
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
                a -> a.modifyActualStat(StatName.HP, a.getStatMax(StatName.HP) * 20 / 100),
                5
            )
        );

        super.setAction(
            GameControl.ATTACK4,
            new PlayerAttack(
                new GlobalRange( new SingleRange()),
                new CircleRange(2, new SingleSplash()),
                new EffectAbs(8) {
                    @Override
                    public void applyEffect(Actor from, Actor to) {
                        int damage = from.getStatActual(StatName.DMG) + from.getStatActual(StatName.STR);
                        to.modifyActualStat(StatName.HP, - damage);
                    }

                    @Override
                    public String toString() {
                        return "The Hero toss a handmade bomb that deals damage in a big area";
                    }
                }
            )
        );

        super.setAction(GameControl.INVENTORY, new OpenInventory());
    }
}
