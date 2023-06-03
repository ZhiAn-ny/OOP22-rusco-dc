package it.unibo.ruscodc.utils.exception;

/**
 * Define a common class for manage some model exception.
 * Ideally, when a command is executed:
 * <ol>
 *  <li>it should be legal</li>
 *  <li>start the management for next actor</li>
 *  </ol>
 * But maybe an action could be generate another action, or the command isn't legal
 * All classes that extend this class define all this situation
 */
public class ModelException extends Exception {
    static final long serialVersionUID = 7002L;

}
