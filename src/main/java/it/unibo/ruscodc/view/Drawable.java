package it.unibo.ruscodc.view;

public class Drawable<X> {
    private final X x;

    public Drawable(X x) {
        this.x = x;
    }

    public X getX(){
        return this.x;
    }


}
