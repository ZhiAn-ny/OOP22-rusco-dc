package it.unibo.ruscodc.model.actors.monster;

import it.unibo.ruscodc.model.gamecommand.GameCommand;

/**
 * An interface for the Monster Action Factory 
 * that is a Factory that creates GameCommands.
 */
public interface MonsterActionFactory {

    /**
     * @return a Basic Melee attack 
     * that deals damage to a single
     * target
     */
    GameCommand basicMeleeAttack();

    /**
     * @return a Heavy Melee attack 
     * that deals a lot of damage to
     * a single target
     */
    GameCommand heavyMeleeAttack();

    /**
     * @return a Basic Ranged attack 
     * that deals damage to a single
     * target
     */
    GameCommand basicRangedAttack();

    /**
     * @return a Magic attack that 
     * deals damage to a single target
     */
    GameCommand ratMagic();

    /**
     * @return a Magic attack that
     * deals damage in a small area
     */
    GameCommand badSmell();

    /**
     * @return a Heavy Melee attack
     * based on DEX Stat
     */
    GameCommand backstab();

    /**
     * @return a big Area of Effect
     * damage that make the user to
     * explode
     */
    GameCommand disgustingDemise();
}
