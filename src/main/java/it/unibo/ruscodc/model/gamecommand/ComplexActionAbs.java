package it.unibo.ruscodc.model.gamecommand;

/**
 * Specific implementation of BasicGameCommad, for all command that cannot be execute immiedately.
 */
public abstract class ComplexActionAbs extends BasicGameCommand {

    /**
     * 
     */
    @Override
    public boolean isReady() {
        return true;
    }

}
