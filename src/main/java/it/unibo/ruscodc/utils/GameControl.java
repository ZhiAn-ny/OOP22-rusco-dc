package it.unibo.ruscodc.utils;

import java.util.List;

/**
 * Enum that contains the actions that the player can do.
 */
public enum GameControl {

    /**
     * Down direction.
     */
    MOVEDOWN,
    /**
     * Up direction.
     */
    MOVEUP,
    /**
     * Left direction.
     */
    MOVELEFT,
    /**
     * Right direction.
     */
    MOVERIGHT,
    /**
     * Inventory with the objects of user.
     */
    INVENTORY,
    /**
     * Pause game.
     */
    PAUSE,
    /**
     * Cancel the choose of user.
     */
    CANCEL,
    /**
     * Confirm the choose of user.
     */
    CONFIRM,
    /**
     * Interact with objects.
     */
    INTERACT,
    /**
     * Base attck.
     */
    BASEATTACK,
    /**
     * Special attack 1.
     */
    ATTACK1,
    /**
     * Special attack 2.
     */
    ATTACK2,
    /**
     * Special attack 3.
     */
    ATTACK3,
    /**
     * Special attack 4.
     */
    ATTACK4,
    /**
     * you can use the consumable item right away.
     */
    USEQUICK,

    /**
     *
     */
    DELETE,
    /**
     * Do nothing.
     */
    DONOTHING;

    public static List<GameControl> getAttackControls() {
        return List.of(BASEATTACK, ATTACK1, ATTACK2, ATTACK3, ATTACK4);
    }

}
