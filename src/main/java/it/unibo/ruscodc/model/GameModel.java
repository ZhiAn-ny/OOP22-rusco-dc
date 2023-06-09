package it.unibo.ruscodc.model;

import it.unibo.ruscodc.model.actors.Actor;
import it.unibo.ruscodc.model.actors.monster.Monster;
import it.unibo.ruscodc.model.gamemap.Room;
import it.unibo.ruscodc.utils.Pair;
import it.unibo.ruscodc.utils.outputinfo.Portrait;

import java.util.List;

/**
 * It is a interface for the model. This process the input received.
 */
public interface GameModel {

    /**
     * order the actor with initiative.
     * @return the list of actor
     */
    List<Actor> getActorByInitative();

    /**
     *
     * @return the current tile.
     */
    List<Entity> getTile();

    /**
     *
     * @param toGet for kown his info.
     * @return the info of specific entity.
     */
    List<Entity> getInfo(Entity toGet);

    /**
     * Get the current room, where there are all entity drawed on view.
     * @return the current room.
     */
    Room getCurrentRoom();

    /**
     * Change the current room.
     * @param pos the coordinates of the passage
     */
    void changeRoom(Pair<Integer, Integer> pos);

    /**
     * Change the entire floor allowing the player into the next level.
     */
    void changeFloor();

    /**
     * Eliminates a monster and removes it from the map.
     * @param monster the monster to eliminate
     */
    void eliminateMonster(Monster monster);

    /**
     * Help to get some info about hero.
     * @return this infos
     */
    Portrait getRuscoInfo();

    /**
     * Check if the game is over, and the player lost.
     * @return <code>True</code> if the hero has died, <code>False</code> otherwise.
     */
    boolean isGameOver();

    /**
     * Check if the game is over, and the player win.
     * @return <code>True</code> if the hero has died, <code>False</code> otherwise.
     */
    boolean isGameWin();
}
