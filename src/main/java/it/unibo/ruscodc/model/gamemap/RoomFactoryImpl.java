package it.unibo.ruscodc.model.gamemap;

import it.unibo.ruscodc.model.actors.monster.Monster;
import it.unibo.ruscodc.model.actors.monster.MonsterGenerator;
import it.unibo.ruscodc.model.actors.monster.MonsterGeneratorImpl;
import it.unibo.ruscodc.utils.Pair;

import java.util.Random;

/**
 * The <coode>RoomFactory</coode> class can be used to generate different
 * types of rooms in the game.
 */
public class RoomFactoryImpl implements RoomFactory {
    private final Random rnd = new Random();
    private static final int MIN_ROOM_SIZE = 3;
    private static final int MAX_ROOM_SIZE = 20;
    private static final int MAX_MONSTERS_NUM = 10;
    private final MonsterGenerator monsterGen = new MonsterGeneratorImpl();

    /** {@inheritDoc} */
    @Override
    public Room randomRoom() {
        final Room room = this.getRandomShapeRoom();
        this.addMonsters(room);
        return room;
    }

    private Room getRandomShapeRoom() {
        return (this.rnd.nextInt() % 2 == 0) ?
                this.squareRoom(this.rnd.nextInt(MIN_ROOM_SIZE, MAX_ROOM_SIZE)) :
                this.rectangleRoom(this.rnd.nextInt(MIN_ROOM_SIZE, MAX_ROOM_SIZE), this.rnd.nextInt(MIN_ROOM_SIZE, MAX_ROOM_SIZE));
    }

    private void addMonsters(final Room base) {
        final Random rnd = new Random();
        final int monstersNum = rnd.nextInt(MAX_MONSTERS_NUM);
        int i = 0;
        while (i < monstersNum) {
            Pair<Integer, Integer> pos = new Pair<>(
                    rnd.nextInt(base.getSize().getX()),
                    rnd.nextInt(base.getSize().getY())
            );
            // TODO: change to random
            Monster monster = this.monsterGen.makeMeleeRat("m" + i, pos);
            if (base.addMonster(monster)) {
                i = i + 1;
            }
        }
    }

    /** {@inheritDoc} */
    @Override
    public Room squareRoom(final int size) {
        return new RectangleRoomImpl(size, size);
    }

    /** {@inheritDoc} */
    @Override
    public Room rectangleRoom(final int width, final int height) {
        return new RectangleRoomImpl(width, height);
    }

// TODO:
//    /** {@inheritDoc} */
//    @Override
//    public Room stairsRoom() {
//        final Room base = this.randomRoom();
//        //base.addStairs(Direction.UNDEFINED);
//        return base;
//    }

}
