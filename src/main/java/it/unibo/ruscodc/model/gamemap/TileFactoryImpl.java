package it.unibo.ruscodc.model.gamemap;

import it.unibo.ruscodc.utils.Pair;

public class TileFactoryImpl implements TileFactory {

    @Override
    public Tile createBaseFloorTile(int x, int y) {
        return new FloorTileImpl(new Pair<>(x, y), true);
    }

    @Override
    public Tile createBaseWallTile(int x, int y) {
        return new WallTileImpl(new Pair<>(x, y));
    }
}
