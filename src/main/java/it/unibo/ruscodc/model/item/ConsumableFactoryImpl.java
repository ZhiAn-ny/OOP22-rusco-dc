package it.unibo.ruscodc.model.item;

import it.unibo.ruscodc.model.actors.stat.StatImpl.StatName;
import it.unibo.ruscodc.model.effect.SingleTargetEffect;
import it.unibo.ruscodc.model.gamecommand.GameCommand;

public class ConsumableFactoryImpl implements ConsumableFactory {

    @Override
    public Consumable createHPPotion() {
        return new Consumable() {

            // private final InfoPayload info = new InfoPayload();

            @Override
            public InfoPayload getInfo() {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'getInfo'");
            }

            @Override
            public boolean isWearable() {
                return false;
            }

            @Override
            public GameCommand drop() {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'drop'");
            }

            @Override
            public SingleTargetEffect consume() {
                return 
            }
            
        };
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
