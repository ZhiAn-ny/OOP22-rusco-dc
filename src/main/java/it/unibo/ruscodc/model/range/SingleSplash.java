package it.unibo.ruscodc.model.range;

/**
 * Specific the splash resources and infos.
 */
public class SingleSplash extends SingleAbs {

    /**
     * 
     */
    @Override
    protected String getSpecificPath() {
        return "file:src/main/resources/it/unibo/ruscodc/range_res/splash";
    }

    /**
     * 
     */
    @Override
    protected String getSpecificInfo() {
        return "Splash attack: here you effect could be deadly!";
    }

}
