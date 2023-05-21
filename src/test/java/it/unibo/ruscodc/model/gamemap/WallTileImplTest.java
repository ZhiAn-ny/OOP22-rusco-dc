package it.unibo.ruscodc.model.gamemap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import it.unibo.ruscodc.model.interactable.Interactable;
import it.unibo.ruscodc.utils.Pair;
import org.junit.jupiter.api.Test;

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
        WallTileImpl wall = new WallTileImpl(pos, WallType.TOP);

        assertEquals("wall", wall.getID());
        assertEquals(pos, wall.getPosition());
        assertFalse(wall.isAccessible());
        assertFalse(wall.isTrap());
    }

    /**
     * Methods under test:
     * <ul>
     *     <li>
     *         {@link WallTileImpl#put(Interactable)}
     *     </li>
     *     <li>
     *         {@link WallTileImpl#get()}
     *     </li>
     *     <li>
     *         {@link WallTileImpl#empty()}
     *     </li>
     * </ul>
     */
    @Test
    void testObjectPlacement() {
        WallTileImpl wallTileImpl = new WallTileImpl(new Pair<>(2, 3), WallType.TOP);
        assertFalse(wallTileImpl.put(new FloorTrapTileImpl(new Pair<>(2, 3))));
        assertFalse(wallTileImpl.get().isPresent());
        assertFalse(wallTileImpl.empty().isPresent());
    }

    /**
     * Method under test: {@link WallTileImpl#getPath()}
     */
    @Test
    void testGetPath() {
        assertEquals("file:src/main/resources/it/unibo/ruscodc/map_res/WallTile/TOP",
                (new WallTileImpl(new Pair<>(2, 3), WallType.TOP)).getPath());
        assertEquals("file:src/main/resources/it/unibo/ruscodc/map_res/WallTile/LEFT",
                (new WallTileImpl(new Pair<>(2, 3), WallType.LEFT)).getPath());
        assertEquals("file:src/main/resources/it/unibo/ruscodc/map_res/WallTile/RIGHT",
                (new WallTileImpl(new Pair<>(2, 3), WallType.RIGHT)).getPath());
        assertEquals("file:src/main/resources/it/unibo/ruscodc/map_res/WallTile/BOTTOM",
                (new WallTileImpl(new Pair<>(2, 3), WallType.BOTTOM)).getPath());

        assertEquals("file:src/main/resources/it/unibo/ruscodc/map_res/WallTile/BOTTOM_LEFT",
                (new WallTileImpl(new Pair<>(2, 3), WallType.BOTTOM_LEFT)).getPath());
        assertEquals("file:src/main/resources/it/unibo/ruscodc/map_res/WallTile/BOTTOM_RIGHT",
                (new WallTileImpl(new Pair<>(2, 3), WallType.BOTTOM_RIGHT)).getPath());
        assertEquals("file:src/main/resources/it/unibo/ruscodc/map_res/WallTile/TOP_LEFT",
                (new WallTileImpl(new Pair<>(2, 3), WallType.TOP_LEFT)).getPath());
        assertEquals("file:src/main/resources/it/unibo/ruscodc/map_res/WallTile/TOP_RIGHT",
                (new WallTileImpl(new Pair<>(2, 3), WallType.TOP_RIGHT)).getPath());
    }
}

