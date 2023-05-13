package it.unibo.ruscodc.model.range;

/**
 * Specific the splash resources and infos.
 */
public class SingleSplash extends SingleAbs {

    private static final String PATH = "file:src/main/resources/it/unibo/ruscodc/range_res/splash";
    private static final String INFO = "Range attack: here you can move the cursor";

    /**
     * Create a splash Range, so a Range that specify where the effect of the Actor will be applied.
     */
    public SingleSplash() {
        super(PATH, INFO);
    }

}
