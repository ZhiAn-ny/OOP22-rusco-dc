package it.unibo.ruscodc.model.interactable;

import it.unibo.ruscodc.utils.Pair;

/**
 *
 */
public abstract class InteractableAbs implements Interactable {

    private final Pair<Integer, Integer> pos;

    /**
     *
     * @param pos
     */
    protected InteractableAbs(final Pair<Integer, Integer> pos) {
        this.pos = pos;
    }


    /**
     *
     * @return
     */
    @Override
    public Pair<Integer, Integer> getPos() {
        return this.pos;
    }

    /**
     * 
     */
    @Override
    public int getID() {
        return 2;
    }

}
