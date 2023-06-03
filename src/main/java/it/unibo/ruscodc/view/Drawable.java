package it.unibo.ruscodc.view;

import it.unibo.ruscodc.utils.Pair;

/**
 * TODO documentazione!.
 */
public interface Drawable<X> {

    /**
     *
     * @return the current position of the object.
     */
    Pair<Integer, Integer> getOnScreenPosition();

    /**
     * 
     * @return the wrapped resource
     */
    X getRes();

}
