package it.unibo.ruscodc.view;

import it.unibo.ruscodc.model.Entity;
import it.unibo.ruscodc.utils.Pair;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class JFXDrawableImpl implements Drawable<GraphicsContext>  {
    private Image sprite;
    private final Pair<Integer, Integer> position;
    private double rotation = 0;
    private double size = 1;

    private final static int SCALE = 100;
    public JFXDrawableImpl(Entity toDraw){
        sprite = new Image(toDraw.getPath() + "/Sprite.png");
        Pair<Integer, Integer> tmp = toDraw.getPos();
        position = new Pair<>(tmp.getX()*SCALE, tmp.getY()*SCALE); //TODO
    }

    @Override
    public Pair<Integer, Integer> getOnScreenPosition() {
        return this.position;
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

    @Override
    public void setSize(double size) {
        this.size = size;
        this.applySize(50);
    }

    private void applySize(double screeUnit) {
        this.sprite = new Image(this.sprite.getUrl(),
                screeUnit * this.size, screeUnit * this.size,
                true, false);
    }

}
