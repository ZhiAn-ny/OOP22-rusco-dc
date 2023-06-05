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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((image == null) ? 0 : image.hashCode());
        result = prime * result + ((pos == null) ? 0 : pos.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        DrawableImpl other = (DrawableImpl) obj;
        if (image == null) {
            if (other.image != null)
                return false;
        } else if (!image.equals(other.image))
            return false;
        if (pos == null) {
            if (other.pos != null)
                return false;
        } else if (!pos.equals(other.pos))
            return false;
        return true;
    }
}
