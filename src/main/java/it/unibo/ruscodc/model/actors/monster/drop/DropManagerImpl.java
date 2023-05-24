package it.unibo.ruscodc.model.actors.monster.drop;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import it.unibo.ruscodc.model.item.Item;

public class DropManagerImpl implements DropManager {
    private final static Random DICE = new Random();
    private final static Predicate<Item> ONLY_E = i -> i.isWearable();
    private final static Predicate<Item> ONLY_C = i -> !(ONLY_E.test(i));
    private final List<Item> extractedDrop;

    public DropManagerImpl(List<Item> extractedDrop) {
        this.extractedDrop = extractedDrop;
    }

    @Override
    public List<Item> generateAllDrop() {
        return new ArrayList<>(extractedDrop);
    }

    private List<Item> randomPicks(final List<Item> box){
        final int dropSize = box.size();
        final int amountToDrop = (int) (DICE.nextDouble() * dropSize);
        System.out.println(amountToDrop);
        return Stream.generate(() -> DICE.nextInt(dropSize))
            .limit(amountToDrop)
            .map(index -> extractedDrop.get(index))
            .collect(Collectors.toList());
    }

    @Override
    public List<Item> generateRandomDrop() {
        return randomPicks(extractedDrop);
    }

    private List<Item> filteredDrop(Predicate<Item> filter) {
        return extractedDrop.stream().filter(filter).collect(Collectors.toList());
    }

    @Override
    public List<Item> generateConsumableDrop() {
        return filteredDrop(ONLY_C);
    }

    @Override
    public List<Item> generateEquipDrop() {
        return filteredDrop(ONLY_E);
    }

    @Override
    public List<Item> generateRandomConsumableDrop() {
        return randomPicks(generateConsumableDrop());
    }

    @Override
    public List<Item> generateRandomEquipDrop() {
        return randomPicks(generateEquipDrop());
    }

    
}
