package it.unibo.ruscodc.model.actors.hero;

import java.util.Optional;

import it.unibo.ruscodc.model.actors.ActorAbs;
import it.unibo.ruscodc.model.actors.skill.Skill;
import it.unibo.ruscodc.model.actors.stat.Stat;
import it.unibo.ruscodc.model.gamecommand.GameCommand;
import it.unibo.ruscodc.model.item.Inventory;
import it.unibo.ruscodc.model.item.InventoryImpl;
import it.unibo.ruscodc.model.item.Item;
import it.unibo.ruscodc.utils.GameControl;
import it.unibo.ruscodc.utils.Pair;

/**
 * The implementation of the interface Hero used to create the playable characters.
 */
public class HeroImpl extends ActorAbs implements Hero {

    private final Inventory inventory;
    private final String path;

    public HeroImpl(String name, Pair<Integer, Integer> initialPos, Skill skills, Stat stats) {
        super(name, initialPos, skills, stats);
        this.inventory = new InventoryImpl();
        this.path = "file:src/main/resources/it/unibo/ruscodc/hero_res/" + this.name;
    }

    @Override
    public Optional<GameCommand> act(GameControl key) {
        Optional<GameCommand> command = this.skills.getAction(key);
        if (command.isEmpty()) {
            return Optional.empty();
        }

        command.get().setActor(this);
        return command;
    }

    @Override
    public Inventory getInventory() {
        return this.inventory;
    }

    @Override
    public String getPath() {
        return this.path;
    }
    
}
