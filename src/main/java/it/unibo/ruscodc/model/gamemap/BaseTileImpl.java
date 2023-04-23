package it.unibo.ruscodc.model.gamemap;

import it.unibo.ruscodc.model.Entity;
import it.unibo.ruscodc.utils.Pair;

import java.util.Optional;
import java.util.Random;

public class BaseTileImpl implements Tile, Entity {
    private final Pair<Integer, Integer> position;
    private boolean isAccessible = true;
    private Entity content = null;

    /**
     * Creates a <code>Tile</code> at the specified position and sets its accessibility.
     * @param position the position of the tile in the room
     * @param accessibility whether the tile can be accessed or not by the player
     */
    public BaseTileImpl(final Pair<Integer, Integer> position, final boolean accessibility) {
        this.position = position;
        this.isAccessible = accessibility;
    }

    @Override
    public Pair<Integer, Integer> getPosition() {
        return this.position;
    }

    @Override
    public boolean isAccessible() {
        return this.isAccessible;
    }

    @Override
    public boolean put(final Entity obj) {
        if (this.content != null)
            return false;

        this.content = obj;
        return true;
    }

    @Override
    public Optional<Entity> get() {
        return Optional.ofNullable(this.content);
    }

    @Override
    public Optional<Entity> empty() {
        Optional<Entity> content = this.get();
        this.content = null;
        return content;
    }

    @Override
    public String getInfo() {
        Random rnd = new Random();
        return switch (rnd.nextInt(0, 5)) {
            case 0 -> "This floor is so dirty!";
            case 1 -> "Nothing to see here.";
            case 2 -> "Dust bunnies where are you?";
            case 3 -> "I wonder where the best trash is...";
            case 4 -> "No banana peels here! The path is clear!";
            default -> "Just some tiles...";
        };
    }

    @Override
    public String getPath() {
        // TODO: add image resource
        return "file:src/main/resources/it/unibo/ruscodc";
    }

    @Override
    public Pair<Integer, Integer> getPos() {
        return this.position;
    }
}
