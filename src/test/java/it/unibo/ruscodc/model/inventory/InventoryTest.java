package it.unibo.ruscodc.model.inventory;

import it.unibo.ruscodc.model.actors.hero.Hero;
import it.unibo.ruscodc.model.actors.hero.HeroImpl;
import it.unibo.ruscodc.model.actors.hero.HeroSkill;
import it.unibo.ruscodc.model.actors.hero.HeroStat;
import it.unibo.ruscodc.model.item.Inventory;
import it.unibo.ruscodc.model.item.equipement.Equipement;
import it.unibo.ruscodc.model.item.equipement.EquipementFactory;
import it.unibo.ruscodc.model.item.equipement.EquipementFactoryImpl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.stream.Collectors;

@TestInstance(Lifecycle.PER_CLASS)
class InventoryTest {
    private final EquipementFactory equipementFactory = new EquipementFactoryImpl();
    private Equipement longSword = this.equipementFactory.createLongSword();
    private Equipement swordAndShield = this.equipementFactory.createSwordShield();

    /**
     * Method under test: Method that creates a Long Sword.
     */
    @Test
    void testAddItem() {
        Hero hero = new HeroImpl("Test", null, new HeroSkill(), new HeroStat());
        Inventory inventory = hero.getInventory();
        inventory.addItem(longSword);
        inventory.addItem(swordAndShield);
        assertEquals(inventory.getAllItems(), List.of(this.longSword, this.swordAndShield));
    }

    @Test
    void testRemoveItem() {
        Hero hero = new HeroImpl("Test", null, new HeroSkill(), new HeroStat());
        Inventory inventory = hero.getInventory();
        inventory.addItem(longSword);
        inventory.addItem(swordAndShield);
        inventory.removeItem(inventory.getAllItems().indexOf(this.longSword));
        assertEquals(inventory.getAllItems(), List.of(this.swordAndShield));
    }

    @Test
    void testEquip() {
        Hero hero = new HeroImpl("Test", null, new HeroSkill(), new HeroStat());
        Inventory inventory = hero.getInventory();
        inventory.addItem(longSword);
        inventory.addItem(swordAndShield);
        inventory.equip((Equipement) inventory.getItem(0), hero);

        assertEquals(
            inventory
                .getEquipedItems()
                .stream()
                .filter(a -> a != null)
                .collect(Collectors.toList()),
            List.of(this.longSword)
        );

        assertEquals(
            inventory
                .getAllItems(),
            List.of(this.swordAndShield)
        );

        inventory.equip(
            (Equipement) inventory.getItem(0),
            hero
        );

        assertEquals(
            inventory
                .getEquipedItems()
                .stream()
                .filter(a -> a != null)
                .collect(Collectors.toList()),
            List.of(this.swordAndShield)
        );

        assertEquals(
            inventory
                .getAllItems(),
            List.of(this.longSword)
        );
    }
}
