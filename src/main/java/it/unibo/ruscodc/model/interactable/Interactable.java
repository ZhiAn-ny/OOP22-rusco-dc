package it.unibo.ruscodc.model.interactable;

import it.unibo.ruscodc.model.gamecommand.GameCommand;

public interface Interactable {

    String getName();

    GameCommand interact();

}
