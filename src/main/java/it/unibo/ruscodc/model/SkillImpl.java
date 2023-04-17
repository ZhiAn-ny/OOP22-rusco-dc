package it.unibo.ruscodc.model;

import it.unibo.ruscodc.utils.gamecommand.GameCommand;

import java.util.HashMap;
import java.util.Map;

public class SkillImpl implements Skill {

    Map<Integer, GameCommand> skills = new HashMap<>();

    public SkillImpl(){

    }

    @Override
    public void setSkill(int key, GameCommand action) {

    }

    @Override
    public GameCommand getSkill(int key) {
        return null;
    }

    @Override
    public void manageSkills(int key, GameCommand toSet) {

    }
}
