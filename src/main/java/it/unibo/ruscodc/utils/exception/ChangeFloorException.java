package it.unibo.ruscodc.utils.exception;

/**
 * The <code>ChangeFloorException</code> exception triggers the floor change event.
 */
public class ChangeFloorException extends ModelException {

    static final long serialVersionUID = 7000L;

    /**
     * Create this type of exception.
     * @param mess the message that advices that the floor is changing
     */
    public ChangeFloorException(final String mess) {
    }


}
