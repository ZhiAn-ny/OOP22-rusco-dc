package it.unibo.ruscodc.utils;

public enum Direction {
    UP,
    LEFT,
    DOWN,
    RIGHT,
    UNDEFINED;

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
