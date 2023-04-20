package it.unibo.ruscodc.launcher;

import it.unibo.ruscodc.controller.GameControllerImpl;
import it.unibo.ruscodc.controller.GameObserverController;
import it.unibo.ruscodc.view.GameView;
import it.unibo.ruscodc.view.ViewJFX;
import javafx.application.Application;

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
        GameObserverController ctrl = new GameControllerImpl();
        ctrl.init();
        ctrl.start();


    }
}