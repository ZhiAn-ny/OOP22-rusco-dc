package it.unibo.ruscodc.view;

import it.unibo.ruscodc.controller.GameObserverController;
import it.unibo.ruscodc.model.Entity;
import it.unibo.ruscodc.model.outputinfo.InfoPayload;
import it.unibo.ruscodc.model.outputinfo.Portrait;
import it.unibo.ruscodc.utils.Pair;

import java.io.IOException;
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
     * Start the view for a new game.
     * @param gameName //TODO non servo!
     * @throws IOException //TODO non servo!
     */
    void startNewGame(String gameName) throws IOException;

    /**
     * Advise the controller if the view is ready to be showed.
     * @return whether the view has already been initialized or not.
     */
    boolean isReady();

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
     * Add a new entity in the room.
     * @param toAdd entity to add.
     */
    void addEntity(Entity toAdd);

    /**
     * Upload the entity with the new position.
     * @param toUpload new position.
     * @param updated entity that needs to be updated.
     */
    void uploadEntity(Pair<Integer, Integer> toUpload, Entity updated);

    /**
     * Reset the level of the game with the new entities.
     * @param entities that need to be printed.
     */
    void resetLevel(List<Entity> entities);

    /**
     * Print to the view the gameover view.
     */
    void printGameOver() throws IOException;

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