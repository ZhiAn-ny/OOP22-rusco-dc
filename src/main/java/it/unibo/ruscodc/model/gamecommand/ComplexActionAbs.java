package it.unibo.ruscodc.model.gamecommand;


public abstract class ComplexActionAbs extends BasicGameCommand {

    /**
     * 
     */
    @Override
    public boolean isReady() {
        return false; //TODO - meditare se anche qui gestire un metodo protetto "setReady" o se fare override nelle altre classi
    }
    
}
