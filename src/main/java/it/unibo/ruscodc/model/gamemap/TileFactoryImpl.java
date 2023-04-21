package it.unibo.ruscodc.model.gamemap;

import it.unibo.ruscodc.utils.Pair;

public class TileFactoryImpl implements TileFactory {

    @Override
    public Tile createBaseFloorTile(int x, int y) {
        return new BaseTileImpl(new Pair<>(x, y), true);
    }

    @Override
    public Tile createBaseWallTile(int x, int y) {
        return new BaseTileImpl(new Pair<>(x, y), false);
    }
}
