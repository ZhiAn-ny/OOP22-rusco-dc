package it.unibo.ruscodc.model.actors.monster.drop;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.function.Supplier;
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
import it.unibo.ruscodc.utils.Pair;

/**
 * The implementation of DropFactory.
 */
public class DropFactoryImpl implements DropFactory {

    private static final Random DICE = new Random();
    private static final double RICH_COEFF = 1.5;
    private static final double POOR_COEFF = 0.5;
    private static final double GOLDEN_RATIO = (Math.sqrt(5) + 1) / 2;
    private static final int FLOOR_CYCLE = 5;
    private static final List<Double> STAT_COEFF = List.of(1.0, 1.0, 1.5, 1.0, 1.0, 1.0);

    private static final List<Supplier<Item>> LIS_E = new ArrayList<>();
    private static final List<Supplier<Item>> LIS_C = new ArrayList<>();

    private static boolean isInit;

    /**
     * Costructor of class. It init "static tables" if this class isn't loaded before 
     */
    public DropFactoryImpl() {
        if (!isInit) {
            init();
        }
    }

    private void fillList(final Set<Method> itemsToAdd, final Object receiver, final List<Supplier<Item>> toFill) {
        itemsToAdd.forEach(
            m -> toFill.add(
                () -> {
                    try {
                        return (Item) m.invoke(receiver);
                    } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                        return null;
                    }
                }
            )
        );
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

        fillList(consMethods, new ConsumableFactoryImpl(), LIS_C);
        fillList(equipMethods, new EquipementFactoryImpl(), LIS_E);
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

    private List<Item> createDrop(final List<Pair<List<Supplier<Item>>, Integer>> dropNeedList) {
        return dropNeedList.stream()
            .flatMap(p -> Stream.iterate(0, i -> i < p.getY(), i -> i + 1)
                .map(i -> p.getX().get(DICE.nextInt(p.getX().size())).get())
                )
            .collect(Collectors.toList());
    }

    private DropManager createMischellaneus(final int total) {
        final int totalEquipment = (int) (total * (2 - GOLDEN_RATIO));
        return new DropManagerImpl(createDrop(List.of(
                new Pair<>(LIS_C, total - totalEquipment),
                new Pair<>(LIS_E, totalEquipment))));
    } 

    private DropManager createOnlyConsumable(final int total) {
        return new DropManagerImpl(createDrop(List.of(new Pair<>(LIS_C, total))));
    } 

    private DropManager creationManager(final int amountValue) {
        final int total = byValueToAmountItems(amountValue);
        if (total < 0) {
            throw new IllegalStateException("amountValue cannot be negative!");
        }
        if (total % 2 == 0) {
            return createMischellaneus(total);
        } else {
            return createOnlyConsumable(total);
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
        return ((int) Math.sqrt(Math.min(roomSize.getX(), roomSize.getY()))) * (floorDepth % FLOOR_CYCLE);
    }

    /**
     * 
     */
    @Override
    public DropManager createDropForRoom(final Pair<Integer, Integer> roomSize, final int floorDepth) {
        return createMischellaneus(computeRoomMaxItem(roomSize, floorDepth));
    }

}
