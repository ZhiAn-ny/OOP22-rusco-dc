package it.unibo.ruscodc.model.gamemap;

import it.unibo.ruscodc.model.actors.Actor;
import it.unibo.ruscodc.model.actors.hero.HeroImpl;
import it.unibo.ruscodc.model.actors.monster.MonsterActionFactory;
import it.unibo.ruscodc.model.actors.monster.MonsterActionFactoryImpl;
import it.unibo.ruscodc.model.actors.skill.Skill;
import it.unibo.ruscodc.model.actors.skill.SkillImpl;
import it.unibo.ruscodc.model.actors.stat.StatFactory;
import it.unibo.ruscodc.model.actors.stat.StatFactoryImpl;
import it.unibo.ruscodc.model.actors.stat.StatImpl;
import it.unibo.ruscodc.utils.GameControl;
import it.unibo.ruscodc.utils.Pair;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.security.InvalidParameterException;

import static org.junit.jupiter.api.Assertions.*;

class TileFactoryImplTest {
    private Actor getActor(final Pair<Integer, Integer> pos) {
        final StatFactory stats = new StatFactoryImpl();
        final MonsterActionFactory MAFactory = new MonsterActionFactoryImpl();
        final Skill skills = new SkillImpl();
        skills.setAction(GameControl.ATTACK1, MAFactory.basicMeleeAttack());
        skills.setAction(GameControl.ATTACK2, MAFactory.heavyMeleeAttack());
        return new HeroImpl("testHero", pos, skills, stats.ratStat());
    }

    /**
     * Method under test: {@link TileFactoryImpl#createSingleUseFloorTrap(int, int)}
     */
    @Test
    void testCreateSingleUseFloorTrap() {
        final Pair<Integer, Integer> pos = new Pair<>(2, 3);
        final Tile singleUseFloorTrap = (new TileFactoryImpl()).createSingleUseFloorTrap(2, 3);
        final Actor actor = this.getActor(pos);
        int hp = actor.getStatInfo(StatImpl.StatName.HP);

        assertTrue(singleUseFloorTrap.isTrap());
        singleUseFloorTrap.getEffect().applyEffect(actor);
        assertTrue(hp > actor.getStatInfo(StatImpl.StatName.HP));

        hp = actor.getStatInfo(StatImpl.StatName.HP);
        singleUseFloorTrap.getEffect().applyEffect(actor);
        assertEquals(hp, actor.getStatInfo(StatImpl.StatName.HP));
    }

    /**
     * Method under test: {@link TileFactoryImpl#createFloorTrap(int, int)}
     */
    @Test
    void testCreateFloorTrap() {
        final Pair<Integer, Integer> pos = new Pair<>(2, 3);
        final Tile floorTrap = new TileFactoryImpl().createFloorTrap(2, 3);
        final Actor actor = this.getActor(pos);

        assertTrue(floorTrap.isTrap());
        for (int i = 0; i < 3; i++) {
            final int hp = actor.getStatInfo(StatImpl.StatName.HP);
            floorTrap.getEffect().applyEffect(actor);
            assertTrue(hp > actor.getStatInfo(StatImpl.StatName.HP));
        }
    }

    /**
     * Method under test: {@link TileFactoryImpl#createBaseFloorTile(int, int)}
     */
    @Test
    void testCreateBaseFloorTile() {
        final Tile floorTile = new TileFactoryImpl().createBaseFloorTile(2, 3);
        assertFalse(floorTile.isTrap());
        assertTrue(floorTile.isAccessible());
    }

    /**
     * Method under test: {@link TileFactoryImpl#createRandomFloorTile(int, int)}
     */
    @Test
    void testCreateRandomFloorTile() {
        final Tile randomFloor = new TileFactoryImpl().createRandomFloorTile(2, 3);
        assertTrue(randomFloor.isAccessible());
    }

    /**
     * Method under test: {@link TileFactoryImpl#createBaseWallTile(int, int, Pair)}
     */
    @Test
    void testCreateBaseWallTileTopWall() {
        final TileFactoryImpl tf = new TileFactoryImpl();
        final Pair<Integer, Integer> roomSize = new Pair<>(3, 3);

        for (int x = 0; x <= roomSize.getX() + 1; x++) {
            final Tile wall = tf.createBaseWallTile(x, 0, roomSize);
            assertFalse(wall.isAccessible());

            if (x == 0) {
                assertEquals("file:src/main/resources/it/unibo/ruscodc/map_res/WallTile/TOP_LEFT",
                        ((WallTileImpl) wall).getPath());
            } else if (x == roomSize.getX() + 1) {
                assertEquals("file:src/main/resources/it/unibo/ruscodc/map_res/WallTile/TOP_RIGHT",
                        ((WallTileImpl) wall).getPath());
            } else {
                assertEquals("file:src/main/resources/it/unibo/ruscodc/map_res/WallTile/TOP",
                        ((WallTileImpl) wall).getPath());
            }
        }
    }

    /**
     * Method under test: {@link TileFactoryImpl#createBaseWallTile(int, int, Pair)}
     */
    @Test
    void testCreateBaseWallTileBottomWall() {
        final TileFactoryImpl tf = new TileFactoryImpl();
        final Pair<Integer, Integer> roomSize = new Pair<>(3, 3);

        for (int x = 0; x <= roomSize.getX() + 1; x++) {
            final Tile wall = tf.createBaseWallTile(x, roomSize.getY() + 1, roomSize);
            assertFalse(wall.isAccessible());

            if (x == 0) {
                assertEquals("file:src/main/resources/it/unibo/ruscodc/map_res/WallTile/BOTTOM_LEFT",
                        ((WallTileImpl) wall).getPath());
            } else if (x == roomSize.getX() + 1) {
                assertEquals("file:src/main/resources/it/unibo/ruscodc/map_res/WallTile/BOTTOM_RIGHT",
                        ((WallTileImpl) wall).getPath());
            } else {
                assertEquals("file:src/main/resources/it/unibo/ruscodc/map_res/WallTile/BOTTOM",
                        ((WallTileImpl) wall).getPath());
            }
        }
    }

    /**
     * Method under test: {@link TileFactoryImpl#createBaseWallTile(int, int, Pair)}
     */
    @Test
    void testCreateBaseWallTileLeftWall() {
        final TileFactoryImpl tf = new TileFactoryImpl();
        final Pair<Integer, Integer> roomSize = new Pair<>(3, 3);

        for (int y = 0; y <= roomSize.getX() + 1; y++) {
            final Tile wall = tf.createBaseWallTile(0, y, roomSize);
            assertFalse(wall.isAccessible());

            if (y == 0) {
                assertEquals("file:src/main/resources/it/unibo/ruscodc/map_res/WallTile/TOP_LEFT",
                        ((WallTileImpl) wall).getPath());
            } else if (y == roomSize.getX() + 1) {
                assertEquals("file:src/main/resources/it/unibo/ruscodc/map_res/WallTile/BOTTOM_LEFT",
                        ((WallTileImpl) wall).getPath());
            } else {
                assertEquals("file:src/main/resources/it/unibo/ruscodc/map_res/WallTile/LEFT",
                        ((WallTileImpl) wall).getPath());
            }
        }
    }

    /**
     * Method under test: {@link TileFactoryImpl#createBaseWallTile(int, int, Pair)}
     */
    @Test
    void testCreateBaseWallTileRightWall() {
        final TileFactoryImpl tf = new TileFactoryImpl();
        final Pair<Integer, Integer> roomSize = new Pair<>(3, 3);

        for (int y = 0; y <= roomSize.getX() + 1; y++) {
            final Tile wall = tf.createBaseWallTile(roomSize.getX() + 1, y, roomSize);
            assertFalse(wall.isAccessible());

            if (y == 0) {
                assertEquals("file:src/main/resources/it/unibo/ruscodc/map_res/WallTile/TOP_RIGHT",
                        ((WallTileImpl) wall).getPath());
            } else if (y == roomSize.getX() + 1) {
                assertEquals("file:src/main/resources/it/unibo/ruscodc/map_res/WallTile/BOTTOM_RIGHT",
                        ((WallTileImpl) wall).getPath());
            } else {
                assertEquals("file:src/main/resources/it/unibo/ruscodc/map_res/WallTile/RIGHT",
                        ((WallTileImpl) wall).getPath());
            }
        }
    }

    /**
     * Method under test: {@link TileFactoryImpl#createBaseWallTile(int, int, Pair)}
     */
    @Test
    void testCreateBaseWallTileInsideRoom() {
        final TileFactoryImpl tf = new TileFactoryImpl();
        final Pair<Integer, Integer> roomSize = new Pair<>(3, 3);
        final Tile wall = tf.createBaseWallTile(3, 2, roomSize);

        assertFalse(wall.isAccessible());
        assertEquals("file:src/main/resources/it/unibo/ruscodc/map_res/WallTile/UNDEFINED",
                ((WallTileImpl) wall).getPath());
    }

    /**
     * Method under test: {@link TileFactoryImpl#createBaseWallTile(int, int, Pair)}
     */
    @Test
    void testCreateBaseWallTileOutsideRoomUnaligned() {
        final TileFactoryImpl tf = new TileFactoryImpl();
        final Pair<Integer, Integer> roomSize = new Pair<>(3, 3);
        final Tile wall = tf.createBaseWallTile(-9, 9, roomSize);

        assertFalse(wall.isAccessible());
        assertEquals("file:src/main/resources/it/unibo/ruscodc/map_res/WallTile/UNDEFINED",
                ((WallTileImpl) wall).getPath());
    }

    /**
     * Method under test: {@link TileFactoryImpl#createBaseWallTile(int, int, Pair)}
     */
    @Test
    void testCreateBaseWallTileOutsideRoomAligned() {
        final TileFactoryImpl tf = new TileFactoryImpl();
        final Pair<Integer, Integer> roomSize = new Pair<>(3, 3);
        final Tile wall = tf.createBaseWallTile(4, 5, roomSize);

        assertFalse(wall.isAccessible());
        assertEquals("file:src/main/resources/it/unibo/ruscodc/map_res/WallTile/UNDEFINED",
                ((WallTileImpl) wall).getPath());
    }

    /**
     * Method under test: {@link TileFactoryImpl#createBaseWallTile(int, int, Pair)}
     */
    @Test
    void testCreateBaseWallTileNullRoomSize() {
        assertThrows(IllegalArgumentException.class,
                () ->  (new TileFactoryImpl()).createBaseWallTile(2, 0, null));
    }
}

