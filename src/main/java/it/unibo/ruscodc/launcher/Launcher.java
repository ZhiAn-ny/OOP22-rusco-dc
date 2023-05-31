package it.unibo.ruscodc.launcher;

import it.unibo.ruscodc.controller.GameControllerImpl;
import it.unibo.ruscodc.controller.GameObserverController;

/**
 *
 */
public final class Launcher {
    private Launcher() {
    }
    /**
     *
     * @param args
     */
    public static void main(final String[] args) {
        GameObserverController ctrl = new GameControllerImpl(args);
        ctrl.init();
        ctrl.start(args);
    }
}