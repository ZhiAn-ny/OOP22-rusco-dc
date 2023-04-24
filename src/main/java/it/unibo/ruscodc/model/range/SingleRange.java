package it.unibo.ruscodc.model.range;

import java.util.Iterator;
import java.util.List;

import it.unibo.ruscodc.model.Entity;
import it.unibo.ruscodc.utils.Pair;

public final class SingleRange implements Range{
    private final static String PATH = "";
    @Override
    public boolean isInRange(Pair<Integer, Integer> by, Pair<Integer, Integer> toCheck) {
        return by.equals(toCheck);
    }

    @Override
    public Iterator<Entity> getRange(Pair<Integer, Integer> by) {
        List<Entity> tmp = List.of(new Entity() {

            @Override
            public String getInfo() {
                return "Range attack";
            }

            @Override
            public String getPath() {
                return PATH;
            }

            @Override
            public Pair<Integer, Integer> getPos() {
                return by;
            }
            
        });
        return tmp.iterator();
        
    }

    protected String getPathRes(){
        return PATH;
    } 

    
}
