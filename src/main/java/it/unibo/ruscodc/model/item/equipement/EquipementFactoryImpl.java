package it.unibo.ruscodc.model.item.equipement;

import java.util.HashMap;
import java.util.Map;

import it.unibo.ruscodc.model.actors.stat.StatImpl.StatName;
import it.unibo.ruscodc.model.gamecommand.GameCommand;
import it.unibo.ruscodc.model.item.InventoryImpl.Slot;
import it.unibo.ruscodc.utils.GameControl;
import it.unibo.ruscodc.utils.Pair;

/**
 * Implementation of the Equipement Factory that creates
 * different type of Equipement.
 */
public class EquipementFactoryImpl implements EquipementFactory {

    private static final int LONG_SWORD_DMG = 10;

    private static final int SWORD_SHIELD_DMG = 7;
    private static final int SWORD_SHIELD_HP = 10;

    private static final int HANDBOW_DMG = 4;
    private static final int HANDBOW_DEX = 2;

    private static final int SHOTGUN_DMG = 5;
    private static final int SHOTGUN_STR = 2;

    private static final int ROCKET_HAMMER_DMG = 10;
    private static final int ROCKET_HAMMER_STR = 3;

    private static final int VIKING_HELMET_HP = 5;
    private static final int VIKING_HELMET_STR = 2;

    private static final int WIZARD_HAT_HP = 3;
    private static final int WIZARD_HAT_INT = 2;

    private static final int CRUSADER_HELMET_HP = 10;
    private static final int CRUSADER_HELMET_STR = 3;

    private static final int TUNIC_HP = 5;

    private static final int CHAINMAIL_HP = 20;
    private static final int CHAINMAIL_STR = 3;

    private static final int LEATHER_ARMOUR_HP = 10;
    private static final int LEATHER_ARMOUR_DEX = 3;

    private static final int HEALTH_RING_HP = 20;

    private static final int ACTION_RING_AP = 20;

    private static final int STRENGHT_RING_STR = 10;

    private static final int DEXTERITY_RING_DEX = 10;

    private static final int INTELLECT_RING_INT = 10;

    private static final int RING_HP = 1;

    private final EquipementActionFactory actionFactory = new EquipementActionFactoryImpl();

    /**
     * 
     */
    @Override
    public Equipement createLongSword() {
        final Map<StatName, Integer> stat = new HashMap<>();
        stat.put(StatName.DMG, LONG_SWORD_DMG);
        return new EquipementImpl(
            "Long Sword",
            "That thing was too big to be called a sword. It was more like a large hunk of Iron",
            "it/unibo/ruscodc/item_res/LongSword",
            Slot.WEAPON,
            stat,
            new Pair<GameControl, GameCommand>(
                GameControl.BASEATTACK, this.actionFactory.createBasicMelee())
        );
    }

    /**
     * 
     */
    @Override
    public Equipement createSwordShield() {
        final Map<StatName, Integer> stat = new HashMap<>();
        stat.put(StatName.DMG, SWORD_SHIELD_DMG);
        stat.put(StatName.HP, SWORD_SHIELD_HP);
        return new EquipementImpl(
            "Sword and Shield",
            "A simple weapon set from a far away land, where even the smallest fighter can hunt the biggest monster",
            "it/unibo/ruscodc/item_res/SwordShield",
            Slot.WEAPON,
            stat,
            new Pair<GameControl, GameCommand>(
                GameControl.BASEATTACK, this.actionFactory.createBasicMelee())
        );
    }

    /**
     * 
     */
    @Override
    public Equipement createHandbow() {
        final Map<StatName, Integer> stat = new HashMap<>();
        stat.put(StatName.DMG, HANDBOW_DMG);
        stat.put(StatName.DEX, HANDBOW_DEX);
        return new EquipementImpl(
            "Handbow",
            "A perfect weapon for vampire hunting",
            "it/unibo/ruscodc/item_res/Handbow",
            Slot.WEAPON,
            stat,
            new Pair<GameControl, GameCommand>(
                GameControl.BASEATTACK, this.actionFactory.createBasicRanged())
        );
    }

    /**
     * 
     */
    @Override
    public Equipement createShotgun() {
        final Map<StatName, Integer> stat = new HashMap<>();
        stat.put(StatName.DMG, SHOTGUN_DMG);
        stat.put(StatName.STR, SHOTGUN_STR);
        return new EquipementImpl(
            "Shotgun",
            "You see this? This is my Boomstick!",
            "it/unibo/ruscodc/item_res/Shotgun",
            Slot.WEAPON,
            stat,
            new Pair<GameControl, GameCommand>(
                GameControl.BASEATTACK, this.actionFactory.createConeAttack())
        );
    }

    /**
     * 
     */
    @Override
    public Equipement createRocketHammer() {
        final Map<StatName, Integer> stat = new HashMap<>();
        stat.put(StatName.DMG, ROCKET_HAMMER_DMG);
        stat.put(StatName.STR, ROCKET_HAMMER_STR);
        return new EquipementImpl(
            "Rocket Hammer",
            "Hammer down!",
            "it/unibo/ruscodc/item_res/RocketHammer",
            Slot.WEAPON,
            stat,
            new Pair<GameControl, GameCommand>(
                GameControl.BASEATTACK, this.actionFactory.createRowAttack())
        );
    }

    /**
     * 
     */
    @Override
    public Equipement createVikingHelmet() {
        final Map<StatName, Integer> stat = new HashMap<>();
        stat.put(StatName.HP, VIKING_HELMET_HP);
        stat.put(StatName.STR, VIKING_HELMET_STR);
        return new EquipementImpl(
            "Viking Helmet",
            "Oh! You are finally awake!",
            "it/unibo/ruscodc/item_res/VikingHelmet",
            Slot.HEAD,
            stat,
            null
        );
    }

    /**
     * 
     */
    @Override
    public Equipement createWizardHat() {
        final Map<StatName, Integer> stat = new HashMap<>();
        stat.put(StatName.HP, WIZARD_HAT_HP);
        stat.put(StatName.INT, WIZARD_HAT_INT);
        return new EquipementImpl(
            "Wizard Hat",
            "You are a wizard Harry!",
            "it/unibo/ruscodc/item_res/WizardHat",
            Slot.HEAD,
            stat,
            null
        );
    }

    /**
     * 
     */
    @Override
    public Equipement createCrusaderHelmet() {
        final Map<StatName, Integer> stat = new HashMap<>();
        stat.put(StatName.HP, CRUSADER_HELMET_HP);
        stat.put(StatName.STR, CRUSADER_HELMET_STR);
        return new EquipementImpl(
            "Crusader Helmet",
            "Deus lo Vult!",
            "it/unibo/ruscodc/item_res/CrusaderHelmet",
            Slot.HEAD,
            stat,
            null
        );
    }

    /**
     * 
     */
    @Override
    public Equipement createTunic() {
        final Map<StatName, Integer> stat = new HashMap<>();
        stat.put(StatName.HP, TUNIC_HP);
        return new EquipementImpl(
            "Tunic",
            "100% pure cotton",
            "it/unibo/ruscodc/item_res/Tunic",
            Slot.ARMOR,
            stat,
            null
        );
    }

    /**
     * 
     */
    @Override
    public Equipement createChainmail() {
        final Map<StatName, Integer> stat = new HashMap<>();
        stat.put(StatName.HP, CHAINMAIL_HP);
        stat.put(StatName.STR, CHAINMAIL_STR);
        return new EquipementImpl(
            "Chainmail",
            "You just got CHAIN-Mailed!",
            "it/unibo/ruscodc/item_res/Chainmail",
            Slot.ARMOR,
            stat,
            null
        );
    }

    /**
     * 
     */
    @Override
    public Equipement createLeatherArmor() {
        final Map<StatName, Integer> stat = new HashMap<>();
        stat.put(StatName.HP, LEATHER_ARMOUR_HP);
        stat.put(StatName.DEX, LEATHER_ARMOUR_DEX);
        return new EquipementImpl(
            "Leather Armor",
            "I think it is worth 15 emeralds",
            "it/unibo/ruscodc/item_res/LeatherArmor",
            Slot.ARMOR,
            stat,
            null
        );
    }

    /**
     * 
     */
    @Override
    public Equipement createHealthRing() {
        final Map<StatName, Integer> stat = new HashMap<>();
        stat.put(StatName.HP, HEALTH_RING_HP);
        return new EquipementImpl(
            "Health Ring",
            "A ring with the shape of an heart",
            "it/unibo/ruscodc/item_res/HealthRing",
            Slot.SPECIAL,
            stat,
            null
        );
    }

    /**
     * 
     */
    @Override
    public Equipement createActionRing() {
        final Map<StatName, Integer> stat = new HashMap<>();
        stat.put(StatName.AP, ACTION_RING_AP);
        return new EquipementImpl(
            "Action Ring",
            "A ring made of feathers",
            "it/unibo/ruscodc/item_res/ActionRing",
            Slot.SPECIAL,
            stat,
            null
        );
    }

    /**
     * 
     */
    @Override
    public Equipement createStrenghtRing() {
        final Map<StatName, Integer> stat = new HashMap<>();
        stat.put(StatName.STR, STRENGHT_RING_STR);
        return new EquipementImpl(
            "Strenght Ring",
            "A ring with the shape of a flame",
            "it/unibo/ruscodc/item_res/StrenghtRing",
            Slot.SPECIAL,
            stat,
            null
        );
    }

    /**
     * 
     */
    @Override
    public Equipement createDexterityRing() {
        final Map<StatName, Integer> stat = new HashMap<>();
        stat.put(StatName.DEX, DEXTERITY_RING_DEX);
        return new EquipementImpl(
            "Dexterity Ring",
            "A ring with the shape of a leaf",
            "it/unibo/ruscodc/item_res/DexterityRing",
            Slot.SPECIAL,
            stat,
            null
        );
    }

    /**
     * 
     */
    @Override
    public Equipement createInteligenceRing() {
        final Map<StatName, Integer> stat = new HashMap<>();
        stat.put(StatName.INT, INTELLECT_RING_INT);
        return new EquipementImpl(
            "Inteligence Ring",
            "A ring with the shape of a harp",
            "it/unibo/ruscodc/item_res/InteligenceRing",
            Slot.SPECIAL,
            stat,
            null
        );
    }

    /**
     * 
     */
    @Override
    public Equipement createRing() {
        final Map<StatName, Integer> stat = new HashMap<>();
        stat.put(StatName.HP, RING_HP);
        return new EquipementImpl(
            "Ring",
            "A ring",
            "it/unibo/ruscodc/item_res/Ring",
            Slot.SPECIAL,
            stat,
            null
        );
    }
}
