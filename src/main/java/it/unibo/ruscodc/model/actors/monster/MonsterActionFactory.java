package it.unibo.ruscodc.model.actors.monster;

import it.unibo.ruscodc.model.gamecommand.GameCommand;

public interface MonsterActionFactory {
    GameCommand basicMeleeAttack();
    GameCommand heavyMeleeAttack();
    GameCommand basicRangedAttack();
    GameCommand ratMagic();
    GameCommand badSmell();
    GameCommand backstab();
    GameCommand disgustingDemise();
}
