package it.unibo.ruscodc.model.range;

import java.util.Iterator;
import java.util.stream.Stream;

import it.unibo.ruscodc.model.Entity;
import it.unibo.ruscodc.utils.Pair;

public class CircleRange extends DecoratedRange {

    private final int size;

    public CircleRange(Range start, int size) {
        super(start);
        this.size = size;
    }
    
    private boolean myInRangeCheck(Pair<Integer, Integer> by, Pair<Integer, Integer> toCheck){
        return (Math.abs(by.getX() - toCheck.getX()) + Math.abs(by.getY() - toCheck.getY())) <= size;
    }

    @Override
    public boolean isInRange(Pair<Integer, Integer> by, Pair<Integer, Integer> toCheck) {
        return getMyRange().isInRange(by, toCheck) || myInRangeCheck(by, toCheck);
        
    }

    @Override
    public Iterator<Entity> getRange(Pair<Integer, Integer> by) {
        int xB = by.getX();
        int yB = by.getY();
        Stream<Entity> tmp = Stream
            .iterate(xB - size, i -> i<= xB + size, j -> j + 1)
            .flatMap(x -> Stream
                .iterate(yB - size, i -> i<= yB + size, j -> j+1)
                .map(y -> new Pair<Integer, Integer>(x, y)))
            .filter(p -> myInRangeCheck(by, p))
            .map(p -> new Entity() {

                @Override
                public String getInfo() {
                    return "classic";
                }

                @Override
                public String getPath() {
                    return getPathRes();
                }

                @Override
                public Pair<Integer, Integer> getPos() {
                    return p;
                }
                
            });
        Iterator<Entity> tmp2 = getMyRange().getRange(by);
        return Stream.concat(tmp,Stream.iterate(tmp2.next(), i -> tmp2.hasNext(), j -> tmp2.next())).iterator();
    }
    
}
