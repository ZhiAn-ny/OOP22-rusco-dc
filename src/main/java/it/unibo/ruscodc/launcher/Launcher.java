package it.unibo.ruscodc.launcher;

import it.unibo.ruscodc.controller.GameControllerImpl;
import it.unibo.ruscodc.controller.GameObserverController;

/**
 * Class entry-point of the project.
 */
public final class Launcher {

    /**
     * Entry point of the game.
     * @param args some parameter... typically not used in this project
     */
    public static void main(final String[] args) {
        final GameObserverController ctrl = new GameControllerImpl(args);
        ctrl.init();
        ctrl.start(args);
    }
}
