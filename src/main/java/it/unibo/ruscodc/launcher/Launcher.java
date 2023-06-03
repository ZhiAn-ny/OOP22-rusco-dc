package it.unibo.ruscodc.launcher;

import it.unibo.ruscodc.controller.GameControllerImpl;
import it.unibo.ruscodc.controller.GameObserverController;

/**
 * Class entry-point of the project
 */
public final class Launcher {

    private Launcher() {
    }

    /**
     * Entry point of the game.
     * @param args some parameter... tipically not used in this project
     */
    public static void main(final String[] args) {
        GameObserverController ctrl = new GameControllerImpl(args);
        ctrl.init();
        ctrl.start();
    }
}