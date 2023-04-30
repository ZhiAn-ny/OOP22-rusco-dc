package it.unibo.ruscodc.utils.exception;

/**
 * If this exception has been thrown, it means that an actor desire to move to a position that isn't accesible.
 */
public final class UnreacheblePos extends ModelException {

    /**
     * Create this type of exception.
     * @param mess the "unreacheble position" message error
     */
    public UnreacheblePos(final String mess) {
    }

}
