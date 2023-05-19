package it.unibo.ruscodc.utils;

import java.util.Iterator;

/**
 * An implementation of Java Iterator that save the last element.
 * (! this implementation could only be optimized for iterators that iterate infinitely)
 * @param <X> the tipe of Iterator's objects
 */
public class MyIterator<X> implements Iterator<X> {

    private X act;
    private final Iterator<X> myIt;

    /**
     * Create this type of iterator.
     * @param it the iterator to wrap
     */
    public MyIterator(final Iterator<X> it) {
        this.myIt = it;
        act = this.next();
    }

    /**
     * 
     */
    @Override
    public boolean hasNext() {
        return myIt.hasNext();
    }

    /**
     * 
     */
    @Override
    public X next() {
        if (!this.hasNext()) {
            throw new IllegalAccessError();
        }
        act = myIt.next();
        return act;
    }

    /**
     * Return the last iterate element.
     * @return <X> the last iterate element
     */
    public X getAct() {
        return act;
    }

}
