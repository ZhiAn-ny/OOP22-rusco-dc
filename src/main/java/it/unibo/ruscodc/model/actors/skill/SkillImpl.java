package it.unibo.ruscodc.model.actors.skill;


import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import it.unibo.ruscodc.model.gamecommand.GameCommand;
import it.unibo.ruscodc.model.gamecommand.quickcommand.MoveDownCommand;
import it.unibo.ruscodc.model.gamecommand.quickcommand.MoveLeftCommand;
import it.unibo.ruscodc.model.gamecommand.quickcommand.MoveRightCommand;
import it.unibo.ruscodc.model.gamecommand.quickcommand.MoveUpCommand;
import it.unibo.ruscodc.utils.GameControl;

/**
 * The Class that manage the "Actions"(GameCommand) an Actor can do.
 */
public class SkillImpl implements Skill {

    private final Map<GameControl, Optional<GameCommand>> skills = new HashMap<>();

    /**
     * 
     */
    public SkillImpl() {

        for (GameControl gameControl : GameControl.values()) {
            this.skills.put(gameControl, Optional.empty());
        }

        this.setAction(GameControl.MOVEUP, new MoveUpCommand());
        this.setAction(GameControl.MOVEDOWN, new MoveDownCommand());
        this.setAction(GameControl.MOVERIGHT, new MoveRightCommand());
        this.setAction(GameControl.MOVELEFT, new MoveLeftCommand());
    }

    /**
     * 
     */
    @Override
    public void setAction(final GameControl key, final GameCommand action) {
        this.skills.put(key, Optional.of(action));
    }

    /**
     * 
     */
    @Override
    public Optional<GameCommand> getAction(final GameControl key) {
        return this.skills.get(key);
    }

    @Override
    public String toString() {
        StringBuilder info = new StringBuilder();
        for (GameControl gameCommand : GameControl.getAttackControls()) {
            info.append(gameCommand.toString() + this.skills.get(gameCommand).get().toString());
        }
        return info.toString();
    }
}
