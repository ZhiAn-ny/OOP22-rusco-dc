package it.unibo.ruscodc.model.actors;

import java.util.Map;

import it.unibo.ruscodc.model.gamecommand.BuilderGameCommand;
import it.unibo.ruscodc.utils.GameControl;

public interface Skill {
    void setAction(GameControl key, BuilderGameCommand action);
    BuilderGameCommand getAction(GameControl key);
    Map<GameControl,BuilderGameCommand> getSkills();
}
