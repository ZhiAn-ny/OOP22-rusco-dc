package it.unibo.ruscodc.model.item.Consumable;

import it.unibo.ruscodc.model.effect.SingleTargetEffect;
import it.unibo.ruscodc.model.outputinfo.InfoPayload;
import it.unibo.ruscodc.model.outputinfo.InfoPayloadImpl;

public class ConsumableImpl implements Consumable {

    private final String name;
    private final String path;
    private final InfoPayload info;
    private final SingleTargetEffect effect;

    public ConsumableImpl(
        String name,
        String description,
        String path,
        SingleTargetEffect effect
        ) {
            this.name = name;
            this.path = path;
            this.effect = effect;
            this.info = new InfoPayloadImpl(name, description, path);
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getPath() {
        return this.path;
    }

    @Override
    public InfoPayload getInfo() {
        return this.info;
    }

    @Override
    public boolean isWearable() {
        return false;
    }

    @Override
    public SingleTargetEffect consume() {
        return this.effect;
    }
    
}
