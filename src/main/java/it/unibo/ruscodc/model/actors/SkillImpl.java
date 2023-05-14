package it.unibo.ruscodc.model.actors;

import it.unibo.ruscodc.model.gamecommand.GameCommand;
import it.unibo.ruscodc.model.gamecommand.MoveDownBuilder;
import it.unibo.ruscodc.model.gamecommand.MoveLeftBuilder;
import it.unibo.ruscodc.model.gamecommand.MoveRightBuilder;
import it.unibo.ruscodc.model.gamecommand.MoveUpBuilder;
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
        this.setAction(GameControl.MOVEUP, new MoveUpBuilder());
        this.setAction(GameControl.MOVEDOWN, new MoveDownBuilder());
        this.setAction(GameControl.MOVERIGHT, new MoveRightBuilder());
        this.setAction(GameControl.MOVELEFT, new MoveLeftBuilder());
    }

    @Override
    public void setAction(GameControl key, GameCommand action) {
        this.skills.put(key, action);
    }
    
    @Override
    public GameCommand getAction(GameControl key) {
        return this.skills.get(key);
    }

}
