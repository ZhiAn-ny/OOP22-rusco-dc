package it.unibo.ruscodc.model.range;

import it.unibo.ruscodc.model.gamemap.Room;
import it.unibo.ruscodc.utils.Pair;

/**
 * Specific the splash resources and infos.
 */
public class SingleSplash extends SingleAbs {

    private static final String PATH = "file:src/main/resources/it/unibo/ruscodc/range_res/splash";
    private static final int DEPTH = 5;

    /**
     * Create a splash Range, so a Range that specify where the effect of the Actor will be applied.
     */
    public SingleSplash() {
        super(PATH, DEPTH);
    }

    /**
     * 
     */
    @Override
    public boolean isInRange(
            final Pair<Integer, Integer> by, 
            final Pair<Integer, Integer> to, 
            final Pair<Integer, Integer> toCheck, 
            final Room where) {
        return by.equals(toCheck);
    }

}
