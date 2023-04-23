package it.unibo.ruscodc.view;

import it.unibo.ruscodc.model.Entity;
import it.unibo.ruscodc.utils.Pair;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class JFXDrawableImpl implements Drawable<GraphicsContext>  {
    private final Image sprite;
    private final Pair<Integer, Integer> position;
    private double rotation = 0;
    private double size = 1;

    public JFXDrawableImpl(Entity toDraw){
        sprite = new Image("file:" + toDraw.getPath());
        position = toDraw.getPos();
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
