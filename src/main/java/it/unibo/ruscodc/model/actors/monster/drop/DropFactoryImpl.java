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
import it.unibo.ruscodc.model.item.consumable.ConsumableFactory;
import it.unibo.ruscodc.model.item.consumable.ConsumableFactoryImpl;
import it.unibo.ruscodc.model.item.equipement.EquipementFactory;
import it.unibo.ruscodc.model.item.equipement.EquipementFactoryImpl;
import it.unibo.ruscodc.utils.MyIterator;
import it.unibo.ruscodc.utils.Pair;

/**
 * TODO.
 */
public class DropFactoryImpl implements DropFactory {

    private static final Random DICE = new Random(); //123456789 - if I need a seed I'll use this number
    private static final double RICH_COEFF = 1.5;
    private static final double POOR_COEFF = 0.5;
    private static final double GOLDEN_RATIO = (Math.sqrt(5) + 1) / 2;
    private static final int ROOM_AMPLIFIER_BASE = 5;
    private static final List<Double> STAT_COEFF = List.of(1.0, 1.0, 1.5, 1.0, 1.0, 1.0);
    private static final Map<Integer, Item> TAB_E = new HashMap<>();
    private static final Map<Integer, Item> TAB_C = new HashMap<>();

    private static boolean isInit;

    /**
     * TODO.
     */
    public DropFactoryImpl() {
        if (!isInit) {
            init();
        }
    }

    private void fillTable(final Set<Method> itemsToAdd, final Object receiver, final Map<Integer, Item> toFill) {
        final MyIterator<Integer> myIt = new MyIterator<>(Stream.iterate(0, i -> i + 1).iterator());
        itemsToAdd.forEach(m -> {
            try {
                toFill.put(myIt.getAct(), (Item) m.invoke(receiver));
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                e.printStackTrace(); // TODO eventuale modo diverso di gestire questa cosa?
            }
            myIt.next();
        });
    }

    private void init() {
        final Method[] eM = EquipementFactory.class.getMethods();
        final Method[] cM = ConsumableFactory.class.getMethods();
        final Method[] oM = Object.class.getMethods();

        final Set<Method> equipMethods = IntStream.range(0, eM.length)
            .mapToObj(i -> eM[i]).collect(Collectors.toSet());
        final Set<Method> consMethods = IntStream.range(0, cM.length)
            .mapToObj(i -> cM[i]).collect(Collectors.toSet());
        final Set<Method> objMethods = IntStream.range(0, oM.length)
            .mapToObj(i -> oM[i]).collect(Collectors.toSet());

        equipMethods.removeAll(objMethods);
        consMethods.removeAll(objMethods);

        fillTable(equipMethods, new EquipementFactoryImpl(), TAB_E);
        fillTable(consMethods, new ConsumableFactoryImpl(), TAB_C);
        isInit = true;
    }

    private int computeActorValue(final Actor by) {
        final int amountS = StatName.values().length;
        if (amountS != STAT_COEFF.size()) {
            throw new IllegalStateException(
                "Maybe added new stat but in DropFactoryImpl there are too many or to few coefficents");
        }

        final List<Integer> value = Stream.iterate(0, i -> i < amountS, i -> i + 1)
            .map(i -> StatName.values()[i])
            .map(st -> by.getStatMax(st))
            .toList();

        return IntStream.range(0, amountS).map(i -> (int) (value.get(i) * STAT_COEFF.get(i))).sum();
    }

    private int byValueToAmountItems(final int toConvert) {
        final double value = Math.sqrt(toConvert) * GOLDEN_RATIO;
        final int finValue = DICE.nextBoolean() ? (int) value : (int) (value - Math.sqrt(toConvert));
        return finValue < 0 ? 0 : finValue;
    }

    private List<Item> createDrop(final List<Pair<Map<Integer, Item>, Integer>> tables) {
        return tables.stream()
            .flatMap(p -> Stream.iterate(0, i -> i < p.getY(), i -> i + 1)
                .map(i -> p.getX().get(DICE.nextInt(p.getX().size())))
                )
            .collect(Collectors.toList());
    }

    private DropManager createMischellaneus(final int total) {
        final int totalEquipment = (int) (total * (2 - GOLDEN_RATIO));
        return new DropManagerImpl(createDrop(List.of(
                new Pair<>(TAB_C, total - totalEquipment),
                new Pair<>(TAB_E, totalEquipment))));
    } 

    private DropManager createOnlyConsumable(final int total) {
        return new DropManagerImpl(createDrop(List.of(new Pair<>(TAB_C, total))));
    } 

    private DropManager creationManager(final int amountValue) {
        if (amountValue < 0) {
            throw new IllegalStateException("amountValue cannot be negative!");
        }
        final int total = byValueToAmountItems(amountValue);
        if (amountValue % 2 == 1) { //TODO warning spotBugs su "op non definita se n è neg" ma check fatto prima
            return createOnlyConsumable(total);
        } else {
            return createMischellaneus(total);
        }
    }

    /**
     * 
     */
    @Override
    public DropManager createGenericBasicDrop(final Actor by) {
        return creationManager(computeActorValue(by));
    }

    /**
     * 
     */
    @Override
    public DropManager createGenericRichDrop(final Actor by) {
        return creationManager((int) (computeActorValue(by) * RICH_COEFF));
    }

    /**
     * 
     */
    @Override
    public DropManager createGenericPoorDrop(final Actor by) {
        return creationManager((int) (computeActorValue(by) * POOR_COEFF));
    }

    private int computeRoomMaxItem(final Pair<Integer, Integer> roomSize, final int floorDepth) {
        return ((int) Math.sqrt(Math.min(roomSize.getX(), roomSize.getY()))) * (floorDepth % ROOM_AMPLIFIER_BASE);
    }

    /**
     * 
     */
    @Override
    public DropManager createDropForRoom(final Pair<Integer, Integer> roomSize, final int floorDepth) {
        return createMischellaneus(computeRoomMaxItem(roomSize, floorDepth));
    }

}
