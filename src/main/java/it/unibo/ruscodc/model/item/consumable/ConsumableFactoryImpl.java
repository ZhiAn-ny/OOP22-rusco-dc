package it.unibo.ruscodc.model.item.consumable;

import it.unibo.ruscodc.model.actors.Actor;
import it.unibo.ruscodc.model.actors.stat.StatImpl.StatName;
import it.unibo.ruscodc.model.effect.SingleTargetEffect;

/**
 * Implementation of the ConsumableFactory interface that creates
 * differnt Consumable Items.
 */
public class ConsumableFactoryImpl implements ConsumableFactory {

    private static final double PERCENT20 = 20.0 / 100.0;
    private static final double PERCENT40 = 40.0 / 100.0;
    private static final double PERCENT60 = 60.0 / 100.0;

    /**
     * 
     */
    @Override
    public Consumable createHPPotion() {
        return new ConsumableImpl(
            "Health Potion",
            "A standard healing potion that heals 20% of the user HP",
            "it/unibo/ruscodc/consumable_res/HP_Potion",
            new SingleTargetEffect() {
                @Override
                public void applyEffect(final Actor target) {
                    target.modifyActualStat(
                        StatName.HP,
                        (int) (target.getStatMax(StatName.HP) * PERCENT20)
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
            "it/unibo/ruscodc/consumable_res/AP_Potion",
            new SingleTargetEffect() {
                @Override
                public void applyEffect(final Actor target) {
                    target.modifyActualStat(
                        StatName.AP,
                        (int) (target.getStatMax(StatName.HP) * PERCENT20)
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
            "it/unibo/ruscodc/consumable_res/STR_Potion",
            new SingleTargetEffect() {
                @Override
                public void applyEffect(final Actor target) {
                    target.modifyMaxStat(
                        StatName.STR,
                        1
                    );

                    target.modifyActualStat(
                        StatName.STR,
                        1
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
            "it/unibo/ruscodc/consumable_res/DEX_Potion",
            new SingleTargetEffect() {
                @Override
                public void applyEffect(final Actor target) {
                    target.modifyMaxStat(
                        StatName.DEX,
                        1
                    );

                    target.modifyActualStat(
                        StatName.DEX,
                        1
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
            "it/unibo/ruscodc/consumable_res/INT_Potion",
            new SingleTargetEffect() {
                @Override
                public void applyEffect(final Actor target) {
                    target.modifyMaxStat(
                        StatName.INT,
                        1
                    );

                    target.modifyActualStat(
                        StatName.INT,
                        1
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
            "it/unibo/ruscodc/consumable_res/Mega_HP_Potion",
            new SingleTargetEffect() {
                @Override
                public void applyEffect(final Actor target) {
                    target.modifyActualStat(
                        StatName.HP,
                        (int) (target.getStatMax(StatName.HP) * PERCENT40)
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
            "it/unibo/ruscodc/consumable_res/Super_HP_Potion",
            new SingleTargetEffect() {
                @Override
                public void applyEffect(final Actor target) {
                    target.modifyActualStat(
                        StatName.HP,
                        (int) (target.getStatMax(StatName.HP) * PERCENT60)
                    );
                }
            }
        );
    }
}
