package it.unibo.ruscodc;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import it.unibo.ruscodc.model.actors.Actor;
import it.unibo.ruscodc.model.actors.monster.MonsterGenerator;
import it.unibo.ruscodc.model.actors.monster.MonsterGeneratorImpl;
import it.unibo.ruscodc.model.actors.monster.drop.DropFactory;
import it.unibo.ruscodc.model.actors.monster.drop.DropFactoryImpl;
import it.unibo.ruscodc.model.actors.monster.drop.DropManager;
import it.unibo.ruscodc.model.gamemap.RectangleRoomImpl;
import it.unibo.ruscodc.model.gamemap.Room;
import it.unibo.ruscodc.model.item.Item;

/**
 * Test class for Drop.
 */
@TestInstance(Lifecycle.PER_CLASS)
final class DropTest {

    private final Random dice = new Random();
    private static final int ATTEMPS = 100;
    private static final int FLOOR_CYCLE = 5;

    private final DropFactory dropGenerator = new DropFactoryImpl();
    private final MonsterGenerator mg = new MonsterGeneratorImpl();
    private final DropManager dm;

    private DropTest() {
        this.dm = dropGenerator.createGenericBasicDrop(mg.makeMeleeRat(null));
    }

    @BeforeAll
    void startTest() {
        new DropTest();
    }

    /**
     * Not a very test.
     * Help developer to see how work loot giving a monster that ideally is dead
     */
    @Test
    void seeInternalStateForUman1() {
        System.out.println(" ### ### seeInternalStateForUman1 ### ### "); //NOPMD: help developer during developing phase
        System.out.println(dm.generateAllDrop().size()); //NOPMD: help developer during developing phase
        dm.generateAllDrop().forEach(d -> System.out.println(d.getName())); //NOPMD: help developer during developing phase
    }

    /**
     * Not a very test.
     * Help developer to see how work loot giving a monster that ideally is dead
     */
    @Test
    void seeInternalStateForUman2() {
        System.out.println(" ### ### seeInternalStateForUman2 ### ### "); //NOPMD: help developer during developing phase
        for (int i = 0; i < ATTEMPS; i++) {
            System.out.println(" ### GEN " + i + " ### "); //NOPMD: help developer during developing phase
            dm.generateRandomDrop().forEach(d -> System.out.println(d.getName())); //NOPMD: help developer during developing phase
        }
    }

    /**
     * Not a very test.
     * Help developer to see how work loot giving a monster that ideally is dead
     */
    @Test
    void seeInternalStateForUman3() {
        System.out.println(" ### ### seeInternalStateForUman3 ### ### "); //NOPMD: help developer during developing phase
        System.out.println(dm.generateAllDrop().size()); //NOPMD: help developer during developing phase
        System.out.println(" ### ALL ### "); //NOPMD: help developer during developing phase
        dm.generateAllDrop().forEach(i -> System.out.println(i.getName())); //NOPMD: help developer during developing phase

        System.out.println("\n ### EQUIP ### "); //NOPMD: help developer during developing phase
        dm.generateEquipDrop().forEach(i -> System.out.println(i.getName())); //NOPMD: help developer during developing phase

        System.out.println("\n ### EQUIP - RANDOM ### "); //NOPMD: help developer during developing phase
        dm.generateRandomEquipDrop().forEach(i -> System.out.println(i.getName())); //NOPMD: as above

        System.out.println("\n ### CONS ### "); //NOPMD: help developer during developing phase
        dm.generateConsumableDrop().forEach(i -> System.out.println(i.getName())); //NOPMD: as above

        System.out.println("\n\n\n ### CONS - RANDOM ### "); //NOPMD: help developer during developing phase
        dm.generateRandomConsumableDrop().forEach(i -> System.out.println(i.getName())); //NOPMD: as above
    }

    /**
     * Check if the loot created by random function is at least equals to the loot created.
     */
    @Test
    void randomMinusAll() {
        final Actor ref = mg.makeMeleeRat(null);
        final List<DropManager> generators = List.of(
            dropGenerator.createGenericBasicDrop(ref),
            dropGenerator.createGenericPoorDrop(ref), 
            dropGenerator.createGenericRichDrop(ref));

        boolean test = dice.ints(ATTEMPS, 0, generators.size())
            .anyMatch(i -> generators.get(i).generateAllDrop().size() < generators.get(i).generateRandomDrop().size());
        assertFalse(test);

        test = dice.ints(ATTEMPS, 0, generators.size())
            .anyMatch(i -> 
                generators.get(i).generateEquipDrop().size() < generators.get(i).generateRandomEquipDrop().size());
        assertFalse(test);

        test = dice.ints(ATTEMPS, 0, generators.size())
            .anyMatch(i -> 
                generators.get(i).generateConsumableDrop().size() < generators.get(i).generateRandomConsumableDrop().size());
        assertFalse(test);

    }

    /**
     * Test creation of a DropManager by certain information about room.
     */
    @Test
    void generateDropForRoom() {
        final int size = 10;
        final int depth = 1;
        final Room myRoom = new RectangleRoomImpl(size, size);
        final DropManager mine = dropGenerator.createDropForRoom(myRoom.getSize(), depth);
        final int expected = 
            ((int) Math.sqrt(Math.min(myRoom.getSize().getX(), myRoom.getSize().getY()))) * (depth % FLOOR_CYCLE);
        assertEquals(expected, mine.generateAllDrop().size());
    }

    /**
     * As precedent, but in this test I'll check if generation of loot for a room is different
     * for the generation of loot for the next room.
     */
    @Test
    void checkRandomFunction() {
        final int size = 10;
        final int depth = 2;
        final Room myRoom = new RectangleRoomImpl(size, size);
        final DropManager mine = dropGenerator.createDropForRoom(myRoom.getSize(), depth);
        final List<List<Item>> loots = Stream.generate(() -> mine.generateRandomDrop()).limit(ATTEMPS).toList();
        assertTrue(IntStream.range(0, 1).allMatch(i -> !(loots.get(i).equals(loots.get(i + 1)))));
    }


}
