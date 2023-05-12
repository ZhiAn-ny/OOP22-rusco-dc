package it.unibo.ruscodc.model.actors.hero;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;

import com.google.gson.Gson;

import it.unibo.ruscodc.model.actors.ActorAbs;
import it.unibo.ruscodc.model.actors.Skill;
import it.unibo.ruscodc.model.actors.Stat;
import it.unibo.ruscodc.model.actors.StatImpl;
import it.unibo.ruscodc.model.actors.StatImpl.StatName;
import it.unibo.ruscodc.model.gamecommand.BuilderGameCommand;
import it.unibo.ruscodc.utils.GameControl;
import it.unibo.ruscodc.utils.Pair;

/**
 * The implementation of the interface Hero used to create the playable characters.
 */
public class HeroImpl extends ActorAbs implements Hero {

    private static final String PATH = "file:src/main/resources/it/unibo/ruscodc/hero_res/";

    /**
     * Constructor for the Hero.
     * @param name a string consisting of the name of the Hero.
     * @param initialPos a Pair of Integer that indicates the Hero initial position.
     */
    public HeroImpl(final String name, final Pair<Integer, Integer> initialPos) {
        super(name, initialPos);
    }

    /**
     * A method to get general information about the hero.
     * @return a string with general info about the actor
     */
    @Override
    public String getInfo() {
        return null;
    }

    /**
     * A method to get the path to this Hero folder.
     * @return the path to this Hero folder
     */
    @Override
    public String getPath() {
        return "file:src/main/resources/it/unibo/ruscodc/hero_res/rusco/racoon-head.png";
    }

    /**
     * a method used to load Stats from the Hero folder.
     */
    @Override
    public void loadStats() {
        final Gson gson = new Gson();
        final String filePath = getPath() + "/Stats.json";
        File file = new File(filePath);
        StringBuilder scannedline = new StringBuilder(); 

        try {
            Scanner scan = new Scanner(file);
            while (scan.hasNextLine()) {
                scannedline.append(scan.nextLine());
                System.out.println(scannedline);
            }
            scan.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Map<StatName, Pair<Integer, Integer>> tmp = gson.fromJson(scannedline.toString(), Stat.class).getStats();

        for (Map.Entry<StatName, Pair<Integer, Integer>> entry : tmp.entrySet()) {
            this.stats.setStat(entry.getKey(), entry.getValue());
        }
    }

    /**
     * a method used to load Stats from the Hero folder.
     */
    @Override
    public void loadSkills() {
        final Gson gson = new Gson();
        final String filePath = getPath() + "/Skills.json";
        File file = new File(filePath);
        StringBuilder scannedline = new StringBuilder();
        try {
            Scanner scan = new Scanner(file);
            while (scan.hasNextLine()) {
                scannedline.append(scan.nextLine());
                System.out.println(scannedline);
            }
            scan.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Map<GameControl, BuilderGameCommand> tmp = gson.fromJson(scannedline.toString(), Skill.class).getSkills();

        for (Map.Entry<GameControl, BuilderGameCommand> entry : tmp.entrySet()) {
            this.skills.setAction(entry.getKey(), entry.getValue());
        }
    }

    /**
     * a method that given the key processed in the input it returns return the respective Command.
     * @param key the input value of the key pressed
     * @return a Builder of GameCommand of the respective key
     */
    public BuilderGameCommand act(final GameControl key) {
        BuilderGameCommand builderGameCommand = this.getFieldSkill().getAction(key);
        builderGameCommand.setActor(this);
        return builderGameCommand;
    }

}
