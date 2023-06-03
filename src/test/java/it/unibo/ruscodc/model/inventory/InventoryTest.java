package it.unibo.ruscodc.model.inventory;

import it.unibo.ruscodc.model.actors.hero.Hero;
import it.unibo.ruscodc.model.actors.hero.HeroImpl;
import it.unibo.ruscodc.model.actors.hero.HeroSkill;
import it.unibo.ruscodc.model.actors.hero.HeroStat;
import it.unibo.ruscodc.model.item.Inventory;
import it.unibo.ruscodc.model.item.equipement.Equipement;
import it.unibo.ruscodc.model.item.equipement.EquipementFactory;
import it.unibo.ruscodc.model.item.equipement.EquipementFactoryImpl;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.stream.Collectors;

@TestInstance(Lifecycle.PER_CLASS)
public class InventoryTest {
    private final EquipementFactory equipementFactory = new EquipementFactoryImpl();
    private Equipement longSword = this.equipementFactory.createLongSword();
    private Equipement swordAndShield = this.equipementFactory.createSwordShield();
    private Hero hero;
    private Inventory inventory;

    @BeforeAll
    void init() {
        this.hero = new HeroImpl("Test", null, new HeroSkill(), new HeroStat());
        this.inventory = hero.getInventory();
        this.inventory.addItem(longSword);
        this.inventory.addItem(swordAndShield);
    }

    /**
     * Method under test: Method that creates a Long Sword.
     */
    @Test
    void testGetAllItems() {
        assertEquals(inventory.getAllItems(), List.of(this.longSword, this.swordAndShield));
    }

    @Test
    void testRemoveItem() {
        System.out.println(List.of(this.swordAndShield));
        System.out.println(inventory.getAllItems());
        System.out.println(this.inventory.getAllItems().indexOf(this.longSword));
        this.inventory.removeItem(this.inventory.getAllItems().indexOf(this.longSword));
        assertEquals(inventory.getAllItems(), List.of(this.swordAndShield));
    }

    @Test
    void testEquip() {
        this.inventory.equip((Equipement) this.inventory.getItem(0), this.hero);

        assertEquals(
            this.inventory
                .getEquipedItems()
                .stream()
                .filter(a -> a != null)
                .collect(Collectors.toList()),
            List.of(this.longSword)
        );

        assertEquals(
            this.inventory
                .getAllItems(),
            List.of(this.swordAndShield)
        );

        this.inventory.equip(
            (Equipement) this.inventory.getItem(0),
            this.hero
        );

        assertEquals(
            this.inventory
                .getEquipedItems()
                .stream()
                .filter(a -> a != null)
                .collect(Collectors.toList()),
            List.of(this.swordAndShield)
        );

        assertEquals(
            this.inventory
                .getAllItems(),
            List.of(this.longSword)
        );
    }
}
