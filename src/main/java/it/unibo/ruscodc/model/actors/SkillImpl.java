package it.unibo.ruscodc.model.actors;

import it.unibo.ruscodc.model.gamecommand.BuilderGameCommand;
import it.unibo.ruscodc.model.gamecommand.MoveDownBuilder;
import it.unibo.ruscodc.model.gamecommand.MoveLeftBuilder;
import it.unibo.ruscodc.model.gamecommand.MoveRightBuilder;
import it.unibo.ruscodc.model.gamecommand.MoveUpBuilder;
import it.unibo.ruscodc.utils.GameControl;

import java.util.HashMap;
import java.util.Map;

public class SkillImpl implements Skill {

    final Map<GameControl, BuilderGameCommand> skills = new HashMap<>();

    public SkillImpl() {
        //TODO Versione brute force dell'implementazione dei basic movement
        this.setAction(GameControl.MOVEUP, new MoveUpBuilder());
        this.setAction(GameControl.MOVEDOWN, new MoveDownBuilder());
        this.setAction(GameControl.MOVERIGHT, new MoveRightBuilder());
        this.setAction(GameControl.MOVELEFT, new MoveLeftBuilder());
    }

    @Override
    public void setAction(GameControl key, BuilderGameCommand action) {
        this.skills.put(key, action);
    }
    
    @Override
    public BuilderGameCommand getAction(GameControl key) {
        return this.skills.get(key);
    }

    public void manageSkill(int key, BuilderGameCommand toSet) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'manageSkill'");
    }

    public void modifySkill() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'modifySkill'");
    }

    @Override
    public Map<GameControl, BuilderGameCommand> getSkills() {
        return Map.copyOf(this.skills);
    }

}
