package it.unibo.ruscodc.model.actors.skill;


import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import it.unibo.ruscodc.model.gamecommand.GameCommand;
import it.unibo.ruscodc.model.gamecommand.quickcommand.*;
import it.unibo.ruscodc.utils.GameControl;

public class SkillImpl implements Skill {

    private final Map<GameControl, Optional<GameCommand>> skills = new HashMap<>();

    public enum SkillType {
        ATK,
        AIMEDATK,
        SELF,
        MOVE;
    }

    public SkillImpl() {
        
        for (GameControl gameControl : GameControl.values()) {
            this.skills.put(gameControl, Optional.empty());
        }

        this.setAction(GameControl.MOVEUP, new MoveUpCommand());
        this.setAction(GameControl.MOVEDOWN, new MoveDownCommand());
        this.setAction(GameControl.MOVERIGHT, new MoveRightCommand());
        this.setAction(GameControl.MOVELEFT, new MoveLeftCommand());
    }

    public void setAction(GameControl key, GameCommand action) {
        this.skills.put(key, Optional.of(action));
    }
    
    @Override
    public Optional<GameCommand> getAction(GameControl key) {
        return this.skills.get(key);
    }

}
