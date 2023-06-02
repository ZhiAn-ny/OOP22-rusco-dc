package it.unibo.ruscodc.view;

import it.unibo.ruscodc.utils.Pair;
import javafx.scene.image.ImageView;

public class FXMLDrawable implements Drawable<ImageView> {

    private final Pair<Integer, Integer> pos;
    private final ImageView res;

    public FXMLDrawable(final ImageView toPrint, final Pair<Integer, Integer> pos) {
        this.res = toPrint;
        this.pos = pos;
    }


    @Override
    public Pair<Integer, Integer> getOnScreenPosition() {
       return pos;
    }


    @Override
    public ImageView getRes() {
        return res;
    }

    
    
}
