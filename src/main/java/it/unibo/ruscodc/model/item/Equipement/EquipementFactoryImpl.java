package it.unibo.ruscodc.model.item.Equipement;

import java.util.HashMap;
import java.util.Map;

import it.unibo.ruscodc.model.actors.stat.StatImpl.StatName;
import it.unibo.ruscodc.model.gamecommand.GameCommand;
import it.unibo.ruscodc.model.item.InventoryImpl.Slot;
import it.unibo.ruscodc.utils.GameControl;
import it.unibo.ruscodc.utils.Pair;

public class EquipementFactoryImpl implements EquipementFactory {

    EquipementActionFactory actionFactory = new EquipementActionFactoryImpl();

    @Override
    public Equipement createLongSword() {
        Map<StatName, Integer> stat = new HashMap<>();
        return new EquipementImpl(
            "Long Sword",
            "That thing was too big to be called a sword. It was more like a large hunk of Iron",
            "file:src/main/resources/it/unibo/ruscodc/map_res/LongSword",
            Slot.WEAPON,
            stat,
            new Pair<GameControl,GameCommand>(
                GameControl.BASEATTACK, this.actionFactory.createBasicMelee()));
    }

    @Override
    public Equipement createSwordShield() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createSwordShield'");
    }

    @Override
    public Equipement createHandBow() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createHandBow'");
    }

    @Override
    public Equipement createTunic() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createTunic'");
    }
    
}
