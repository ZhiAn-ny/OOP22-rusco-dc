package it.unibo.ruscodc.model.actors.monster;

import it.unibo.ruscodc.model.actors.Skill;
import it.unibo.ruscodc.model.actors.SkillImpl;
import it.unibo.ruscodc.model.actors.Stat;
import it.unibo.ruscodc.model.actors.StatImpl;
import it.unibo.ruscodc.model.actors.StatImpl.StatName;
import it.unibo.ruscodc.model.actors.monster.behaviour.Behaviour;
import it.unibo.ruscodc.model.actors.monster.behaviour.BehaviourFactory;
import it.unibo.ruscodc.model.actors.monster.behaviour.BehaviourFactoryImpl;
import it.unibo.ruscodc.model.gamecommand.iacommand.IAAttack;
import it.unibo.ruscodc.model.range.CircleRange;
import it.unibo.ruscodc.model.range.SingleRange;
import it.unibo.ruscodc.model.range.SingleSplash;
import it.unibo.ruscodc.utils.GameControl;
import it.unibo.ruscodc.utils.Pair;

public class MonsterGeneratorImpl implements MonsterGenerator {

    private final BehaviourFactory behaviourFactory = new BehaviourFactoryImpl();

    @Override
    public Monster makeMeleeRat(String name, Pair<Integer, Integer> pos) {
        
        Skill skills = new SkillImpl();
        skills.setAction(
            GameControl.ATTACK1,
            new IAAttack(
                new CircleRange(1, new SingleRange()),
                new SingleSplash(),
                (from, to) -> {
                    int damage = from.getStatInfo(StatName.DMG);
                    from.modifyStat(StatName.HP, - damage);
                } )
        );
        skills.setAction(
            GameControl.ATTACK2,
            new IAAttack(
                new CircleRange(1, new SingleRange()),
                new SingleSplash(),
                (from, to) -> {
                    int damage = from.getStatInfo(StatName.DMG) + from.getStatInfo(StatName.STR)*2;
                    from.modifyStat(StatName.HP, - damage);
                } )
        );

        Stat stats = new StatImpl();
        stats.setStat(StatName.HP, new Pair<Integer,Integer>(5, 5));
        stats.setStat(StatName.AP, new Pair<Integer,Integer>(3, 3));
        stats.setStat(StatName.DMG, new Pair<Integer,Integer>(2, 2));
        stats.setStat(StatName.STR, new Pair<Integer,Integer>(2, 2));
        stats.setStat(StatName.DEX, new Pair<Integer,Integer>(3, 3));
        stats.setStat(StatName.INT, new Pair<Integer,Integer>(0, 0));

        Behaviour behaviour = this.behaviourFactory.makeMeleeAggressive();

        return new MonsterImpl(name, pos, skills, stats, behaviour);
    }

    @Override
    public Monster makeRangedRat(String name, Pair<Integer, Integer> pos) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'makeRangedRat'");
    }

}
