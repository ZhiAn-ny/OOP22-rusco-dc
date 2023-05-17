package it.unibo.ruscodc.model.actors.monster;

import it.unibo.ruscodc.model.actors.monster.behaviour.Behaviour;
import it.unibo.ruscodc.model.actors.monster.behaviour.BehaviourFactory;
import it.unibo.ruscodc.model.actors.monster.behaviour.BehaviourFactoryImpl;
import it.unibo.ruscodc.model.actors.skill.Skill;
import it.unibo.ruscodc.model.actors.skill.SkillImpl;
import it.unibo.ruscodc.model.actors.stat.Stat;
import it.unibo.ruscodc.model.actors.stat.StatFactory;
import it.unibo.ruscodc.model.actors.stat.StatFactoryImpl;
import it.unibo.ruscodc.utils.GameControl;
import it.unibo.ruscodc.utils.Pair;

public class MonsterGeneratorImpl implements MonsterGenerator {

    private final BehaviourFactory behaviourFactory = new BehaviourFactoryImpl();
    private final StatFactory statFactory = new StatFactoryImpl();
    private final MonsterActionFactory MAFactory = new MonsterActionFactoryImpl();

    @Override
    public Monster makeMeleeRat(String name, Pair<Integer, Integer> pos) {
        
        Skill skills = new SkillImpl();
        skills.setAction(GameControl.ATTACK1, this.MAFactory.basicMeleeAttack());
        skills.setAction(GameControl.ATTACK2, this.MAFactory.heavyMeleeAttack());

        Stat stats = this.statFactory.ratStat();

        Behaviour behaviour = this.behaviourFactory.makeMeleeAggressive();

        return new MonsterImpl(name, pos, skills, stats, behaviour);
    }

    @Override
    public Monster makeRangedRat(String name, Pair<Integer, Integer> pos) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'makeRangedRat'");
    }

}
