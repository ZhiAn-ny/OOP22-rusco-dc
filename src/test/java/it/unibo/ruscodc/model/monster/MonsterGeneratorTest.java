package it.unibo.ruscodc.model.monster;

import it.unibo.ruscodc.model.actors.monster.Monster;
import it.unibo.ruscodc.model.actors.monster.MonsterActionFactory;
import it.unibo.ruscodc.model.actors.monster.MonsterActionFactoryImpl;
import it.unibo.ruscodc.model.actors.monster.MonsterGenerator;
import it.unibo.ruscodc.model.actors.monster.MonsterGeneratorImpl;
import it.unibo.ruscodc.model.actors.monster.MonsterStatFactory;
import it.unibo.ruscodc.model.actors.monster.MonsterStatFactoryImpl;
import it.unibo.ruscodc.model.actors.skill.Skill;
import it.unibo.ruscodc.model.actors.stat.Stat;
import it.unibo.ruscodc.model.actors.stat.StatImpl.StatName;
import it.unibo.ruscodc.utils.GameControl;
import it.unibo.ruscodc.utils.Pair;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(Lifecycle.PER_CLASS)
final class MonsterGeneratorTest {
    
    private final static int ID = 3;
    private final static String GENERIC_PATH = "it/unibo/ruscodc/monster_res/";
    
    private static final Pair<Integer, Integer> CENTRE_POS = new Pair<>(3, 3);
    private final MonsterGenerator monsterGenerator = new MonsterGeneratorImpl();
    private final MonsterStatFactory statFactory = new MonsterStatFactoryImpl();
    private final MonsterActionFactory monsterActionFactory = new MonsterActionFactoryImpl();

    /**
     * Method under test: Method that creates a Melee Rat.
     */
    @Test
    void testMeleeRat() {
        final Monster meleeRat = this.monsterGenerator.makeMeleeRat(CENTRE_POS);
        final Skill meleeRatSkill = meleeRat.getSkills();
        assertEquals(ID, meleeRat.getID());
        assertEquals("MeleeRat", meleeRat.getName());
        assertEquals(GENERIC_PATH + "MeleeRat", meleeRat.getPath());
        assertEquals(CENTRE_POS, meleeRat.getPos());

        final Stat toCheck = this.statFactory.ratStat();
        for (StatName stat : StatName.values()) {
            assertEquals(toCheck.getStatActual(stat), meleeRat.getStatActual(stat));
            assertEquals(toCheck.getStatMax(stat), meleeRat.getStatMax(stat));
        }

        assertEquals(
            this.monsterActionFactory.basicMeleeAttack().toString(),
            meleeRatSkill.getAction(GameControl.BASEATTACK).get().toString()
        );

        assertEquals(
            this.monsterActionFactory.heavyMeleeAttack().toString(),
            meleeRatSkill.getAction(GameControl.ATTACK1).get().toString()
        );
    }

    @Test
    void testRangedRat() {
        final Monster rangedRat = this.monsterGenerator.makeRangedRat(CENTRE_POS);
        final Skill rangedRatSkill = rangedRat.getSkills();
        assertEquals(ID, rangedRat.getID());
        assertEquals("RangedRat", rangedRat.getName());
        assertEquals(GENERIC_PATH + "RangedRat", rangedRat.getPath());
        assertEquals(CENTRE_POS, rangedRat.getPos());

        final Stat toCheck = this.statFactory.ratStat();
        for (StatName stat : StatName.values()) {
            assertEquals(toCheck.getStatActual(stat), rangedRat.getStatActual(stat));
            assertEquals(toCheck.getStatMax(stat), rangedRat.getStatMax(stat));
        }

        assertEquals(
            this.monsterActionFactory.basicRangedAttack().toString(),
            rangedRatSkill.getAction(GameControl.BASEATTACK).get().toString()
        );
    }

    @Test
    void testMageRat() {
        final Monster mageRat = this.monsterGenerator.makeMageRat(CENTRE_POS);
        final Skill mageRatSkill = mageRat.getSkills();
        assertEquals(ID, mageRat.getID());
        assertEquals("MageRat", mageRat.getName());
        assertEquals(GENERIC_PATH + "MageRat", mageRat.getPath());
        assertEquals(CENTRE_POS, mageRat.getPos());

        final Stat toCheck = this.statFactory.ratStat();
        for (StatName stat : StatName.values()) {
            if (stat == StatName.INT) {
                assertEquals(toCheck.getStatActual(stat) + 5, mageRat.getStatActual(stat));
                assertEquals(toCheck.getStatMax(stat) + 5, mageRat.getStatActual(stat));
            } else {
                assertEquals(toCheck.getStatActual(stat), mageRat.getStatActual(stat));
                assertEquals(toCheck.getStatMax(stat), mageRat.getStatActual(stat));
            }
        }

        assertEquals(
            this.monsterActionFactory.ratMagic().toString(),
            mageRatSkill.getAction(GameControl.BASEATTACK).get().toString()
        );

        assertEquals(
            this.monsterActionFactory.badSmell().toString(),
            mageRatSkill.getAction(GameControl.ATTACK1).get().toString()
        );
    }

    @Test
    void testRogueOpossum() {
        final Monster rogueOpossum = this.monsterGenerator.makeRogueOpossum(CENTRE_POS);
        final Skill rogueOpossumSkill = rogueOpossum.getSkills();
        assertEquals(ID, rogueOpossum.getID());
        assertEquals("RogueOpossum", rogueOpossum.getName());
        assertEquals(GENERIC_PATH + "RogueOpossum", rogueOpossum.getPath());
        assertEquals(CENTRE_POS, rogueOpossum.getPos());

        final Stat toCheck = this.statFactory.opossumStat();
        for (StatName stat : StatName.values()) {
            assertEquals(toCheck.getStatActual(stat), rogueOpossum.getStatActual(stat));
            assertEquals(toCheck.getStatMax(stat), rogueOpossum.getStatActual(stat));
        }

        assertEquals(
            this.monsterActionFactory.basicMeleeAttack().toString(),
            rogueOpossumSkill.getAction(GameControl.BASEATTACK).get().toString()
        );

        assertEquals(
            this.monsterActionFactory.heavyMeleeAttack().toString(),
            rogueOpossumSkill.getAction(GameControl.ATTACK1).get().toString()
        );

        assertEquals(
            this.monsterActionFactory.backstab().toString(),
            rogueOpossumSkill.getAction(GameControl.ATTACK1).get().toString()
        );
    }

    @Test
    void testrangedOpossum() {
        final Monster rangedOpossum = this.monsterGenerator.makeRangedOpossum(CENTRE_POS);
        final Skill rangedOpossumSkill = rangedOpossum.getSkills();
        assertEquals(ID, rangedOpossum.getID());
        assertEquals("RangedOpossum", rangedOpossum.getName());
        assertEquals(GENERIC_PATH + "RangedOpossum", rangedOpossum.getPath());
        assertEquals(CENTRE_POS, rangedOpossum.getPos());

        final Stat toCheck = this.statFactory.opossumStat();
        for (StatName stat : StatName.values()) {
            assertEquals(toCheck.getStatActual(stat), rangedOpossum.getStatActual(stat));
            assertEquals(toCheck.getStatMax(stat), rangedOpossum.getStatActual(stat));
        }

        assertEquals(
            this.monsterActionFactory.basicRangedAttack().toString(),
            rangedOpossumSkill.getAction(GameControl.BASEATTACK).get().toString()
        );
    }

    @Test
    void testRangedOpossum() {
        final Monster rangedOpossum = this.monsterGenerator.makeRangedOpossum(CENTRE_POS);
        final Skill rangedOpossumSkill = rangedOpossum.getSkills();
        assertEquals(ID, rangedOpossum.getID());
        assertEquals("RangedOpossum", rangedOpossum.getName());
        assertEquals(GENERIC_PATH + "RangedOpossum", rangedOpossum.getPath());
        assertEquals(CENTRE_POS, rangedOpossum.getPos());

        final Stat toCheck = this.statFactory.opossumStat();
        for (StatName stat : StatName.values()) {
            assertEquals(toCheck.getStatActual(stat), rangedOpossum.getStatActual(stat));
            assertEquals(toCheck.getStatMax(stat), rangedOpossum.getStatActual(stat));
        }

        assertEquals(
            this.monsterActionFactory.basicRangedAttack().toString(),
            rangedOpossumSkill.getAction(GameControl.BASEATTACK).get().toString()
        );
    }

    @Test
    void testMeleeSeagull() {
        final Monster meleeSeagull = this.monsterGenerator.makeMeleeSeagull(CENTRE_POS);
        final Skill meleeSeagullSkill = meleeSeagull.getSkills();
        assertEquals(ID, meleeSeagull.getID());
        assertEquals("MeleeSeagull", meleeSeagull.getName());
        assertEquals(GENERIC_PATH + "MeleeSeagull", meleeSeagull.getPath());
        assertEquals(CENTRE_POS, meleeSeagull.getPos());

        final Stat toCheck = this.statFactory.seagullStat();
        for (StatName stat : StatName.values()) {
            assertEquals(toCheck.getStatActual(stat), meleeSeagull.getStatActual(stat));
            assertEquals(toCheck.getStatMax(stat), meleeSeagull.getStatActual(stat));
        }

        assertEquals(
            this.monsterActionFactory.basicMeleeAttack().toString(),
            meleeSeagullSkill.getAction(GameControl.BASEATTACK).get().toString()
        );

        assertEquals(
            this.monsterActionFactory.heavyMeleeAttack().toString(),
            meleeSeagullSkill.getAction(GameControl.ATTACK1).get().toString()
        );
    }

    @Test
    void testRangedSeagull() {
        final Monster rangedSeagull = this.monsterGenerator.makeRangedSeagull(CENTRE_POS);
        final Skill rangedSeagullSkill = rangedSeagull.getSkills();
        assertEquals(ID, rangedSeagull.getID());
        assertEquals("RangedSeagull", rangedSeagull.getName());
        assertEquals(GENERIC_PATH + "RangedSeagull", rangedSeagull.getPath());
        assertEquals(CENTRE_POS, rangedSeagull.getPos());

        final Stat toCheck = this.statFactory.seagullStat();
        for (StatName stat : StatName.values()) {
            assertEquals(toCheck.getStatActual(stat), rangedSeagull.getStatActual(stat));
            assertEquals(toCheck.getStatMax(stat), rangedSeagull.getStatActual(stat));
        }

        assertEquals(
            this.monsterActionFactory.basicRangedAttack().toString(),
            rangedSeagullSkill.getAction(GameControl.BASEATTACK).get().toString()
        );
    }

    @Test
    void testBombCockroach() {
        final Monster bombCockroach = this.monsterGenerator.makeBombCockroach(CENTRE_POS);
        final Skill bombCockroachSkill = bombCockroach.getSkills();
        assertEquals(ID, bombCockroach.getID());
        assertEquals("BombCockroach", bombCockroach.getName());
        assertEquals(GENERIC_PATH + "BombCockroach", bombCockroach.getPath());
        assertEquals(CENTRE_POS, bombCockroach.getPos());

        final Stat toCheck = this.statFactory.cockroachStat();
        for (StatName stat : StatName.values()) {
            assertEquals(toCheck.getStatActual(stat), bombCockroach.getStatActual(stat));
            assertEquals(toCheck.getStatMax(stat), bombCockroach.getStatActual(stat));
        }

        assertEquals(
            this.monsterActionFactory.disgustingDemise().toString(),
            bombCockroachSkill.getAction(GameControl.BASEATTACK).get().toString()
        );
    }
}
