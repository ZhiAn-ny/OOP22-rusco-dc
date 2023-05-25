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

public class MonsterImpl extends ActorAbs implements Monster{

    private final Behaviour behaviour;
    private final String path;

    public MonsterImpl(String name, Pair<Integer, Integer> currentPos, Skill skills, Stat stats, Behaviour behaviour) {
        super(name, currentPos, skills, stats);
        this.behaviour = behaviour;
        this.path = "file:src/main/resources/it/unibo/ruscodc/monster_res/" + this.name;
    }

    @Override
    public GameCommand behave(Room room, List<Actor> actors) {
        return this.behaviour.makeDecision(this, room, actors);
    }

    @Override
    public String getPath() {
        return this.path;
    }

}
