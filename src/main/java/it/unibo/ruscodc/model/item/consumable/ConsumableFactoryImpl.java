package it.unibo.ruscodc.model.item.consumable;

import it.unibo.ruscodc.model.actors.Actor;
import it.unibo.ruscodc.model.actors.stat.StatImpl.StatName;
import it.unibo.ruscodc.model.effect.SingleTargetEffect;

/**
 * Implementation of the ConsumableFactory interface that creates
 * differnt Consumable Items.
 */
public class ConsumableFactoryImpl implements ConsumableFactory {

    private static final float PERCENT20 = 20 / 100;
    private static final float PERCENT40 = 40 / 100;
    private static final float PERCENT60 = 60 / 100;

    /**
     * 
     */
    @Override
    public Consumable createHPPotion() {
        return new ConsumableImpl(
            "Health Potion",
            "A standard healing potion that heals 20% of the user HP",
            "file:src/main/resources/it/unibo/ruscodc/consumable_res/HP_Potion",
            new SingleTargetEffect() {
                @Override
                public void applyEffect(final Actor target) {
                    target.modifyActualStat(
                        StatName.HP,
                        target.getStatActual(StatName.HP)
                        + (int) (target.getStatMax(StatName.HP) * PERCENT20)
                    );
                }
            }
        );
    }

    /**
     * 
     */
    @Override
    public Consumable createAPotion() {
        return new ConsumableImpl(
            "Action potion",
            "A standard energetic brew to charge you up",
            "file:src/main/resources/it/unibo/ruscodc/consumable_res/AP_Potion",
            new SingleTargetEffect() {
                @Override
                public void applyEffect(final Actor target) {
                    target.modifyActualStat(
                        StatName.AP,
                        target.getStatActual(StatName.AP)
                        + (int) (target.getStatMax(StatName.HP) * PERCENT20)
                    );
                }
            }
        );
    }

    /**
     * 
     */
    @Override
    public Consumable createSTRPotion() {
        return new ConsumableImpl(
            "Strenght potion",
            "A potion that permanently increase your STR by 1",
            "file:src/main/resources/it/unibo/ruscodc/consumable_res/STR_Potion",
            new SingleTargetEffect() {
                @Override
                public void applyEffect(final Actor target) {
                    target.modifyMaxStat(
                        StatName.STR,
                        target.getStatMax(StatName.STR) + 1
                    );
                }
            }
        );
    }

    /**
     * 
     */
    @Override
    public Consumable createDEXPotion() {
        return new ConsumableImpl(
            "Dexterity potion",
            "A potion that permanently increase your DEX by 1",
            "file:src/main/resources/it/unibo/ruscodc/consumable_res/DEX_Potion",
            new SingleTargetEffect() {
                @Override
                public void applyEffect(final Actor target) {
                    target.modifyMaxStat(
                        StatName.DEX,
                        target.getStatMax(StatName.DEX) + 1
                    );
                }
            }
        );
    }

    /**
     * 
     */
    @Override
    public Consumable createINTPotion() {
        return new ConsumableImpl(
            "Intellect potion",
            "A potion that permanently increase your INT by 1",
            "file:src/main/resources/it/unibo/ruscodc/consumable_res/INT_Potion",
            new SingleTargetEffect() {
                @Override
                public void applyEffect(final Actor target) {
                    target.modifyMaxStat(
                        StatName.INT,
                        target.getStatMax(StatName.INT) + 1
                    );
                }
            }
        );
    }

    /**
     * 
     */
    @Override
    public Consumable createMegaHPPotion() {
        return new ConsumableImpl(
            "Mega Health potion",
            "A slightly larger healing potion that heals 40% of the user HP",
            "file:src/main/resources/it/unibo/ruscodc/consumable_res/Mega_HP_Potion",
            new SingleTargetEffect() {
                @Override
                public void applyEffect(final Actor target) {
                    target.modifyActualStat(
                        StatName.HP,
                        target.getStatActual(StatName.HP)
                        + (int) (target.getStatMax(StatName.HP) * PERCENT40)
                    );
                }
            }
        );
    }

    /**
     * 
     */
    @Override
    public Consumable createSuperHPPotion() {
        return new ConsumableImpl(
            "Super Health potion",
            "A large healing potion that heals 60% of the user HP",
            "file:src/main/resources/it/unibo/ruscodc/consumable_res/Super_HP_Potion",
            new SingleTargetEffect() {
                @Override
                public void applyEffect(final Actor target) {
                    target.modifyActualStat(
                        StatName.HP,
                        target.getStatActual(StatName.HP)
                        + (int) (target.getStatMax(StatName.HP) * PERCENT60)
                    );
                }
            }
        );
    }
}
