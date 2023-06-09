package it.unibo.ruscodc.utils.exception;

/**
 *  The <code>Undo</code> exception triggers controller to abort actual command,
 *  but also maintain the turn phase on the last actor.
 */
public class Undo extends ModelException {

    static final long serialVersionUID = 7004L;

}
