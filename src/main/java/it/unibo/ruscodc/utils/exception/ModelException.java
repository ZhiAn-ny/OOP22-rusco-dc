package it.unibo.ruscodc.utils.exception;

/**
 * Define a common class for manage some model exception.
 * Ideally, when a command is executed:
 * <ol>
 *  <li>it should be legal</li>
 *  <li>start the management for next actor</li>
 *  </ol>
 * But maybe an action could alter this basic game flow
 * These exception are usefull to go back, or to init, a new cycle of turns
 */
public class ModelException extends Exception {
    static final long serialVersionUID = 7002L;

}
