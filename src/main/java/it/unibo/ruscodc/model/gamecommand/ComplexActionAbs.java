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
        return true; //TODO - meditare se anche qui gestire un metodo protetto "setReady" o se fare override nelle altre classi
    }

}
