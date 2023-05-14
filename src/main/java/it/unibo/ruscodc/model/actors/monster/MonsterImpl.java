package it.unibo.ruscodc.model.actors.monster;

import it.unibo.ruscodc.model.actors.ActorAbs;
import it.unibo.ruscodc.model.actors.Skill;
import it.unibo.ruscodc.model.actors.Stat;
import it.unibo.ruscodc.model.gamecommand.GameCommand;
import it.unibo.ruscodc.utils.Pair;

public class MonsterImpl extends ActorAbs implements Monster{

    private final Behaviour behaviour = new BehaviourImpl(new MovementBehaveImpl(), new CombactBehaveImpl());

    public MonsterImpl(String name, Pair<Integer, Integer> currentPos, Skill skills, Stat stats) {
        super(name, currentPos, skills, stats);
        //TODO Auto-generated constructor stub
    }

    @Override
    public GameCommand behave() {
        
    }

}
