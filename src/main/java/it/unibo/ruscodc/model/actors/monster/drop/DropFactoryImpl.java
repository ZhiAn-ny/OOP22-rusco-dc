package it.unibo.ruscodc.model.actors.monster.drop;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import it.unibo.ruscodc.model.actors.Actor;
import it.unibo.ruscodc.model.actors.stat.StatImpl.StatName;
import it.unibo.ruscodc.model.item.Item;
import it.unibo.ruscodc.model.item.Consumable.ConsumableFactory;
import it.unibo.ruscodc.model.item.Consumable.ConsumableFactoryImpl;
import it.unibo.ruscodc.model.item.Equipement.EquipementFactory;
import it.unibo.ruscodc.model.item.Equipement.EquipementFactoryImpl;
import it.unibo.ruscodc.utils.MyIterator;
import it.unibo.ruscodc.utils.Pair;

/**
 * TODO
 */
@Deprecated
public class DropFactoryImpl  {

    private final static Random DICE = new Random(); //123456789
    private final static double RICH_COEFF = 1.5;
    private final static double POOR_COEFF = 0.5;
    private final static double GOLDEN_RATIO = (Math.sqrt(5) + 1) / 2;
    private final static List<Double> STAT_COEFF = List.of(0.5,0.25,0.25,0.25,0.1,0.1);
    private final static Map<Integer, Item> TAB_E = new HashMap<>();
    private final static Map<Integer, Item> TAB_C = new HashMap<>();
    //private final static EquipementFactory GEN_E = new EquipementFactoryImpl();
    //private final static ConsumableFactory GEN_C = new ConsumableFactoryImpl();
    private static boolean isInit;

    public DropFactoryImpl() {
        if (!isInit) {
            init();
        }
        isInit = true;
    }

    private void fillTable(Set<Method> itemsToAdd, Object receiver, Map<Integer, Item> toFill) {
        final MyIterator<Integer> myIt = new MyIterator<>(Stream.iterate(0, i -> i+1).iterator());
        itemsToAdd.forEach(m -> {
            try {
                toFill.put(myIt.getAct(), (Item) m.invoke(receiver));
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                e.printStackTrace();
            }
            myIt.next();           
        });

    }

    private void init() {
        Method eM[] = EquipementFactory.class.getMethods();
        Method cM[] = ConsumableFactory.class.getMethods();
        Method oM[] = Object.class.getMethods();

        Set<Method> equpMethods = IntStream.range(0, eM.length)
            .mapToObj(i -> eM[i]).collect(Collectors.toSet());
        Set<Method> consMethods = IntStream.range(0, cM.length)
            .mapToObj(i -> cM[i]).collect(Collectors.toSet());
        Set<Method> objMethods = IntStream.range(0, oM.length)
            .mapToObj(i -> oM[i]).collect(Collectors.toSet());

        equpMethods.removeAll(objMethods);
        consMethods.removeAll(objMethods);

        fillTable(equpMethods, new EquipementFactoryImpl(), TAB_E);
        fillTable(consMethods, new ConsumableFactoryImpl(), TAB_C);

        //TAB_E.forEach( (a,b) -> System.out.println("K: " + a.toString() + " / V: " + b.getName()));
        //TAB_C.forEach((a,b) -> System.out.println("K: " + a.toString() + " / V: " + b.getName()));
        // final MyIterator<Integer> myIt = new MyIterator<>(Stream.iterate(0, i -> i+1).iterator());
        // provMethods.forEach(m -> {
        //     try {
        //         TAB_E.put(myIt.getAct(), (Item) m.invoke(GEN_E));
        //     } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
        //         e.printStackTrace();
        //     }
        //     myIt.next();           
        // });
        // myIt.reset();
        
    }

    private int computeActorValue(final Actor by) {
        final int amountS = StatName.values().length;
        if (amountS != STAT_COEFF.size()) {
            throw new IllegalStateException("Maybe added new stat but in DropFactoryImpl there are too many or to few coefficents");
        }

        List<Integer> value = Stream.iterate(0, i -> i<amountS, i -> i+1)
            .map(i -> StatName.values()[i])
            .map(st -> by.getStatMax(st))
            .toList();
        
        return IntStream.range(0, amountS).map(i -> (int) (value.get(i) * STAT_COEFF.get(i))).sum();
    }

    private int byValueToAmountItems(final int toConvert){
        final double value = Math.sqrt(toConvert) * GOLDEN_RATIO;
        final int finValue = DICE.nextBoolean() ? (int) value : (int) (value - Math.sqrt(toConvert));
        return finValue < 0 ? 0 : finValue;
    }

    private List<Item> createDrop(final List<Pair<Map<Integer, Item>, Integer>> tables) {
        return tables.stream()
            .flatMap(p -> Stream.iterate(0, i -> i < p.getY(), i -> i+1)
                .map(i -> p.getX().get(DICE.nextInt(p.getX().size())))
                )
            .collect(Collectors.toList());
    }

    private DropManager createMischellaneus(final int amountValue) {
        return null;
        // () -> {
        //     int total = byValueToAmountItems(amountValue);
            
        //     int totalEquipment = (int) (total * (2 - GOLDEN_RATIO));
        //     return createDrop(List.of(
        //         new Pair<>(TAB_C, total - totalEquipment),
        //         new Pair<>(TAB_E, totalEquipment)));
        // };
    } 

    private DropManager createOnlyConsumable(final int amountValue) {
        return null;
        // () -> {
        //     int total = byValueToAmountItems(amountValue);
        //     return createDrop(List.of(new Pair<>(TAB_C, total)));
        // };
    } 

    private DropManager creationManager(final int amountValue) {
        if (amountValue % 2 == 1) {
            return createOnlyConsumable(amountValue);
        } else {
            return createMischellaneus(amountValue);
        }
    }

    //@Override
    public DropManager createGenericBasicDrop(Actor by) {
        return creationManager(computeActorValue(by));
    }

    //@Override
    public DropManager createGenericRichDrop(Actor by) {
        return creationManager((int) (computeActorValue(by) * RICH_COEFF));
    }

    //@Override
    public DropManager createGenericPoorDrop(Actor by) {
        return creationManager((int) (computeActorValue(by) * POOR_COEFF));
    }
    
    // public boolean isInitForTest() {
    //     return isInit;
    // }
}
