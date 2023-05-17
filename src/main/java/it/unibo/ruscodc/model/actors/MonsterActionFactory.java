package it.unibo.ruscodc.model.actors;

import it.unibo.ruscodc.model.gamecommand.GameCommand;

public interface MonsterActionFactory {
    GameCommand basicMeleeAttack();
    GameCommand heavyMeleeAttack();
}
