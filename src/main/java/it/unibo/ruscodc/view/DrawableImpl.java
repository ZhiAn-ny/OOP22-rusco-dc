package it.unibo.ruscodc.view;

import it.unibo.ruscodc.utils.Pair;

/**
 * Implementing of Drawable, maintaing the generic.
 * @param <X> the type of image supported by the implemented view.
 */
public class DrawableImpl<X> implements Drawable<X> {

    private final X image;
    private final Pair<Integer, Integer> pos;

    /**
     * Create this type of object.
     * @param toPrint image to save.
     * @param pos where is it into a grid.
     */
    public DrawableImpl(final X toPrint, final Pair<Integer, Integer> pos) {
        this.image = toPrint;
        this.pos = pos;
    }

    /**
     * 
     */
    @Override
    public Pair<Integer, Integer> getOnScreenPosition() {
        return pos;
    }

    /**
     * 
     */
    @Override
    public X getRes() {
        return image;
    }
}
