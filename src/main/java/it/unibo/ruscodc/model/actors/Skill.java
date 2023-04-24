package it.unibo.ruscodc.model.actors;

import java.util.Map;

import it.unibo.ruscodc.model.gamecommand.BuilderGameCommand;

public interface Skill {
    void setAction(int key, BuilderGameCommand action);
    BuilderGameCommand getAction(int key);
    Map<Integer,BuilderGameCommand> getSkills();
}
