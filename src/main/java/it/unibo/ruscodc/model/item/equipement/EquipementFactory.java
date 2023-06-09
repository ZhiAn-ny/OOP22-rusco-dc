package it.unibo.ruscodc.model.item.equipement;

/**
 * Interface of the Factory that creates Equipable Items.
 */
public interface EquipementFactory {

    //WEAPONS:
    /**
     * Method used to create a Long Sword.
     * @return a Long Sword
     */
    Equipement createLongSword();

    /**
     * Method used to create a Sword and Shield.
     * @return a Sword and Shield
     */
    Equipement createSwordShield();

    /**
     * Method used to create a Handbow.
     * @return a Handbow
     */
    Equipement createHandbow();

    /**
     * Method used to create a Shotgun.
     * @return a Shotgun
     */
    Equipement createShotgun();

    /**
     * Method used to create a Rocket Hammer.
     * @return a Rocket Hammer
     */
    Equipement createRocketHammer();

    //HEAD:
    /**
     * Method used to create a Vinking Helment.
     * @return a Vinking Helment
     */
    Equipement createVikingHelmet();

    /**
     * Method used to create a Wizard Hat.
     * @return a Wizard Hat
     */
    Equipement createWizardHat();

    /**
     * Method used to create a Crusader Helmet.
     * @return a Crusader Helmet
     */
    Equipement createCrusaderHelmet();

    //TORSO
    /**
     * Method used to create a Tunic.
     * @return a Tunic
     */
    Equipement createTunic();

    /**
     * Method used to create a Chainmail.
     * @return a Chainmail
     */
    Equipement createChainmail();

    /**
     * Method used to create a Leather Armor.
     * @return a Leather Armor
     */
    Equipement createLeatherArmor();

    //SPECIAL:
    /**
     * Method used to create a Health Ring.
     * @return a Health Ring
     */
    Equipement createHealthRing();

    /**
     * Method used to create a Action Ring.
     * @return a Action Ring
     */
    Equipement createActionRing();

    /**
     * Method used to create a Strenght Ring.
     * @return a Strenght Ring
     */
    Equipement createStrenghtRing();

    /**
     * Method used to create a Dexterity Ring.
     * @return a Long Sword
     */
    Equipement createDexterityRing();

    /**
     * Method used to create a Inteligence Ring.
     * @return a Inteligence Ring
     */
    Equipement createInteligenceRing();

    /**
     * Method used to create a Ring.
     * @return a Ring
     */
    Equipement createRing();
}
