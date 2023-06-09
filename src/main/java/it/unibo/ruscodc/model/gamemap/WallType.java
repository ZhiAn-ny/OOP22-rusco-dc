package it.unibo.ruscodc.model.gamemap;

/**
 * The WallType enum differentiates different types of <code>WallTile</code>
 * based on the position of the wall relative to the center of the room.
 */
public enum WallType {
    /**
     * The wall placed on the upper side of the room.
     */
    TOP,
    /**
     * The wall placed on the left corner of the upper side of the room.
     */
    TOP_LEFT,
    /**
     * The wall placed on the right corner of the upper side of the room.
     */
    TOP_RIGHT,
    /**
     * The wall placed on the bottom side of the room.
     */
    BOTTOM,
    /**
     * The wall placed on the left corner of the bottom side of the room.
     */
    BOTTOM_LEFT,
    /**
     * The wall placed on the right corner of the bottom side of the room.
     */
    BOTTOM_RIGHT,
    /**
     * The wall placed on the right side of the room.
     */
    RIGHT,
    /**
     * The wall placed on the left side of the room.
     */
    LEFT,
    /**
     * No side of the room was defined for this wall.
     */
    UNDEFINED
}
