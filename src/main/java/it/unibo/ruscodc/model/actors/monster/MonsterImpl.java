package it.unibo.ruscodc.model.actors.monster;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;

import com.google.gson.Gson;

import it.unibo.ruscodc.model.actors.ActorAbs;
import it.unibo.ruscodc.model.actors.Skill;
import it.unibo.ruscodc.model.gamecommand.BasicGameCommand;
import it.unibo.ruscodc.utils.GameControl;
import it.unibo.ruscodc.utils.Pair;

public class MonsterImpl extends ActorAbs implements Monster{

    private static final String PATH = "file:src/main/resources/it/unibo/ruscodc/monster_res/";

    public MonsterImpl(String name, Pair<Integer, Integer> initialPos) {
        super(name, initialPos);
    }

    @Override
    public String getInfo() {
        return null;
    }

    @Override
    public String getPath() {
        return PATH + this.name; 
    }

    @Override
    public BasicGameCommand behave() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'behave'");
    }

    @Override
    public void loadStats() {
        final Gson gson = new Gson();
        final String filePath = getPath() + "Stats.json";
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

        Map<GameControl, BasicGameCommand> tmp = gson.fromJson(scannedline.toString(), Skill.class).getSkills();

        for (Map.Entry<GameControl, BasicGameCommand> entry : tmp.entrySet()) {
            this.skills.setAction(entry.getKey(), entry.getValue());
        }
    }

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

        Map<GameControl, BasicGameCommand> tmp = gson.fromJson(scannedline.toString(), Skill.class).getSkills();

        for (Map.Entry<GameControl, BasicGameCommand> entry : tmp.entrySet()) {
            this.skills.setAction(entry.getKey(), entry.getValue());
        }
    }


}
