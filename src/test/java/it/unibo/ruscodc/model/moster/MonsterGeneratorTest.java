package it.unibo.ruscodc.model.moster;

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
    private final static String GENERIC_PATH = "file:src/main/resources/it/unibo/ruscodc/monster_res/";
    
    private final Pair<Integer, Integer> pos = new Pair<>(0, 0);
    private final MonsterGenerator monsterGenerator = new MonsterGeneratorImpl();
    private final MonsterStatFactory statFactory = new MonsterStatFactoryImpl();
    private final MonsterActionFactory monsterActionFactory = new MonsterActionFactoryImpl();

    /**
     * Method under test: Method that creates a Melee Rat.
     */
    @Test
    void testMeleeRat() {
        final Monster meleeRat = this.monsterGenerator.makeMeleeRat(this.pos);
        final Skill meleeRatSkill = meleeRat.getSkills();
        assertEquals(meleeRat.getID(), ID);
        assertEquals(meleeRat.getName(), "MeleeRat");
        assertEquals(meleeRat.getPath(), GENERIC_PATH + "MeleeRat");
        assertEquals(meleeRat.getPos(), this.pos);

        final Stat toCheck = this.statFactory.ratStat();
        for (StatName stat : StatName.values()) {
            assertEquals(meleeRat.getStatActual(stat), toCheck.getStatActual(stat));
            assertEquals(meleeRat.getStatActual(stat), toCheck.getStatMax(stat));
        }

        assertEquals(
            meleeRatSkill.getAction(GameControl.BASEATTACK).get().toString(),
            this.monsterActionFactory.basicMeleeAttack().toString()
        );

        assertEquals(
            meleeRatSkill.getAction(GameControl.ATTACK1).get().toString(),
            this.monsterActionFactory.heavyMeleeAttack().toString()
        );
    }
}
