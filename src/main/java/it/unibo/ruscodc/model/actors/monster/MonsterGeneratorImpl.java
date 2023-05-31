package it.unibo.ruscodc.model.actors.monster;

import it.unibo.ruscodc.model.actors.monster.behaviour.Behaviour;
import it.unibo.ruscodc.model.actors.monster.behaviour.BehaviourFactory;
import it.unibo.ruscodc.model.actors.monster.behaviour.BehaviourFactoryImpl;
import it.unibo.ruscodc.model.actors.skill.Skill;
import it.unibo.ruscodc.model.actors.skill.SkillImpl;
import it.unibo.ruscodc.model.actors.stat.Stat;
import it.unibo.ruscodc.model.actors.stat.StatFactory;
import it.unibo.ruscodc.model.actors.stat.StatFactoryImpl;
import it.unibo.ruscodc.model.actors.stat.StatImpl.StatName;
import it.unibo.ruscodc.utils.GameControl;
import it.unibo.ruscodc.utils.Pair;

public class MonsterGeneratorImpl implements MonsterGenerator {

    private final BehaviourFactory behaviourFactory = new BehaviourFactoryImpl();
    private final StatFactory statFactory = new StatFactoryImpl();
    private final MonsterActionFactory MAFactory = new MonsterActionFactoryImpl();

    @Override
    public Monster makeMeleeRat(Pair<Integer, Integer> pos) {
        
        Skill skills = new SkillImpl();
        skills.setAction(GameControl.BASEATTACK, this.MAFactory.basicMeleeAttack());
        skills.setAction(GameControl.ATTACK1, this.MAFactory.heavyMeleeAttack());

        Stat stats = this.statFactory.ratStat();

        Behaviour behaviour = this.behaviourFactory.makeMeleeAggressive();

        return new MonsterImpl("MeleeRat", pos, skills, stats, behaviour);
    }

    @Override
    public Monster makeRangedRat(Pair<Integer, Integer> pos) {
        Skill skills = new SkillImpl();
        skills.setAction(GameControl.BASEATTACK, this.MAFactory.basicRangedAttack());

        Stat stats = this.statFactory.ratStat();

        Behaviour behaviour = this.behaviourFactory.makeRangedBrainless();

        return new MonsterImpl("RangedRat", pos, skills, stats, behaviour);
    }

    @Override
    public Monster makeMageRat(Pair<Integer, Integer> pos) {
        Skill skills = new SkillImpl();
        skills.setAction(GameControl.BASEATTACK, this.MAFactory.ratMagic());
        skills.setAction(GameControl.ATTACK1, this.MAFactory.badSmell());

        Stat stats = this.statFactory.ratStat();
        stats.setStat(StatName.INT, new Pair<Integer,Integer>(5, 5));

        Behaviour behaviour = this.behaviourFactory.makeRangedShy();

        return new MonsterImpl("MagedRat", pos, skills, stats, behaviour);
    }

    @Override
    public Monster makeRogueOpossum(Pair<Integer, Integer> pos) {
        Skill skills = new SkillImpl();
        skills.setAction(GameControl.BASEATTACK, this.MAFactory.basicMeleeAttack());
        skills.setAction(GameControl.ATTACK1, this.MAFactory.heavyMeleeAttack());
        skills.setAction(GameControl.ATTACK2, this.MAFactory.backstab());

        Stat stats = this.statFactory.opossumStat();

        Behaviour behaviour = this.behaviourFactory.makeMeleeAggressive();

        return new MonsterImpl("RogueOpossum", pos, skills, stats, behaviour);
    }

    @Override
    public Monster makeRangedOpossum(Pair<Integer, Integer> pos) {
        Skill skills = new SkillImpl();
        skills.setAction(GameControl.BASEATTACK, this.MAFactory.basicRangedAttack());

        Stat stats = this.statFactory.opossumStat();

        Behaviour behaviour = this.behaviourFactory.makeRangedShy();

        return new MonsterImpl("RangedOpossum", pos, skills, stats, behaviour);
    }

    @Override
    public Monster makeMeleeSeagull(Pair<Integer, Integer> pos) {
        Skill skills = new SkillImpl();
        skills.setAction(GameControl.BASEATTACK, this.MAFactory.basicMeleeAttack());
        skills.setAction(GameControl.ATTACK1, this.MAFactory.backstab());

        Stat stats = this.statFactory.seagullStat();

        Behaviour behaviour = this.behaviourFactory.makeMeleeAggressive();

        return new MonsterImpl("MeleeSeagull", pos, skills, stats, behaviour);
    }

    @Override
    public Monster makeRangedSeagull(Pair<Integer, Integer> pos) {
        Skill skills = new SkillImpl();
        skills.setAction(GameControl.BASEATTACK, this.MAFactory.basicRangedAttack());

        Stat stats = this.statFactory.seagullStat();

        Behaviour behaviour = this.behaviourFactory.makeRangedShy();

        return new MonsterImpl("RangedSeagull", pos, skills, stats, behaviour);
    }

    @Override
    public Monster makeBombCockroach(Pair<Integer, Integer> pos) {
        Skill skills = new SkillImpl();
        skills.setAction(GameControl.BASEATTACK, this.MAFactory.disgustingDemise());

        Stat stats = this.statFactory.cockroachStat();

        Behaviour behaviour = this.behaviourFactory.makeMeleeBrainless();

        return new MonsterImpl("BombCockroach", pos, skills, stats, behaviour);
    }
}
