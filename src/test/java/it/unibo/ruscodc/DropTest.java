package it.unibo.ruscodc;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

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
import it.unibo.ruscodc.model.actors.monster.drop.DropFactoryImplTRY;
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

    //private final static DropFactory dropGenerator = new DropFactoryImpl();
    private final DropFactory dropGenerator = new DropFactoryImplTRY();
    //private final List<Method> factoryMethod = new ArrayList<>();
    //private static DropFactory dropGenerator;
    private final MonsterGenerator mg = new MonsterGeneratorImpl();

    // @BeforeAll
    // public void init(){
    //     Method dfM[] = DropFactory.class.getMethods();
    //     Method oM[] = Object.class.getMethods();

    //     Set<Method> facMethods = IntStream.range(0, dfM.length)
    //         .mapToObj(i -> dfM[i]).collect(Collectors.toSet());
    //     Set<Method> objMethods = IntStream.range(0, oM.length)
    //         .mapToObj(i -> oM[i]).collect(Collectors.toSet());

    //     facMethods.removeAll(objMethods);
    //     factoryMethod.addAll(facMethods);

    // }

    @Test
    public void basicFuncion() {
        //simulate that a monster is dead to check if the DropFactory work
        DropManager dm = dropGenerator.createGenericBasicDrop(mg.makeMeleeRat(null, null));
        System.out.println(dm.generateAllDrop().size());
        for (int i = 0; i < 45; i++) {
            System.out.println(" ### GEN " + i + " ### ");
            dm.generateAllDrop().forEach(d -> System.out.println(d.getName()));
        }
    }

    @Test
    public void basicFuncion2() {
        //simulate that a monster is dead to check if the DropFactory work
        DropManager dm = dropGenerator.createGenericBasicDrop(mg.makeMeleeRat(null, null));
        System.out.println(dm.generateAllDrop().size());
        System.out.println(" ### ALL ### ");
        dm.generateAllDrop().forEach(i -> System.out.println(i.getName()));
        System.out.println("\n ### EQUIP ### ");
        dm.generateEquipDrop().forEach(i -> System.out.println(i.getName()));
        System.out.println("\n ### CONS ### \n\n\n");
        dm.generateConsumableDrop().forEach(i -> System.out.println(i.getName()));
        for (int i = 0; i < 5; i++) {
            System.out.println(" ### GEN " + i + " ### ");
            dm.generateRandomDrop().forEach(d -> System.out.println(d.getName()));
            System.out.println();
        }
    }

    @Test
    public void basicFuncion3() {
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
    
    // @Test
    // public void checkIfInitOnce() throws NoSuchFieldException, SecurityException, NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
    //     Class<DropFactoryImpl> dFClass = DropFactoryImpl.class;
    //     //Field fi = dFClass.getDeclaredField("isInit");
    //     Method m = dFClass.getDeclaredMethod("isInitForTest");
    //     //System.out.println(m);
    //     //assertDoesNotThrow(() -> fi = dFClass.getField("isInit"));
    //     //assertDoesNotThrow(() -> fi.get(fi));
    //     Boolean toTest = (Boolean) m.invoke(dropGenerator);
    //     //Boolean toTest = (Boolean) fi.get(dropGenerator);
    //     assertTrue(toTest.booleanValue());
    //     //final DropFactory dG1 = new DropFactoryImpl();

    // }

    // @Test
    // public void checkIfInitOnce() {

    // }

    @Test
    public void randomMinusAll() {
        //DropManager dm = dropGenerator.createGenericBasicDrop(mg.makeMeleeRat(null, null));
        final Actor ref = mg.makeMeleeRat(null, null);
        final List<DropManager> generators =List.of(
            dropGenerator.createGenericBasicDrop(ref),
            dropGenerator.createGenericPoorDrop(ref), 
            dropGenerator.createGenericRichDrop(ref));
        // assertDoesNotThrow(() -> {
        //     generators.addAll(dice.ints(ATTEMPS, 0, factoryMethod.size())
        //         .mapToObj(i -> factoryMethod.get(i))
        //         .map(m -> (DropManager) m.invoke(dropGenerator))
        //         .collect(Collectors.toList()));
        // });
        
        //DropManager dmm = dropGenerator.createGenericBasicDrop(mg.makeMeleeRat(null, null));
        // assertFalse(() -> generators.
        // dm.generateRandomConsumableDrop().size() > dm.generateConsumableDrop().size());
        boolean test = dice.ints(ATTEMPS, 0, generators.size())
            .anyMatch(i -> generators.get(i).generateAllDrop().size() < generators.get(i).generateRandomDrop().size());
        assertFalse(test);
        // assertFalse(generators.stream()
        //     .anyMatch(dm -> dm.generateRandomConsumableDrop().size() > dm.generateConsumableDrop().size()));
    }

    @Test
    public void generateDropForRoom() {
        final int size = 10;
        final int depth = 2;
        Room myRoom = new RectangleRoomImpl(size, size);
        DropManager mine = dropGenerator.createDropForRoom(myRoom.getSize(), depth);
        int expected = (int) Math.sqrt(Math.min(myRoom.getSize().getX(),myRoom.getSize().getY())) * depth % 5;
        assertEquals(expected, mine.generateAllDrop().size());
    }

    public void checkRandomFunction() {
        final int size = 10;
        final int depth = 2;
        final Room myRoom = new RectangleRoomImpl(size, size);
        final DropManager mine = dropGenerator.createDropForRoom(myRoom.getSize(), depth);
        List<List<Item>> loots = Stream.generate(() -> mine.generateRandomDrop()).toList();
        assertFalse(IntStream.range(0, loots.size()-1).anyMatch(i -> loots.get(i).equals(loots.get(i+1))));
    }

    
}
