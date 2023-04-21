package it.unibo.ruscodc.model.gamemap;

import it.unibo.ruscodc.utils.Pair;

public class TileFactoryImpl implements TileFactory {

    @Override
    public Tile createBaseFloorTile(Pair<Integer, Integer> pos) {
        return new BaseTileImpl(pos, true);
    }

    @Override
    public Tile createBaseWallTile(Pair<Integer, Integer> pos) {
        return new BaseTileImpl(pos, false);
    }
}
