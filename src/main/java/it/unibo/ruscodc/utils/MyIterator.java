package it.unibo.ruscodc.utils;

import java.util.Iterator;

/**
 * An implementation of Java Iterator that save the last element.
 * (! this implementation could only be optimized for iterators that iterate infinitely)
 * In that case, to obtain an element, is better use getAct method instead of next method
 * If this object is created with a Iterator that iterate on an empty collection
 * getAct method return null, and obviosily "next" method will throw an exception
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
        if (myIt.hasNext()) {
            act = this.next();
        } else {
            act = null;
        }
        
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
