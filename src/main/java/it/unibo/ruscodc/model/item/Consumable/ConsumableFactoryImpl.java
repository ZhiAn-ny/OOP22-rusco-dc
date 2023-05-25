package it.unibo.ruscodc.model.item.consumable;

import it.unibo.ruscodc.model.actors.Actor;
import it.unibo.ruscodc.model.actors.stat.StatImpl.StatName;
import it.unibo.ruscodc.model.effect.SingleTargetEffect;

public class ConsumableFactoryImpl implements ConsumableFactory {
    
    @Override
    public Consumable createHPPotion() {
        return new ConsumableImpl(
            "Health Potion",
            "A standard healing potion that heals 20% of the user HP",
            "file:src/main/resources/it/unibo/ruscodc/map_res/HP_Potion",
            new SingleTargetEffect() {

                @Override
                public void applyEffect(Actor target) {
                    target.modifyActualStat(
                        StatName.HP,
                        target.getStatActual(StatName.HP) + (target.getStatActual(StatName.HP)*20/100));
                }
            }
        );
    }

    @Override
    public Consumable createAPotion() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createAPotion'");
    }

    @Override
    public Consumable createSTRPotion() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createSTRPotion'");
    }
    
}
