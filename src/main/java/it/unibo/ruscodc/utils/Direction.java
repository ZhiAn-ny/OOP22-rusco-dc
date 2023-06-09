package it.unibo.ruscodc.utils;

/**
 * The <code>Direction</code> enum defines the basic directions used in the game.
 */
public enum Direction {
    /**
     * Direction pointing toward the top of the screen.
     */
    UP,
    /**
     * Direction pointing toward the left side of the screen.
     */
    LEFT,
    /**
     * Direction pointing toward the bottom of the screen.
     */
    DOWN,
    /**
     * Direction pointing toward the right side of the screen.
     */
    RIGHT,
    /**
     * Direction not defined.
     */
    UNDEFINED;

    /**
     * Return the direction opposing the current one.
     * @return the <code>Direction</code> opposite to the current one
     */
    public Direction getOpposite() {
        return switch (this) {
            case UP -> Direction.DOWN;
            case DOWN -> Direction.UP;
            case LEFT -> Direction.RIGHT;
            case RIGHT -> Direction.LEFT;
            default -> Direction.UNDEFINED;
        };
    }
}
