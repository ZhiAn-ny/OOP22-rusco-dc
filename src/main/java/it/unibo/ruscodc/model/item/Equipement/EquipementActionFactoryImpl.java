package it.unibo.ruscodc.model.item.Equipement;

import it.unibo.ruscodc.model.actors.Actor;
import it.unibo.ruscodc.model.effect.EffectAbs;
import it.unibo.ruscodc.model.gamecommand.GameCommand;
import it.unibo.ruscodc.model.gamecommand.playercommand.PlayerAttack;
import it.unibo.ruscodc.model.range.CircleRange;
import it.unibo.ruscodc.model.range.SingleRange;
import it.unibo.ruscodc.model.range.SingleSplash;

public class EquipementActionFactoryImpl implements EquipementActionFactory {

    @Override
    public GameCommand createBasicMelee() {
        return new PlayerAttack(
            new CircleRange(1, new SingleRange()),
            new SingleSplash(),
            new EffectAbs(0) {
                @Override
                public void applyEffect(Actor from, Actor to) {
                    int damage = from.getStatActual(StatName.DMG) + from.getStatActual(StatName.STR);
                    to.modifyActualStat(StatName.HP, - damage);
                }
            };
        );
    }

    @Override
    public GameCommand createBasicRanged() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createBasicRanged'");
    }

    @Override
    public GameCommand createConeAttack() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createConeAttack'");
    }

    @Override
    public GameCommand createRowAttack() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createRowAttack'");
    }
    
}
