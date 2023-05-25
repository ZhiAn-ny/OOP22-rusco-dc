package it.unibo.ruscodc.view;

import it.unibo.ruscodc.controller.GameObserverController;
import it.unibo.ruscodc.model.Entity;
import it.unibo.ruscodc.utils.Pair;

import java.util.List;

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
     *
     * @return whether the view has already been initialized or not
     */
    boolean isReady();

    /**
     * Prints a message on the errors stream.
     * @param err the message to visualize as error
     */
    void printError(String err);

    void resetView(List<Entity> toDraw, Pair<Integer, Integer> roomSize);
    void addEntity(Entity toAdd);
    void removeEntity(Entity toRemove);
    void uploadEntity(Entity toUpload);

}