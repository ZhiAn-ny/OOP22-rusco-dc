package it.unibo.ruscodc.model.gamemap;

import it.unibo.ruscodc.model.Actor;
import it.unibo.ruscodc.model.Entity;
import it.unibo.ruscodc.utils.Pair;

import java.util.*;

public class RectangleRoomImpl implements Room {
    private final Pair<Integer, Integer> size;
    private final List<Tile> tiles = new ArrayList<>();
    private final Set<Actor> monsters = new HashSet<>();

    public RectangleRoomImpl(int width, int height) {
        this.size = new Pair<Integer, Integer>(width, height);
        this.addTiles();
    }

    /**
     * Creates the room's tiles.
     */
    private void addTiles() {
        TileFactory tf = new TileFactoryImpl();

        for (int i = 0; i < this.size.getX(); i++) {
            for (int j = 0; j < this.size.getY(); j++) {
                if (i == 0 || j == 0)
                    this.tiles.add(tf.createBaseWallTile(i, j));
                else
                    this.tiles.add(tf.createBaseFloorTile(i, j));
            }
        }
    }

    @Override
    public boolean isInRoom(Pair<Integer, Integer> pos) {
        return this.tiles.stream().anyMatch(t -> t.getPosition().equals(pos));
    }

    @Override
    public Set<Actor> getMonsters() {
        return this.monsters;
    }

    @Override
    public boolean put(Pair<Integer, Integer> pos, Entity obj) {
        Optional<Tile> tile = this.tiles.stream()
                .filter(t -> t.getPosition().equals(pos))
                .findFirst();
        if (tile.isEmpty())
            return false;
        return tile.get().put(obj);
    }

    @Override
    public boolean isAccessible(Pair<Integer, Integer> pos) {
        Optional<Tile> tile = this.tiles.stream()
                .filter(t -> t.getPosition().equals(pos))
                .findFirst();

        if (tile.isEmpty())
            return false;
        return tile.get().isAccessible();
    }
}
