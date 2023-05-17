package it.unibo.ruscodc.model.actors;

import it.unibo.ruscodc.model.gamecommand.GameCommand;
import it.unibo.ruscodc.model.gamecommand.quickcommand.MoveDownCommand;
import it.unibo.ruscodc.model.gamecommand.quickcommand.MoveLeftCommand;
import it.unibo.ruscodc.model.gamecommand.quickcommand.MoveRightCommand;
import it.unibo.ruscodc.model.gamecommand.quickcommand.MoveUpCommand;
import it.unibo.ruscodc.utils.GameControl;

import java.util.HashMap;
import java.util.Map;

public class SkillImpl implements Skill {

    final Map<GameControl, GameCommand> skills = new HashMap<>();

    public enum SkillType {
        ATK,
        AIMEDATK,
        SELF,
        MOVE;
    }

    public SkillImpl() {
        this.setAction(GameControl.MOVEUP, new MoveUpCommand());
        this.setAction(GameControl.MOVEDOWN, new MoveDownCommand());
        this.setAction(GameControl.MOVERIGHT, new MoveRightCommand());
        this.setAction(GameControl.MOVELEFT, new MoveLeftCommand());
    }

    public void setAction(GameControl key, GameCommand action) {
        this.skills.put(key, action);
    }
    
    @Override
    public GameCommand getAction(GameControl key) {
        return this.skills.get(key);
    }

}
