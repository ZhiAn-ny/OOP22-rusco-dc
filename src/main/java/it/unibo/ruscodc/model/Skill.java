package it.unibo.ruscodc.model;

import it.unibo.ruscodc.model.gamecommand.BuilderGameCommand;

public interface Skill {
    void setAction(int key, BuilderGameCommand action);
    BuilderGameCommand getAction(int key);
}
