package it.unibo.ruscodc;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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
 * Test class for Drop
 */
@TestInstance(Lifecycle.PER_CLASS)
final class DropTest {

    private final Random dice = new Random();
    private final static int ATTEMPS = 100;

    private final DropFactory dropGenerator = new DropFactoryImpl();
    private final MonsterGenerator mg = new MonsterGeneratorImpl();

    @Test
    public void seeInternalStateForUman1() {
        //simulate that a monster is dead to check if the DropFactory work
        DropManager dm = dropGenerator.createGenericBasicDrop(mg.makeMeleeRat(null, null));
        System.out.println(dm.generateAllDrop().size());
        dm.generateAllDrop().forEach(d -> System.out.println(d.getName()));
    }

    @Test
    public void seeInternalStateForUman2() {
        //simulate that a monster is dead to check if the DropFactory work
        DropManager dm = dropGenerator.createGenericBasicDrop(mg.makeMeleeRat(null, null));
        for (int i = 0; i < ATTEMPS; i++) {
            System.out.println(" ### GEN " + i + " ### ");
            dm.generateAllDrop().forEach(d -> System.out.println(d.getName()));
        }
    }

    @Test
    public void seeInternalStateForUman3() {
        //simulate that a monster is dead to check if the DropFactory work
        DropManager dm = dropGenerator.createGenericBasicDrop(mg.makeMeleeRat(null, null));
        System.out.println(dm.generateAllDrop().size());

        System.out.println(" ### ALL ### ");
        dm.generateAllDrop().forEach(i -> System.out.println(i.getName()));

        System.out.println("\n ### EQUIP ### ");
        dm.generateEquipDrop().forEach(i -> System.out.println(i.getName()));

        System.out.println("\n ### CONS ### \n\n\n");
        dm.generateConsumableDrop().forEach(i -> System.out.println(i.getName()));
    }

    @Test
    public void seeInternalStateForUman4() {
        //simulate that a monster is dead to check if the DropFactory work
        DropManager dm = dropGenerator.createGenericBasicDrop(mg.makeMeleeRat(null, null));
        System.out.println(dm.generateAllDrop().size());
        System.out.println(" ### ALL ### ");
        dm.generateAllDrop().forEach(i -> System.out.println(i.getName()));

        System.out.println("\n ### EQUIP ### ");
        dm.generateEquipDrop().forEach(i -> System.out.println(i.getName()));

        System.out.println("\n ### EQUIP - RANDOM ### ");
        dm.generateRandomEquipDrop().forEach(i -> System.out.println(i.getName()));

        System.out.println("\n ### CONS ### \n\n\n");
        dm.generateConsumableDrop().forEach(i -> System.out.println(i.getName()));

        System.out.println("\n ### CONS - RANDOM ### \n\n\n");
        dm.generateRandomConsumableDrop().forEach(i -> System.out.println(i.getName()));
        
    }

    @Test
    public void randomMinusAll() {
        //DropManager dm = dropGenerator.createGenericBasicDrop(mg.makeMeleeRat(null, null));
        final Actor ref = mg.makeMeleeRat(null, null);
        final List<DropManager> generators =List.of(
            dropGenerator.createGenericBasicDrop(ref),
            dropGenerator.createGenericPoorDrop(ref), 
            dropGenerator.createGenericRichDrop(ref));
       
        boolean test = dice.ints(ATTEMPS, 0, generators.size())
            .anyMatch(i -> generators.get(i).generateAllDrop().size() < generators.get(i).generateRandomDrop().size());
        assertFalse(test);

        test = dice.ints(ATTEMPS, 0, generators.size())
            .anyMatch(i -> generators.get(i).generateEquipDrop().size() < generators.get(i).generateRandomEquipDrop().size());
        assertFalse(test);

        test = dice.ints(ATTEMPS, 0, generators.size())
            .anyMatch(i -> generators.get(i).generateConsumableDrop().size() < generators.get(i).generateRandomConsumableDrop().size());
        assertFalse(test);
       
    }

    @Test
    public void generateDropForRoom() {
        final int size = 10;
        final int depth = 1;
        Room myRoom = new RectangleRoomImpl(size, size);
        DropManager mine = dropGenerator.createDropForRoom(myRoom.getSize(), depth);
        int expected = ((int) Math.sqrt(Math.min(myRoom.getSize().getX(),myRoom.getSize().getY()))) * (depth % 5);
        System.out.println(expected);
        assertEquals(expected, mine.generateAllDrop().size());
    }

    @Test
    public void checkRandomFunction() {
        final int size = 10;
        final int depth = 2;
        final Room myRoom = new RectangleRoomImpl(size, size);
        final DropManager mine = dropGenerator.createDropForRoom(myRoom.getSize(), depth);
        List<List<Item>> loots = Stream.generate(() -> mine.generateRandomDrop()).limit(ATTEMPS).toList();
        assertTrue(IntStream.range(0, 1).allMatch(i -> !(loots.get(i).equals(loots.get(i + 1)))));
    }

    
}
