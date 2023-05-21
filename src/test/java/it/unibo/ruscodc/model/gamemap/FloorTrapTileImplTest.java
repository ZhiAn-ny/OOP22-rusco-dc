package it.unibo.ruscodc.model.gamemap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import it.unibo.ruscodc.utils.Pair;

import java.util.function.Consumer;

import org.junit.jupiter.api.Test;

class FloorTrapTileImplTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link FloorTrapTileImpl#FloorTrapTileImpl(Pair)}
     *   <li>{@link FloorTrapTileImpl#setDamage(int)}
     *   <li>{@link FloorTrapTileImpl#setDisableSuccessRate(int)}
     *   <li>{@link FloorTrapTileImpl#setPostTriggered(Consumer)}
     *   <li>{@link FloorTrapTileImpl#getName()}
     *   <li>{@link FloorTrapTileImpl#isTrap()}
     * </ul>
     */
    @Test
    void testConstructor() {
        FloorTrapTileImpl actualFloorTrapTileImpl = new FloorTrapTileImpl(new Pair<>(2, 3));
        actualFloorTrapTileImpl.setDamage(1);
        actualFloorTrapTileImpl.setDisableSuccessRate(1);
        actualFloorTrapTileImpl.setPostTriggered(null);
        assertEquals("It's a trap!", actualFloorTrapTileImpl.getName());
        assertTrue(actualFloorTrapTileImpl.isTrap());
    }

    /**
     * Method under test: {@link FloorTrapTileImpl#FloorTrapTileImpl(Pair)}
     */
    @Test
    void testConstructor2() {
        Pair<Integer, Integer> pair = new Pair<>(2, 3);

        FloorTrapTileImpl actualFloorTrapTileImpl = new FloorTrapTileImpl(pair);
        assertTrue(actualFloorTrapTileImpl.isAccessible());
        assertSame(pair, actualFloorTrapTileImpl.getPosition());
    }
}

