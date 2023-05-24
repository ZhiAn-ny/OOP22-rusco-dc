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
        stat.put(StatName.DMG, 10);
        return new EquipementImpl(
            "Long Sword",
            "That thing was too big to be called a sword. It was more like a large hunk of Iron",
            "file:src/main/resources/it/unibo/ruscodc/map_res/LongSword",
            Slot.WEAPON,
            stat,
            new Pair<GameControl,GameCommand>(
                GameControl.BASEATTACK, this.actionFactory.createBasicMelee())
        );
    }

    @Override
    public Equipement createSwordShield() {
        Map<StatName, Integer> stat = new HashMap<>();
        stat.put(StatName.DMG, 7);
        stat.put(StatName.HP, 10);
        return new EquipementImpl(
            "Sword and Shield",
            "A simple weapon set from a far away land, where even the smallest fighter can hunt the biggest monster",
            "file:src/main/resources/it/unibo/ruscodc/map_res/SwordShield",
            Slot.WEAPON,
            stat,
            new Pair<GameControl,GameCommand>(
                GameControl.BASEATTACK, this.actionFactory.createBasicMelee())
        );
    }
    
    @Override
    public Equipement createHandbow() {
        Map<StatName, Integer> stat = new HashMap<>();
        stat.put(StatName.DMG, 4);
        stat.put(StatName.DEX, 2);
        return new EquipementImpl(
            "Handbow",
            "A perfect weapon for vampire hunting",
            "file:src/main/resources/it/unibo/ruscodc/map_res/Handbow",
            Slot.WEAPON,
            stat,
            new Pair<GameControl,GameCommand>(
                GameControl.BASEATTACK, this.actionFactory.createBasicRanged())
        );
    }

    @Override
    public Equipement createShotgun() {
        Map<StatName, Integer> stat = new HashMap<>();
        stat.put(StatName.DMG, 5);
        stat.put(StatName.STR, 2);
        return new EquipementImpl(
            "Shotgun",
            "You see this? This is my Boomstick!",
            "file:src/main/resources/it/unibo/ruscodc/map_res/Shotgun",
            Slot.WEAPON,
            stat,
            new Pair<GameControl,GameCommand>(
                GameControl.BASEATTACK, this.actionFactory.createConeAttack())
        );
    }

    @Override
    public Equipement createRocketHammer() {
        Map<StatName, Integer> stat = new HashMap<>();
        stat.put(StatName.DMG, 10);
        stat.put(StatName.STR, 3);
        return new EquipementImpl(
            "Rocket Hammer",
            "Hammer down!",
            "file:src/main/resources/it/unibo/ruscodc/map_res/RocketHammer",
            Slot.WEAPON,
            stat,
            new Pair<GameControl,GameCommand>(
                GameControl.BASEATTACK, this.actionFactory.createRowAttack())
        );
    }

    @Override
    public Equipement createVikingHelmet() {
        Map<StatName, Integer> stat = new HashMap<>();
        stat.put(StatName.HP, 5);
        stat.put(StatName.STR, 2);
        return new EquipementImpl(
            "Viking Helmet",
            "Oh! You are finally awake!",
            "file:src/main/resources/it/unibo/ruscodc/map_res/VikingHelmet",
            Slot.HEAD,
            stat,
            null
        );
    }
    
    @Override
    public Equipement createWizardHat() {
        Map<StatName, Integer> stat = new HashMap<>();
        stat.put(StatName.HP, 3);
        stat.put(StatName.INT, 2);
        return new EquipementImpl(
            "Wizard Hat",
            "You are a wizard Harry!",
            "file:src/main/resources/it/unibo/ruscodc/map_res/WizardHat",
            Slot.HEAD,
            stat,
            null
        );
    }

    @Override
    public Equipement createCrusaderHelmet() {
        Map<StatName, Integer> stat = new HashMap<>();
        stat.put(StatName.HP, 10);
        stat.put(StatName.STR, 3);
        return new EquipementImpl(
            "Crusader Helmet",
            "Deus lo Vult!",
            "file:src/main/resources/it/unibo/ruscodc/map_res/CrusaderHelmet",
            Slot.HEAD,
            stat,
            null
        );
    }

    @Override
    public Equipement createTunic() {
        Map<StatName, Integer> stat = new HashMap<>();
        stat.put(StatName.HP, 5);
        return new EquipementImpl(
            "Tunic",
            "100% pure cotton",
            "file:src/main/resources/it/unibo/ruscodc/map_res/Tunic",
            Slot.ARMOR,
            stat,
            null
        );
    }

    @Override
    public Equipement createChainmail() {
        Map<StatName, Integer> stat = new HashMap<>();
        stat.put(StatName.HP, 20);
        stat.put(StatName.STR, 3);
        return new EquipementImpl(
            "Chainmail",
            "You just got CHAIN-Mailed!",
            "file:src/main/resources/it/unibo/ruscodc/map_res/Chainmail",
            Slot.ARMOR,
            stat,
            null
        );
    }

    @Override
    public Equipement createLeatherArmor() {
        Map<StatName, Integer> stat = new HashMap<>();
        stat.put(StatName.HP, 10);
        stat.put(StatName.DEX, 3);
        return new EquipementImpl(
            "Leather Armor",
            "I think it is worth 15 emeralds",
            "file:src/main/resources/it/unibo/ruscodc/map_res/LeatherArmor",
            Slot.ARMOR,
            stat,
            null
        );
    }

    @Override
    public Equipement createHealthRing() {
        Map<StatName, Integer> stat = new HashMap<>();
        stat.put(StatName.HP, 20);
        return new EquipementImpl(
            "Health Ring",
            "A ring with the shape of an heart",
            "file:src/main/resources/it/unibo/ruscodc/map_res/HealthRing",
            Slot.HEAD,
            stat,
            null
        );
    }

    @Override
    public Equipement createActionRing() {
        Map<StatName, Integer> stat = new HashMap<>();
        stat.put(StatName.AP, 20);
        return new EquipementImpl(
            "Actio Ring",
            "A ring made of feathers",
            "file:src/main/resources/it/unibo/ruscodc/map_res/ActionRing",
            Slot.HEAD,
            stat,
            null
        );
    }

    @Override
    public Equipement createStrenghtRing() {
        Map<StatName, Integer> stat = new HashMap<>();
        stat.put(StatName.STR, 10);
        return new EquipementImpl(
            "Strenght Ring",
            "A ring with the shape of a flame",
            "file:src/main/resources/it/unibo/ruscodc/map_res/StrenghtRing",
            Slot.HEAD,
            stat,
            null
        );
    }
    
    @Override
    public Equipement createDexterityRing() {
        Map<StatName, Integer> stat = new HashMap<>();
        stat.put(StatName.DEX, 10);
        return new EquipementImpl(
            "Dexterity Ring",
            "A ring with the shape of a leaf",
            "file:src/main/resources/it/unibo/ruscodc/map_res/DexterityRing",
            Slot.HEAD,
            stat,
            null
        );
    }

    @Override
    public Equipement createInteligenceRing() {
        Map<StatName, Integer> stat = new HashMap<>();
        stat.put(StatName.INT, 10);
        return new EquipementImpl(
            "Inteligence Ring",
            "A ring with the shape of a harp",
            "file:src/main/resources/it/unibo/ruscodc/map_res/InteligenceRing",
            Slot.HEAD,
            stat,
            null
        );
    }

    @Override
    public Equipement createRing() {
        Map<StatName, Integer> stat = new HashMap<>();
        stat.put(StatName.HP, 1);
        return new EquipementImpl(
            "Ring",
            "A ring",
            "file:src/main/resources/it/unibo/ruscodc/map_res/Ring",
            Slot.HEAD,
            stat,
            null
        );
    }
}
