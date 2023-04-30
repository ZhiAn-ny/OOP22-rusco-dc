package it.unibo.ruscodc.model.range;

/**
 * Specific the range resources and infos.
 */
public final class SingleRange extends SingleAbs {

    @Override
    protected String getSpecificPath() {
        return "file:src/main/resources/it/unibo/ruscodc/range_res/range";
    }

    @Override
    protected String getSpecificInfo() {
        return "Range attack: here you can move the cursor";
    }

}
