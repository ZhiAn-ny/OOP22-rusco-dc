package it.unibo.ruscodc.model.actors;

import it.unibo.ruscodc.model.gamecommand.BuilderGameCommand;
import it.unibo.ruscodc.model.gamecommand.MoveDownBuilder;
import it.unibo.ruscodc.model.gamecommand.MoveLeftBuilder;
import it.unibo.ruscodc.model.gamecommand.MoveRightBuilder;
import it.unibo.ruscodc.model.gamecommand.MoveUpBuilder;

import java.util.HashMap;
import java.util.Map;

public class SkillImpl implements Skill {

    final Map<Integer, BuilderGameCommand> skills = new HashMap<>();

    public SkillImpl() {
        //TODO Versione brute force dell'implementazione dei basic movement
        this.setAction(1, new MoveUpBuilder());
        this.setAction(2, new MoveDownBuilder());
        this.setAction(3, new MoveRightBuilder());
        this.setAction(4, new MoveLeftBuilder());
    }

    @Override
    public void setAction(int key, BuilderGameCommand action) {
        this.skills.put(key, action);
    }
    
    @Override
    public BuilderGameCommand getAction(int key) {
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

}
