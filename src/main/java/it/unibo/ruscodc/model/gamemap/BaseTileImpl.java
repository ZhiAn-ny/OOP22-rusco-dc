package it.unibo.ruscodc.model.gamemap;

import it.unibo.ruscodc.model.Entity;
import it.unibo.ruscodc.utils.Pair;
import it.unibo.ruscodc.view.AbstractJFXDrawableImpl;

import java.util.Optional;

public class BaseTileImpl extends AbstractJFXDrawableImpl implements Tile {
    private final Pair<Integer, Integer> position;
    private boolean isAccessible = true;
    private Entity content = null;

    public BaseTileImpl(Pair<Integer, Integer> position) {
        this.position = position;
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
    public boolean put(Entity obj) {
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
}
