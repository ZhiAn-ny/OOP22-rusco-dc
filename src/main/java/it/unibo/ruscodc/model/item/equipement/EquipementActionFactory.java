package it.unibo.ruscodc.model.item.equipement;

import it.unibo.ruscodc.model.gamecommand.GameCommand;

/**
 * Interface for the Factory that creates GameCommand for Equipement Items.
 */
public interface EquipementActionFactory {

    /**
     * @return a Melee Weapon Attack
     */
    GameCommand createBasicMelee();

    /**
     * @return a Ranged Weapon Attack
     */
    GameCommand createBasicRanged();

    /**
     * @return a Cone Weapon Attack
     */
    GameCommand createConeAttack();

    /**
     * @return a Row Weapon Attack
     */
    GameCommand createRowAttack();
}
