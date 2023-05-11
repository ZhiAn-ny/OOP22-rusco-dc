package it.unibo.ruscodc.view;

import it.unibo.ruscodc.model.Entity;
import it.unibo.ruscodc.utils.Pair;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.*;
import java.util.stream.Collectors;

public class ViewFlyweight {

    private CanvasLevel canvas;
    private Map<String, Image> sprite;
    private Map<Canvas, Set<Drawable<GraphicsContext>>> map = new HashMap<>();
    private Scene scene;


    public void renderInfo(Entity info) {
        //todo
    }

    public void renderRange(Set<Entity> range){
        //todo
    }

    public void renderMobs(Set<Entity> mobs){



    }

    public void reset(Set<Entity> e, Pair<Integer, Integer> sizeRoom){
        canvas.clearStack();
        canvas.addStack(new Canvas(sizeRoom.getX(), sizeRoom.getY()));
        map.clear();
        map.put(canvas.getLastLevel(), byEntityToDrawable(e));
    }


    public Set<Drawable<GraphicsContext>> byEntityToDrawable(Set<Entity> entities){
        return entities.stream().map(e -> new JFXDrawableImpl(e)).collect(Collectors.toSet());
    }

}
