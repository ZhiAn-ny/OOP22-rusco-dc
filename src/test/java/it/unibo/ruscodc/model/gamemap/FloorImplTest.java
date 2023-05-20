package it.unibo.ruscodc.model.gamemap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import it.unibo.ruscodc.model.Entity;
import it.unibo.ruscodc.utils.Pair;

import java.util.List;

import org.junit.jupiter.api.Test;

class FloorImplTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>default or parameterless constructor of {@link FloorImpl}
     *   <li>{@link FloorImpl#getCurrentRoom()}
     * </ul>
     */
    @Test
    void testConstructor() {
        FloorImpl actualFloorImpl = new FloorImpl();
        Room currentRoom = actualFloorImpl.getCurrentRoom();
        assertTrue(currentRoom instanceof RectangleRoomImpl);
        assertEquals(1, actualFloorImpl.getNRoomExplored());
        assertTrue(currentRoom.getMonsters().isEmpty());
        List<Entity> tilesAsEntity = currentRoom.getTilesAsEntity();
        assertEquals(49, tilesAsEntity.size());
        assertTrue(currentRoom.getObjectsInRoom().isEmpty());
        Pair<Integer, Integer> size = currentRoom.getSize();
        assertEquals(5, size.getY().intValue());
        assertEquals(5, size.getX().intValue());
        Entity getResult = tilesAsEntity.get(47);
        assertTrue(getResult instanceof WallTileImpl);
        Entity getResult1 = tilesAsEntity.get(1);
        assertTrue(getResult1 instanceof WallTileImpl);
        Entity getResult2 = tilesAsEntity.get(48);
        assertTrue(getResult2 instanceof WallTileImpl);
        Entity getResult3 = tilesAsEntity.get(0);
        assertTrue(getResult3 instanceof WallTileImpl);
        assertFalse(((WallTileImpl) getResult2).isTrap());
        assertFalse(((WallTileImpl) getResult3).isTrap());
        assertFalse(((WallTileImpl) getResult3).isAccessible());
        Pair<Integer, Integer> position = ((WallTileImpl) getResult3).getPosition();
        assertSame(position, getResult3.getPos());
        assertFalse(((WallTileImpl) getResult).get().isPresent());
        assertEquals("file:src/main/resources/it/unibo/ruscodc/map_res/WallTile/TOP_LEFT", getResult3.getPath());
        Pair<Integer, Integer> position1 = ((WallTileImpl) getResult).getPosition();
        assertSame(position1, getResult.getPos());
        assertEquals("wall", getResult3.getID());
        assertFalse(((WallTileImpl) getResult1).isAccessible());
        assertFalse(((WallTileImpl) getResult2).isAccessible());
        Pair<Integer, Integer> expectedPos = ((WallTileImpl) getResult2).getPosition();
        assertSame(expectedPos, getResult2.getPos());
        assertEquals("file:src/main/resources/it/unibo/ruscodc/map_res/WallTile/BOTTOM_RIGHT", getResult2.getPath());
        assertEquals("wall", getResult2.getID());
        assertFalse(((WallTileImpl) getResult).isTrap());
        Pair<Integer, Integer> position2 = ((WallTileImpl) getResult1).getPosition();
        assertSame(position2, getResult1.getPos());
        assertEquals("file:src/main/resources/it/unibo/ruscodc/map_res/WallTile/LEFT", getResult1.getPath());
        assertEquals("wall", getResult1.getID());
        assertFalse(((WallTileImpl) getResult).isAccessible());
        assertFalse(((WallTileImpl) getResult1).isTrap());
        assertEquals("wall", getResult.getID());
        assertEquals("file:src/main/resources/it/unibo/ruscodc/map_res/WallTile/RIGHT", getResult.getPath());
        assertEquals(0, position2.getX().intValue());
        assertEquals(0, position.getY().intValue());
        assertEquals(6, position1.getX().intValue());
        assertEquals(5, position1.getY().intValue());
        assertEquals(0, position.getX().intValue());
    }

    /**
     * Method under test: default or parameterless constructor of {@link FloorImpl}
     */
    @Test
    void testConstructor2() {
        FloorImpl actualFloorImpl = new FloorImpl();
        Room currentRoom = actualFloorImpl.getCurrentRoom();
        assertTrue(currentRoom instanceof RectangleRoomImpl);
        assertEquals(1, actualFloorImpl.getNRoomExplored());
        assertTrue(currentRoom.getMonsters().isEmpty());
        List<Entity> tilesAsEntity = currentRoom.getTilesAsEntity();
        assertEquals(49, tilesAsEntity.size());
        Entity getResult = tilesAsEntity.get(0);
        assertFalse(((WallTileImpl) getResult).isAccessible());
        Pair<Integer, Integer> position = ((WallTileImpl) getResult).getPosition();
        assertSame(position, getResult.getPos());
        assertEquals("file:src/main/resources/it/unibo/ruscodc/map_res/WallTile/TOP_LEFT", getResult.getPath());
        Entity getResult1 = tilesAsEntity.get(47);
        Pair<Integer, Integer> position1 = ((WallTileImpl) getResult1).getPosition();
        assertSame(position1, getResult1.getPos());
        Entity getResult2 = tilesAsEntity.get(1);
        assertFalse(((WallTileImpl) getResult2).isAccessible());
        Entity getResult3 = tilesAsEntity.get(48);
        assertFalse(((WallTileImpl) getResult3).isAccessible());
        Pair<Integer, Integer> expectedPos = ((WallTileImpl) getResult3).getPosition();
        assertSame(expectedPos, getResult3.getPos());
        assertEquals("file:src/main/resources/it/unibo/ruscodc/map_res/WallTile/BOTTOM_RIGHT", getResult3.getPath());
        Pair<Integer, Integer> position2 = ((WallTileImpl) getResult2).getPosition();
        assertSame(position2, getResult2.getPos());
        assertEquals("file:src/main/resources/it/unibo/ruscodc/map_res/WallTile/LEFT", getResult2.getPath());
        assertFalse(((WallTileImpl) getResult1).isAccessible());
        assertEquals("file:src/main/resources/it/unibo/ruscodc/map_res/WallTile/RIGHT", getResult1.getPath());
        assertEquals(0, position2.getX().intValue());
        assertEquals(0, position.getY().intValue());
        assertEquals(6, position1.getX().intValue());
        assertEquals(5, position1.getY().intValue());
        assertEquals(0, position.getX().intValue());
    }

    /**
     * Method under test: {@link FloorImpl#getNRoomExplored()}
     */
    @Test
    void testGetNRoomExplored() {
        assertEquals(1, (new FloorImpl()).getNRoomExplored());
    }
}

