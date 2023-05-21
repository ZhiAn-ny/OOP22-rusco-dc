package it.unibo.ruscodc.model.item;

import it.unibo.ruscodc.model.actors.Actor;
import it.unibo.ruscodc.model.actors.stat.StatImpl.StatName;
import it.unibo.ruscodc.model.effect.SingleTargetEffect;
import it.unibo.ruscodc.model.gamecommand.GameCommand;
import it.unibo.ruscodc.model.interactable.Drop;
import it.unibo.ruscodc.model.outputinfo.InfoPayload;
import it.unibo.ruscodc.model.outputinfo.InfoPayloadImpl;

public class ConsumableFactoryImpl implements ConsumableFactory {
    
    @Override
    public Consumable createHPPotion() {
        return new Consumable() {
            
            // private final InfoPayload info = new InfoPayload();
            private final String name = "Health Potion";
            private final String path = "file:src/main/resources/it/unibo/ruscodc/map_res/HP_Potion";
            
            @Override
            public String getName() {
                return this.name;
            }
            
            @Override
            public String getPath() {
               return this.path;
            }

            @Override
            public InfoPayload getInfo() {
                return new InfoPayloadImpl(
                    name,
                    "A standard healing potion that heals 20% of the user HP",
                    path);
            }

            @Override
            public boolean isWearable() {
                return false;
            }

            @Override
            public GameCommand drop(Actor actor) {
                return null;    // Aspetta JP
            }

            @Override
            public SingleTargetEffect consume() {
                return new SingleTargetEffect() {

                    @Override
                    public void applyEffect(Actor target) {
                        target.modifyStat(
                            StatName.HP,
                            target.getStatActual(StatName.HP) + (target.getStatActual(StatName.HP)*20/100));
                    }
                    
                };
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
