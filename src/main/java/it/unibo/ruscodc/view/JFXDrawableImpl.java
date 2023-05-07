package it.unibo.ruscodc.view;

import it.unibo.ruscodc.model.Entity;
import it.unibo.ruscodc.utils.Pair;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class JFXDrawableImpl implements Drawable<GraphicsContext>  {
    private Image sprite;
    private Pair<Integer, Integer> position;
    private double rotation = 0;
    private double size = 1;
    private double screenUnit;

    public JFXDrawableImpl(Entity toDraw, double screenUnit){
        int offset = 2; // TODO: change according to room size
        this.screenUnit = screenUnit;
        this.sprite = new Image(toDraw.getPath() + "/Sprite.png");
        Pair<Integer, Integer> logicPosition = toDraw.getPos();
        this.position = new Pair<Integer, Integer>(
                Math.toIntExact((logicPosition.getX() + offset) * Math.round(this.screenUnit)),
                Math.toIntExact((logicPosition.getY() + offset) * Math.round(this.screenUnit))
        );
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
    public void updateScreenUnit(double screenUnit) {
        double logicPosX = Math.round(this.position.getX() / this.screenUnit);
        double logicPosY = Math.round(this.position.getY() / this.screenUnit);
        this.screenUnit = screenUnit;
        this.applySize();
        this.position = new Pair<Integer, Integer>(
                Math.toIntExact(Math.round(logicPosX * this.screenUnit)),
                Math.toIntExact(Math.round(logicPosY * this.screenUnit))
        );
    }

    @Override
    public void setSize(double size) {
        this.size = size;
        this.applySize();

    }

    private void applySize() {
        this.sprite = new Image(
            this.sprite.getUrl(),
            this.screenUnit * this.size,
            this.screenUnit * this.size,
            true, false
        );
    }

}
