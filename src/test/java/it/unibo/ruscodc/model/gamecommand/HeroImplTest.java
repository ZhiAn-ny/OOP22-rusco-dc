package it.unibo.ruscodc.model.gamecommand;

import java.util.stream.Stream;

import it.unibo.ruscodc.model.actors.hero.HeroImpl;
import it.unibo.ruscodc.model.actors.skill.Skill;
import it.unibo.ruscodc.model.actors.stat.Stat;
import it.unibo.ruscodc.model.item.consumable.ConsumableFactory;
import it.unibo.ruscodc.model.item.consumable.ConsumableFactoryImpl;
import it.unibo.ruscodc.utils.Pair;

/**
 * Implementation very similar to HeroImpl, but specific for testing ComplexCommands.
 * Credits: Pesaresi Jacopo
 */
public final class HeroImplTest extends HeroImpl {

    private static final ConsumableFactory CONS_GEN = new ConsumableFactoryImpl();
    private static final int AMOUNT = 13;

    /**
     * Create a specific implementation of an hero, usefull for test.
     * @param name his name
     * @param initialPos his initial pos
     * @param skills his skills (probabily modified into test class to be testes)
     * @param stats his stats (probabily modified into test class to be testes)
     */
    public HeroImplTest(final String name, final Pair<Integer, Integer> initialPos, final Skill skills, final Stat stats) {
        super(name, initialPos, skills, stats);
        Stream.generate(() -> CONS_GEN.createHPPotion())
            .limit(AMOUNT)
            .forEach(c -> this.getInventory().addItem(c));
    }
}
