package it.unibo.ruscodc.view;

import it.unibo.ruscodc.model.Entity;
import it.unibo.ruscodc.utils.Pair;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * This class implement Drawable.
 * It serves for draw a map and actor to pass at the view which will print all.
 */
public class JFXDrawableImpl implements OldDrawable<GraphicsContext>  {
    private Image sprite;
    private final Pair<Integer, Integer> position;
    private double rotation = 0;
    private double size = 1;

    private final static int SCALE = 100;

    /**
     * Class constructor.
     * @param toDraw is a entity need to draw in the view
     */
    public JFXDrawableImpl(final Entity toDraw) {
        sprite = new Image(toDraw.getPath());
        Pair<Integer, Integer> tmp = toDraw.getPos();
        position = new Pair<>(tmp.getX() * SCALE, tmp.getY() * SCALE); //TODO
    }

    /**
     * Return the coordinates of position.
     * @return the position
     */
    @Override
    public Pair<Integer, Integer> getOnScreenPosition() {
        return this.position;
    }

    /**
     * Set the view with the position.
     * @param context the graphic context in which the object will be rendered.
     */
    @Override
    public void render(final GraphicsContext context) {
        context.save();
        context.translate(this.position.getX(), this.position.getY());
        context.rotate(this.rotation);
        context.translate(-this.sprite.getWidth() / 2, -this.sprite.getHeight() / 2);
        context.drawImage(this.sprite, 0, 0);
        context.restore();

    }

    /**
     *  Set the size of view.
     * @param size the scale to apply to the object.
     */
    @Override
    public void setSize(final double size) {
        this.size = size;
        this.applySize(50);
    }

    private void applySize(final double screeUnit) {
        this.sprite = new Image(this.sprite.getUrl(),
                screeUnit * this.size, screeUnit * this.size,
                true, false);
    }

}
