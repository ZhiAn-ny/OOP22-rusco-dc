package it.unibo.ruscodc.view;

import it.unibo.ruscodc.utils.Pair;

/**
 * Help view to speed up (a bit) the process of printing.
 * @param <X> the type of image supported by the implemented view
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
