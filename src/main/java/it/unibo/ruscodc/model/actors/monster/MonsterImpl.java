package it.unibo.ruscodc.model.actors.monster;

import java.util.List;

import it.unibo.ruscodc.model.actors.Actor;
import it.unibo.ruscodc.model.actors.ActorAbs;
import it.unibo.ruscodc.model.actors.monster.behaviour.Behaviour;
import it.unibo.ruscodc.model.actors.skill.Skill;
import it.unibo.ruscodc.model.actors.stat.Stat;
import it.unibo.ruscodc.model.gamecommand.GameCommand;
import it.unibo.ruscodc.model.gamemap.Room;
import it.unibo.ruscodc.utils.Pair;

/**
 * Implementation of the class Monster.
 */
public class MonsterImpl extends ActorAbs implements Monster {

    private final Behaviour behaviour;
    private final String path;

    /**
     * Basic Constructor for the Monsetrs.
     * @param name of the Monster
     * @param currentPos of the Monster
     * @param skills of the Monster
     * @param stats of the Monster
     * @param behaviour of the Monster
     */
    public MonsterImpl(
        final String name,
        final Pair<Integer, Integer> currentPos,
        final Skill skills,
        final Stat stats,
        final Behaviour behaviour
    ) {
        super(name, currentPos, skills, stats);
        this.behaviour = behaviour;
        this.path = "it/unibo/ruscodc/monster_res/" + super.getName();
    }

    /**
     * 
     */
    @Override
    public GameCommand behave(final Room room, final List<Actor> actors) {
        return this.behaviour.makeDecision(this, room, actors);
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
        return super.getStats().toString();
    }
}
