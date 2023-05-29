package it.unibo.ruscodc.utils.exception;

/**
 * If this exception has been thrown, it means that the action to be performed does not respect a certain range.
 */
public class NotInRange extends ModelException {

    static final long serialVersionUID = 7003L;

    /**
     * Create this type of exception.
     * @param mess the "not in range" message error
     */
    public NotInRange(final String mess) {
    }

}
