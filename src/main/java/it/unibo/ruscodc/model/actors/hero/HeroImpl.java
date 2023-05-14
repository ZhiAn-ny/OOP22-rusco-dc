package it.unibo.ruscodc.model.actors.hero;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;

import com.google.gson.Gson;

import it.unibo.ruscodc.model.actors.ActorAbs;
import it.unibo.ruscodc.model.actors.Skill;
import it.unibo.ruscodc.model.actors.SkillImpl;
import it.unibo.ruscodc.model.actors.Stat;
import it.unibo.ruscodc.model.actors.StatImpl;
import it.unibo.ruscodc.model.actors.StatImpl.StatName;
import it.unibo.ruscodc.model.gamecommand.GameCommand;
import it.unibo.ruscodc.utils.GameControl;
import it.unibo.ruscodc.utils.Pair;

/**
 * The implementation of the interface Hero used to create the playable characters.
 */
public class HeroImpl extends ActorAbs implements Hero {

    //private final String path

    public HeroImpl(String name, Pair<Integer, Integer> initialPos, Skill skills, Stat stats) {
        super(name, initialPos, skills, stats);
    }

    @Override
    public GameCommand act(GameControl key) {
        GameCommand command = this.skills.getAction(key);
        command.setActor();
        return command;
    }
    
    //TODO: Dopo il merge con item fai inventory
    
}
