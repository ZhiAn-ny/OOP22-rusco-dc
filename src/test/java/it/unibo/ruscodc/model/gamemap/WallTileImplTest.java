package it.unibo.ruscodc.model.gamemap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import it.unibo.ruscodc.model.interactable.Chest;
import it.unibo.ruscodc.model.interactable.Interactable;
import it.unibo.ruscodc.utils.Pair;
import org.junit.jupiter.api.Test;

import java.util.Set;

class WallTileImplTest {
    /**
     * Method under test: default or parameterless constructor of {@link WallTileImpl}.
     */
    @Test
    void testConstructor() {
        final Pair<Integer, Integer> pos = new Pair<>(2, 3);
        final WallTileImpl wallTile = new WallTileImpl(pos, WallType.TOP);

        assertEquals(1, wallTile.getID());
        assertEquals(pos, wallTile.getPosition());
        assertFalse(wallTile.isTrap());
    }

    /**
     * Method under test: {@link WallTileImpl#getPath()}.
     */
    @Test
    void testIsAccessible() {
        final Pair<Integer, Integer> pos = new Pair<>(2, 3);
        final WallTileImpl wallTile = new WallTileImpl(pos, WallType.TOP);
        assertFalse(wallTile.isAccessible());
    }

    /**
     * Tests object placement over WallTile.
     * Methods under test:
     * <ul>
     *   <li>{@link WallTileImpl#put(Interactable)}
     *   <li>{@link WallTileImpl#get()}
     *   <li>{@link WallTileImpl#empty()}
     * </ul>
     */
    @Test
    void testObjectPlacement() {
        final Pair<Integer, Integer> pos = new Pair<>(2, 3);
        final WallTileImpl wallTile = new WallTileImpl(pos, WallType.TOP);
        final Interactable obj = new Chest(Set.of(), pos);
        assertFalse(wallTile.put(obj));
        assertFalse(wallTile.get().isPresent());
        assertFalse(wallTile.empty().isPresent());
    }

    /**
     * Method under test: {@link WallTileImpl#getPath()}.
     */
    @Test
    void testGetPath() {
        final Pair<Integer, Integer> pos = new Pair<>(2, 3);
        assertEquals("it/unibo/ruscodc/map_res/WallTile/TOP",
                new WallTileImpl(pos, WallType.TOP).getPath());
        assertEquals("it/unibo/ruscodc/map_res/WallTile/LEFT",
                new WallTileImpl(pos, WallType.LEFT).getPath());
        assertEquals("it/unibo/ruscodc/map_res/WallTile/RIGHT",
                new WallTileImpl(pos, WallType.RIGHT).getPath());
        assertEquals("it/unibo/ruscodc/map_res/WallTile/BOTTOM",
                new WallTileImpl(pos, WallType.BOTTOM).getPath());

        assertEquals("it/unibo/ruscodc/map_res/WallTile/BOTTOM_LEFT",
                new WallTileImpl(pos, WallType.BOTTOM_LEFT).getPath());
        assertEquals("it/unibo/ruscodc/map_res/WallTile/BOTTOM_RIGHT",
                new WallTileImpl(pos, WallType.BOTTOM_RIGHT).getPath());
        assertEquals("it/unibo/ruscodc/map_res/WallTile/TOP_LEFT",
                new WallTileImpl(pos, WallType.TOP_LEFT).getPath());
        assertEquals("it/unibo/ruscodc/map_res/WallTile/TOP_RIGHT",
                new WallTileImpl(pos, WallType.TOP_RIGHT).getPath());
    }
}

