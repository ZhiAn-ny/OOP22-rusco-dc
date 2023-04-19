package it.unibo.ruscodc.model;

import it.unibo.ruscodc.model.gamecommand.BuilderGameCommand;

public interface Skill {
    void setSkill(int key, BuilderGameCommand action);
    BuilderGameCommand getAction(int key);
    /* void manageSkill(int key, BuilderGameCommand toSet);
    void modifySkill(); */
}
