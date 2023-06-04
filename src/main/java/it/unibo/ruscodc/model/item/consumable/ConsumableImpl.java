package it.unibo.ruscodc.model.item.consumable;

import it.unibo.ruscodc.model.effect.SingleTargetEffect;
import it.unibo.ruscodc.model.outputinfo.InfoPayload;
import it.unibo.ruscodc.model.outputinfo.InfoPayloadImpl;

/**
 * Implementation of the Consumable interface.
 */
public class ConsumableImpl implements Consumable {

    private final String name;
    private final String path;
    private final InfoPayload info;
    private final SingleTargetEffect effect;

    /**
     * Constructor for Consumable Items.
     * @param name of the Consumable
     * @param description of the Consumable
     * @param path where to find Consumable related info
     * @param effect of the Consumable
     */
    public ConsumableImpl(
        final String name,
        final String description,
        final String path,
        final SingleTargetEffect effect
        ) {
            this.name = name;
            this.path = path;
            this.effect = effect;
            this.info = new InfoPayloadImpl(name, description, path);
    }

    /**
     * 
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * 
     */
    @Override
    public String getPath() {
        return this.path;
    }

    /**
     * 
     */
    @Override
    public InfoPayload getInfo() {
        return this.info;
    }

    /**
     * 
     */
    @Override
    public boolean isWearable() {
        return false;
    }

    /**
     * 
     */
    @Override
    public SingleTargetEffect consume() {
        return this.effect;
    }
}
