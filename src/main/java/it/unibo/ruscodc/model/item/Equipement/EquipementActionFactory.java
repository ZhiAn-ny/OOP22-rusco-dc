package it.unibo.ruscodc.model.item.Equipement;

import it.unibo.ruscodc.model.gamecommand.GameCommand;

public interface EquipementActionFactory {
    GameCommand createBasicMelee();
    GameCommand createBasicRanged();
    GameCommand createConeAttack();
    GameCommand createRowAttack();
}
