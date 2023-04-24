package it.unibo.ruscodc.model.actors;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;

import com.google.gson.Gson;

import it.unibo.ruscodc.model.actors.StatImpl.StatName;
import it.unibo.ruscodc.model.gamecommand.BuilderGameCommand;
import it.unibo.ruscodc.utils.Pair;

public class HeroImpl extends ActorAbs implements Hero {

    static String PATH = "file:src/main/resources/it/unibo/ruscodc/hero_res/";
    
    public HeroImpl(String name, Pair<Integer, Integer> initialPos) {
        super(name,initialPos);
    }
    
    @Override
    public String getInfo() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getInfo'");
    }
    
    @Override
    public String getPath() {
        return PATH + this.name;
    }

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
        
        Map<StatName,Pair<Integer, Integer>> tmp = gson.fromJson(scannedline.toString(), Stat.class).getStats();
        
        for (Map.Entry<StatName,Pair<Integer, Integer>> entry : tmp.entrySet()) {
            this.stats.setStat(entry.getKey(), entry.getValue());
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
        
        Map<Integer,BuilderGameCommand> tmp = gson.fromJson(scannedline.toString(), Skill.class).getSkills();
        
        for (Map.Entry<Integer,BuilderGameCommand> entry : tmp.entrySet()) {
            this.skills.setAction(entry.getKey(), entry.getValue());
        }
    }

    public BuilderGameCommand act(int key) {
        BuilderGameCommand builderGameCommand = this.getSkills().getAction(key);
        builderGameCommand.setActor(this);
        return builderGameCommand;
    }

}
