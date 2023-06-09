package it.unibo.ruscodc.utils;

import java.io.Serializable;

/**
 * Class that wrap the concept of "2D tupla".
 * Taked by file gived into OOP exam exercitations.
 * @param <X> the first component
 * @param <Y> the second component
 */
public final class Pair<X, Y> implements Serializable {

    private static final long serialVersionUID = 123L;

    private final X x;
    private final Y y;

    /**
     * Create a tuple, with their values.
     * This values will be read-only
     * @param x the X component
     * @param y the Y component
     */
    public Pair(final X x, final Y y) {
        super();
        this.x = x;
        this.y = y;
    }

    /**
     * Get the X component of the pair.
     * @return the X component
     */
    public X getX() {
        return x;
    }

    /**
     * Get the Y component of the pair.
     * @return the Y component
     */
    public Y getY() {
        return y;
    }

    /**
     * 
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((x == null) ? 0 : x.hashCode());
        result = prime * result + ((y == null) ? 0 : y.hashCode());
        return result;
    }

    /**
     * 
     */
    @SuppressWarnings("rawtypes")
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
        return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Pair other = (Pair) obj;
        if (x == null) {
            if (other.x != null) {
                return false;
            }
        } else if (!x.equals(other.x)) {
            return false;
        }
        if (y == null) {
            if (other.y != null) {
                return false;
            }
        } else if (!y.equals(other.y)) {
            return false;
        }
        return true;
    }

    /**
     * 
     */
    @Override
    public String toString() {
        return "Pair [x=" + x + ", y=" + y + "]";
    } 

}
