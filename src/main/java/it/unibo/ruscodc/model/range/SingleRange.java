package it.unibo.ruscodc.model.range;

/**
 * Specific the range resources and infos.
 */
public final class SingleRange extends SingleAbs {

    private static final String PATH = "it/unibo/ruscodc/range_res/range";
    private static final int DEPTH = 4;

    /**
     * Create a range Range, so a Range where the Actor can see targets and choose who attack.
     */
    public SingleRange() {
        super(PATH, DEPTH);
    }

}
