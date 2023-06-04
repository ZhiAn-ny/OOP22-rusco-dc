package it.unibo.ruscodc.view;

import java.util.Collections;

import it.unibo.ruscodc.utils.Pair;
import javafx.scene.image.ImageView;


public class FXMLDrawable implements Drawable<ImageView> {

    private final Pair<Integer, Integer> pos;
    private final ImageView res;

    /**
     * Construct an object of this class.
     * @param toPrint image that will be printed
     * @param pos the position
     */
    public FXMLDrawable(final ImageView toPrint, final Pair<Integer, Integer> pos) {
        this.res = Collections.nCopies(1, toPrint).get(0);
        this.pos = Collections.nCopies(1, pos).get(0);
    }


    /** {@inheritDoc} */
    @Override
    public Pair<Integer, Integer> getOnScreenPosition() {
       return pos;
    }


    /** {@inheritDoc} */
    @Override
    public ImageView getRes() {
        return Collections.nCopies(1, res).get(0);
    }

}
