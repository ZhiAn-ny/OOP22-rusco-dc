package it.unibo.ruscodc.model;

import it.unibo.ruscodc.utils.gamecommand.GameCommand;

public interface Skill {
    void setSkill(int key, GameCommand action);
    GameCommand getSkill(int key);
    void manageSkill(int key, GameCommand toSet);
    void modifySkill();
}
