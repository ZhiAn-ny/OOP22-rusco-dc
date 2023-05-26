package it.unibo.ruscodc.model.item.equipement;

import java.util.Map;
import java.util.Optional;

import it.unibo.ruscodc.model.actors.Actor;
import it.unibo.ruscodc.model.actors.stat.StatImpl.StatName;
import it.unibo.ruscodc.model.gamecommand.GameCommand;
import it.unibo.ruscodc.model.item.InventoryImpl.Slot;
import it.unibo.ruscodc.model.outputinfo.InfoPayload;
import it.unibo.ruscodc.model.outputinfo.InfoPayloadImpl;
import it.unibo.ruscodc.utils.GameControl;
import it.unibo.ruscodc.utils.Pair;

public class EquipementImpl implements Equipement {

    private final String name;
    private final String path;
    private final Slot slot;
    private final Map<StatName, Integer> stat;
    private final Optional<Pair<GameControl, GameCommand>> action;
    private final InfoPayload info;

    
    public EquipementImpl(
        String name,
        String description,
        String path,
        Slot slot,
        Map<StatName, Integer> stat,
        Pair<GameControl, GameCommand> action
        ) {
            this.name = name;
            this.path = path;
            this.slot = slot;
            this.stat = stat;
            this.action = Optional.ofNullable(action);
            this.info = new InfoPayloadImpl(name, description, path);
    }
    
    public EquipementImpl(
            String name,
            String description,
            String path,
            Slot slot,
            Map<StatName, Integer> stat
    ) {
        this(
            name,
            description,
            path,
            slot,
            stat,
            null
        );
    }

    public boolean isWearable() {
        return true;
    }

    public String getName() {
        return this.name;
    }
    
    public String getPath() {
        return this.path;
    }
    
    public InfoPayload getInfo() {
        return this.info;
    }
    
    public void equip(Actor actor) {
        this.stat
            .entrySet()
            .stream()
            .forEach(
                a -> actor.modifyMaxStat(a.getKey(), actor.getStatMax(a.getKey()) + a.getValue()));

        if (action.isPresent()) {
            actor.getSkills().setAction(action.get().getX(), action.get().getY());
        }
    }

    public void unequip(Actor actor) {
        this.stat
            .entrySet()
            .stream()
            .forEach(
                a -> actor.modifyMaxStat(a.getKey(), actor.getStatMax(a.getKey()) - a.getValue()));
    }

    public Slot getSlot() {
        return this.slot;
    }

}
