package it.unibo.ruscodc.view;

import it.unibo.ruscodc.utils.Pair;

public interface OldDrawable<X> {

    /**
     *
     * @return the current position of the object.
     */
    Pair<Integer, Integer> getOnScreenPosition();

    /**
     * Sets the scale of the object on the game window.
     * The scale is set to 1 by default.
     *
     * @param size the scale to apply to the object.
     */
    void setSize(double size);

    /**
     * Draws the object into the window created by the view.
     *
     * @param context the graphic context in which the object will be rendered.
     */
    void render(X context);

}
