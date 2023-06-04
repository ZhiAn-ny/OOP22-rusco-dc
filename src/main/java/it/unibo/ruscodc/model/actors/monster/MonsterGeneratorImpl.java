package it.unibo.ruscodc.model.actors.monster;

import it.unibo.ruscodc.model.actors.monster.behaviour.Behaviour;
import it.unibo.ruscodc.model.actors.monster.behaviour.BehaviourFactory;
import it.unibo.ruscodc.model.actors.monster.behaviour.BehaviourFactoryImpl;
import it.unibo.ruscodc.model.actors.skill.Skill;
import it.unibo.ruscodc.model.actors.skill.SkillImpl;
import it.unibo.ruscodc.model.actors.stat.Stat;
import it.unibo.ruscodc.model.actors.stat.StatImpl.StatName;
import it.unibo.ruscodc.model.gamecommand.quickcommand.DoNothing;
import it.unibo.ruscodc.utils.GameControl;
import it.unibo.ruscodc.utils.Pair;

/**
 * Implementation of MosterGenerator that creates different type of Monsters.
 */
public class MonsterGeneratorImpl implements MonsterGenerator {

    private final BehaviourFactory behaviourFactory = new BehaviourFactoryImpl();
    private final MonsterStatFactory statFactory = new MonsterStatFactoryImpl();
    private final MonsterActionFactory maFactory = new MonsterActionFactoryImpl();

    private static final int MAGE_INTELLECT_BONUS = 5;

    /**
     * 
     */
    @Override
    public Monster makeMeleeRat(final Pair<Integer, Integer> pos) {

        Skill skills = new SkillImpl();
        skills.setAction(GameControl.BASEATTACK, this.maFactory.basicMeleeAttack());
        skills.setAction(GameControl.ATTACK1, this.maFactory.heavyMeleeAttack());
        skills.setAction(GameControl.DONOTHING, new DoNothing());

        Stat stats = this.statFactory.ratStat();

        Behaviour behaviour = this.behaviourFactory.makeMeleeAggressive();

        return new MonsterImpl("MeleeRat", pos, skills, stats, behaviour);
    }

    /**
     * 
     */
    @Override
    public Monster makeRangedRat(final Pair<Integer, Integer> pos) {
        Skill skills = new SkillImpl();
        skills.setAction(GameControl.BASEATTACK, this.maFactory.basicRangedAttack());
        skills.setAction(GameControl.DONOTHING, new DoNothing());

        Stat stats = this.statFactory.ratStat();

        Behaviour behaviour = this.behaviourFactory.makeRangedBrainless();

        return new MonsterImpl("RangedRat", pos, skills, stats, behaviour);
    }

    /**
     * 
     */
    @Override
    public Monster makeMageRat(final Pair<Integer, Integer> pos) {
        Skill skills = new SkillImpl();
        skills.setAction(GameControl.BASEATTACK, this.maFactory.ratMagic());
        skills.setAction(GameControl.ATTACK1, this.maFactory.badSmell());
        skills.setAction(GameControl.DONOTHING, new DoNothing());

        Stat stats = this.statFactory.ratStat();
        stats.setStat(
            StatName.INT,
            new Pair<Integer, Integer>(MAGE_INTELLECT_BONUS, MAGE_INTELLECT_BONUS)
        );

        Behaviour behaviour = this.behaviourFactory.makeRangedShy();

        return new MonsterImpl("MageRat", pos, skills, stats, behaviour);
    }

    /**
     * 
     */
    @Override
    public Monster makeRogueOpossum(final Pair<Integer, Integer> pos) {
        Skill skills = new SkillImpl();
        skills.setAction(GameControl.BASEATTACK, this.maFactory.basicMeleeAttack());
        skills.setAction(GameControl.ATTACK1, this.maFactory.heavyMeleeAttack());
        skills.setAction(GameControl.ATTACK2, this.maFactory.backstab());
        skills.setAction(GameControl.DONOTHING, new DoNothing());

        Stat stats = this.statFactory.opossumStat();

        Behaviour behaviour = this.behaviourFactory.makeMeleeAggressive();

        return new MonsterImpl("RogueOpossum", pos, skills, stats, behaviour);
    }

    /**
     * 
     */
    @Override
    public Monster makeRangedOpossum(final Pair<Integer, Integer> pos) {
        Skill skills = new SkillImpl();
        skills.setAction(GameControl.BASEATTACK, this.maFactory.basicRangedAttack());
        skills.setAction(GameControl.DONOTHING, new DoNothing());

        Stat stats = this.statFactory.opossumStat();

        Behaviour behaviour = this.behaviourFactory.makeRangedShy();

        return new MonsterImpl("RangedOpossum", pos, skills, stats, behaviour);
    }

    /**
     * 
     */
    @Override
    public Monster makeMeleeSeagull(final Pair<Integer, Integer> pos) {
        Skill skills = new SkillImpl();
        skills.setAction(GameControl.BASEATTACK, this.maFactory.basicMeleeAttack());
        skills.setAction(GameControl.ATTACK1, this.maFactory.backstab());
        skills.setAction(GameControl.DONOTHING, new DoNothing());

        Stat stats = this.statFactory.seagullStat();

        Behaviour behaviour = this.behaviourFactory.makeMeleeAggressive();

        return new MonsterImpl("MeleeSeagull", pos, skills, stats, behaviour);
    }

    /**
     * 
     */
    @Override
    public Monster makeRangedSeagull(final Pair<Integer, Integer> pos) {
        Skill skills = new SkillImpl();
        skills.setAction(GameControl.BASEATTACK, this.maFactory.basicRangedAttack());
        skills.setAction(GameControl.DONOTHING, new DoNothing());

        Stat stats = this.statFactory.seagullStat();

        Behaviour behaviour = this.behaviourFactory.makeRangedShy();

        return new MonsterImpl("RangedSeagull", pos, skills, stats, behaviour);
    }

    /**
     * 
     */
    @Override
    public Monster makeBombCockroach(final Pair<Integer, Integer> pos) {
        Skill skills = new SkillImpl();
        skills.setAction(GameControl.BASEATTACK, this.maFactory.disgustingDemise());
        skills.setAction(GameControl.DONOTHING, new DoNothing());

        Stat stats = this.statFactory.cockroachStat();

        Behaviour behaviour = this.behaviourFactory.makeMeleeBrainless();

        return new MonsterImpl("BombCockroach", pos, skills, stats, behaviour);
    }
}
