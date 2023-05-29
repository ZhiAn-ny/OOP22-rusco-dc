package it.unibo.ruscodc.view;

import it.unibo.ruscodc.controller.GameObserverController;
import it.unibo.ruscodc.model.Entity;
import it.unibo.ruscodc.model.outputinfo.InfoPayload;
import it.unibo.ruscodc.utils.Pair;

import java.util.List;

public interface GameView {
    /**
     * Starts the game view creating a new window.
     */
    void startView(String[] args);

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
     * @param toPrint the message to visualize as error
     */
    void printInfo(InfoPayload toPrint);

    void resetView(List<Entity> toDraw, Pair<Integer, Integer> roomSize);
    void addEntity(Entity toAdd);
    void uploadEntity(Pair<Integer, Integer> toUpload, Entity updated);
    void resetLevel(List<Entity> entities);

}