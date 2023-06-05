package it.unibo.ruscodc.view;

import it.unibo.ruscodc.controller.GameObserverController;
import it.unibo.ruscodc.model.Entity;
import it.unibo.ruscodc.model.outputinfo.InfoPayload;
import it.unibo.ruscodc.model.outputinfo.Portrait;
import it.unibo.ruscodc.utils.Pair;

import java.util.List;

/**
 * Define the set of method for show result after input of this game.
 */
public interface GameView {

    /**
     * Starts the game view creating a new window.
     */
    void startView();

    /**
     * Initializes the view.
     * @param ctrl the controller that will communicate with the view
     */
    void init(GameObserverController ctrl);

    /**
     * Prints a message on the errors stream.
     * @param toPrint the message to visualize as error.
     */
    void printInfo(InfoPayload toPrint);

    /**
     *
     * @param infos
     */
    void uploadPortrait(Portrait infos);

    /**
     * Print the new room with the new entities.
     * @param toDraw list of entities to draw.
     * @param roomSize size of the room.
     */
    void resetView(List<Entity> toDraw, Pair<Integer, Integer> roomSize);

    /**
     * Reset the level of the game with the new entities.
     * @param entities that need to be printed.
     */
    void resetLevel(List<Entity> entities);

    /**
     * Print to the view the gameover view.
     */
    void printGameOver();

    /**
     * Open the inventory view.
     */
    void openInventory();

    /**
     * Close the inventory view.
     */
    void closeInventory();

    /**
     * Print to view the stat of actual hero.
     * @param heroStats where take infos.
     */
    void printStats(String heroStats);
}
