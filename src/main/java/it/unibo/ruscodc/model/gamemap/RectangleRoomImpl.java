package it.unibo.ruscodc.model.gamemap;

import it.unibo.ruscodc.model.Actor;
import it.unibo.ruscodc.model.Entity;
import it.unibo.ruscodc.utils.Pair;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class RectangleRoomImpl implements Room {
    final private Pair<Integer, Integer> size;
    private Map<Pair<Integer, Integer>, Tile> tiles = new HashMap<Pair<Integer, Integer>, Tile>();

    public RectangleRoomImpl(int width, int height) {
        this.size = new Pair<Integer, Integer>(width, height);

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {

            }
        }
    }

    @Override
    public boolean isInRoom(Pair<Integer, Integer> pos) {
        return false;
    }

    @Override
    public Set<Actor> getMonsters() {
        return null;
    }

    @Override
    public boolean put(Pair<Integer, Integer> pos, Entity obj) {
        return false;
    }
}
