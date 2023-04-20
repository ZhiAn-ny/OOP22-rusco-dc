package it.unibo.ruscodc.view;

import it.unibo.ruscodc.utils.Pair;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public abstract class AbstractJFXDrawableImpl implements Drawable {
    private Image sprite;
    private Pair<Integer, Integer> position = new Pair(0,0);
    private double rotation = 0;
    private double size = 1;

    /**
     * Sets the object's sprite.
     *
     * @param path the path to the image file that will be used as sprite.
     */
    public void setImage(String path) {
        this.sprite = new Image("file:" + path);
    }

    @Override
    public void setPosition(Pair<Integer, Integer> pos) {
        this.position = pos;
    }

    @Override
    public Pair<Integer, Integer> getOnScreenPosition() {
        return this.position;
    }

    @Override
    public void setSize(double size) {
        this.size = size;
    }

    @Override
    public void render(GraphicsContext context) {
        context.save();
        context.translate(this.position.getX(), this.position.getY());
        context.rotate(this.rotation);
        context.translate(-this.sprite.getWidth()/2, -this.sprite.getHeight()/2);
        context.drawImage(this.sprite, 0, 0);

        context.restore();
    }
}
