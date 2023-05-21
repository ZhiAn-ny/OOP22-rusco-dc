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
     * Methods under test:
     *
     * <ul>
     *   <li>{@link WallTileImpl#WallTileImpl(Pair, WallType)}
     *   <li>{@link WallTileImpl#getID()}
     * </ul>
     */
    @Test
    void testConstructor() {
        final Pair<Integer, Integer> pos = new Pair<>(2, 3);
        final WallTileImpl wallTile = new WallTileImpl(pos, WallType.TOP);

        assertEquals("wall", wallTile.getID());
        assertEquals(pos, wallTile.getPosition());
        assertFalse(wallTile.isAccessible());
        assertFalse(wallTile.isTrap());
    }

    /**
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
        assertFalse(wallTile.put(new Chest(Set.of(), pos)));
        assertFalse(wallTile.get().isPresent());
        assertFalse(wallTile.empty().isPresent());
    }

    /**
     * Method under test: {@link WallTileImpl#getPath()}
     */
    @Test
    void testGetPath() {
        final Pair<Integer, Integer> pos = new Pair<>(2, 3);
        assertEquals("file:src/main/resources/it/unibo/ruscodc/map_res/WallTile/TOP",
                (new WallTileImpl(pos, WallType.TOP)).getPath());
        assertEquals("file:src/main/resources/it/unibo/ruscodc/map_res/WallTile/LEFT",
                (new WallTileImpl(pos, WallType.LEFT)).getPath());
        assertEquals("file:src/main/resources/it/unibo/ruscodc/map_res/WallTile/RIGHT",
                (new WallTileImpl(pos, WallType.RIGHT)).getPath());
        assertEquals("file:src/main/resources/it/unibo/ruscodc/map_res/WallTile/BOTTOM",
                (new WallTileImpl(pos, WallType.BOTTOM)).getPath());

        assertEquals("file:src/main/resources/it/unibo/ruscodc/map_res/WallTile/BOTTOM_LEFT",
                (new WallTileImpl(pos, WallType.BOTTOM_LEFT)).getPath());
        assertEquals("file:src/main/resources/it/unibo/ruscodc/map_res/WallTile/BOTTOM_RIGHT",
                (new WallTileImpl(pos, WallType.BOTTOM_RIGHT)).getPath());
        assertEquals("file:src/main/resources/it/unibo/ruscodc/map_res/WallTile/TOP_LEFT",
                (new WallTileImpl(pos, WallType.TOP_LEFT)).getPath());
        assertEquals("file:src/main/resources/it/unibo/ruscodc/map_res/WallTile/TOP_RIGHT",
                (new WallTileImpl(pos, WallType.TOP_RIGHT)).getPath());
    }
}

