package it.unibo.ruscodc.model.actors.monster.drop;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import it.unibo.ruscodc.model.item.Item;

/**
 * Class that wrap a list of item and manage some type of access of these objects.
 */
public class DropManagerImpl implements DropManager {
    private static final Random DICE = new Random();
    private static final Predicate<Item> ONLY_E = i -> i.isWearable();
    private static final Predicate<Item> ONLY_C = i -> !(ONLY_E.test(i));
    private final List<Item> extractedDrop;

    /**
     * Create this managing system.
     * @param extractedDrop the all list of item, on which perform some type of extraction
     */
    public DropManagerImpl(final List<Item> extractedDrop) {
        this.extractedDrop = Collections.unmodifiableList(extractedDrop);
        //this.extractedDrop.addAll(extractedDrop);
    }

    /**
     * 
     */
    @Override
    public List<Item> generateAllDrop() {
        return new ArrayList<>(extractedDrop);
    }

    private List<Item> randomPicks(final List<Item> box) {
        final int dropSize = box.size();
        final int amountToDrop = (int) Math.round(DICE.nextDouble() * dropSize);
        //System.out.println(amountToDrop);
        return Stream.generate(() -> DICE.nextInt(dropSize))
            .limit(amountToDrop)
            .map(index -> extractedDrop.get(index))
            .collect(Collectors.toList());
    }

    /**
     * 
     */
    @Override
    public List<Item> generateRandomDrop() {
        return randomPicks(extractedDrop);
    }

    private List<Item> filteredDrop(final Predicate<Item> filter) {
        return extractedDrop.stream().filter(filter).collect(Collectors.toList());
    }

    /**
     * 
     */
    @Override
    public List<Item> generateConsumableDrop() {
        return filteredDrop(ONLY_C);
    }

    /**
     * 
     */
    @Override
    public List<Item> generateEquipDrop() {
        return filteredDrop(ONLY_E);
    }

    /**
     * 
     */
    @Override
    public List<Item> generateRandomConsumableDrop() {
        return randomPicks(generateConsumableDrop());
    }

    /**
     * 
     */
    @Override
    public List<Item> generateRandomEquipDrop() {
        return randomPicks(generateEquipDrop());
    }

}
