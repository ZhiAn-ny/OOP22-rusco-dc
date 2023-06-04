package it.unibo.ruscodc.model.actors.hero;

import java.util.Collections;
import java.util.Optional;

import it.unibo.ruscodc.model.actors.ActorAbs;
import it.unibo.ruscodc.model.actors.skill.Skill;
import it.unibo.ruscodc.model.actors.stat.Stat;
import it.unibo.ruscodc.model.gamecommand.GameCommand;
import it.unibo.ruscodc.model.item.Inventory;
import it.unibo.ruscodc.model.item.InventoryImpl;
import it.unibo.ruscodc.utils.GameControl;
import it.unibo.ruscodc.utils.Pair;

/**
 * The implementation of the interface Hero used to create the playable characters.
 */
public class HeroImpl extends ActorAbs implements Hero {

    private final Inventory inventory;
    private final String path;

    /**
     * Basic Constructor for the Hero.
     * @param name of the Hero
     * @param initialPos of the Hero
     * @param skills of the Hero
     * @param stats of the Hero
     */
    public HeroImpl(final String name, final Pair<Integer, Integer> initialPos, final Skill skills, final Stat stats) {
        super(name, initialPos, skills, stats);
        this.inventory = new InventoryImpl();
        this.path = "file:src/main/resources/it/unibo/ruscodc/hero_res/" + super.getName();
    }

    /**
     * 
     */
    @Override
    public Optional<GameCommand> act(final GameControl key) {
        final Optional<GameCommand> command = super.getSkills().getAction(key);
        if (command.isEmpty()) {
            return Optional.empty();
        }

        command.get().setActor(this);
        return command;
    }

    /**
     * 
     */
    @Override
    public Inventory getInventory() {
        return Collections.nCopies(1, this.inventory).get(0);
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
    public String toString() {
        return super.getStats().toString() + "\n" + super.getSkills().toString();
    }
}
