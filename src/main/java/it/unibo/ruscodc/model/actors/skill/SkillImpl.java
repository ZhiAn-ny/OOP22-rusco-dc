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

    private final Map<GameControl, GameCommand> skills = new HashMap<>();

    /**
     * 
     */
    public SkillImpl() {
        this.setAction(GameControl.MOVEUP, new MoveUpCommand());
        this.setAction(GameControl.MOVEDOWN, new MoveDownCommand());
        this.setAction(GameControl.MOVERIGHT, new MoveRightCommand());
        this.setAction(GameControl.MOVELEFT, new MoveLeftCommand());
    }

    /**
     * 
     */
    @Override
    public final void setAction(final GameControl key, final GameCommand action) {
        this.skills.put(key, action);
    }

    /**
     * 
     */
    @Override
    public Optional<GameCommand> getAction(final GameControl key) {
        if (this.skills.containsKey(key)) {
            return Optional.of(this.skills.get(key));
        }
        return Optional.empty();
    }

    /**
     * 
     */
    @Override
    public String toString() {
        final StringBuilder info = new StringBuilder();
        for (final GameControl gameCommand : GameControl.getAttackControls()) {
            info.append(gameCommand.toString() + this.skills.get(gameCommand).toString());
        }
        return info.toString();
    }
}
