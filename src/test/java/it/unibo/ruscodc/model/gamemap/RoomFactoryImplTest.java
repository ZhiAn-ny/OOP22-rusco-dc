package it.unibo.ruscodc.model.gamemap;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class RoomFactoryImplTest {
    /**
     * Method under test: {@link RoomFactoryImpl#randomRoom()}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testRandomRoom() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.UnsupportedOperationException: Unimplemented method 'makeMeleeAggressive'
        //       at it.unibo.ruscodc.model.actors.monster.behaviour.BehaviourFactoryImpl.makeMeleeAggressive(BehaviourFactoryImpl.java:8)
        //       at it.unibo.ruscodc.model.actors.monster.MonsterGeneratorImpl.makeMeleeRat(MonsterGeneratorImpl.java:29)
        //       at it.unibo.ruscodc.model.gamemap.RoomFactoryImpl.addMonsters(RoomFactoryImpl.java:58)
        //       at it.unibo.ruscodc.model.gamemap.RoomFactoryImpl.randomRoom(RoomFactoryImpl.java:25)
        //   In order to prevent randomRoom()
        //   from throwing UnsupportedOperationException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   randomRoom().
        //   See https://diff.blue/R013 to resolve this issue.

        (new RoomFactoryImpl()).randomRoom();
    }

    /**
     * Method under test: {@link RoomFactoryImpl#randomRoomWithTraps()}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testRandomRoomWithTraps() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.UnsupportedOperationException: Unimplemented method 'makeMeleeAggressive'
        //       at it.unibo.ruscodc.model.actors.monster.behaviour.BehaviourFactoryImpl.makeMeleeAggressive(BehaviourFactoryImpl.java:8)
        //       at it.unibo.ruscodc.model.actors.monster.MonsterGeneratorImpl.makeMeleeRat(MonsterGeneratorImpl.java:29)
        //       at it.unibo.ruscodc.model.gamemap.RoomFactoryImpl.addMonsters(RoomFactoryImpl.java:58)
        //       at it.unibo.ruscodc.model.gamemap.RoomFactoryImpl.randomRoom(RoomFactoryImpl.java:25)
        //       at it.unibo.ruscodc.model.gamemap.RoomFactoryImpl.randomRoomWithTraps(RoomFactoryImpl.java:31)
        //   In order to prevent randomRoomWithTraps()
        //   from throwing UnsupportedOperationException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   randomRoomWithTraps().
        //   See https://diff.blue/R013 to resolve this issue.

        (new RoomFactoryImpl()).randomRoomWithTraps();
    }
}

