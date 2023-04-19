package it.unibo.ruscodc.model.gamecommand;

import java.util.LinkedList;
import java.util.List;
import java.util.function.BiConsumer;

import it.unibo.ruscodc.model.Actor;
import it.unibo.ruscodc.model.effect.Effect;
import it.unibo.ruscodc.model.gamemap.Room;
import it.unibo.ruscodc.model.range.Range;
import it.unibo.ruscodc.utils.exception.ModelException;

public class GenericBuilderGameCommand {
    
    //private SkillType st;
    private final Actor actEntity;
    private final List<Actor> possibleTargets = new LinkedList<>();
    private Room where;
    //private Range targetRange;
    //private Range effectRange;
    private Effect eff;

    public GenericBuilderGameCommand(Actor actEntity) {  //SkillType
        this.actEntity = actEntity;
    } 

    // public void setTargetRange(Range targetRange){
    //     this.targetRange = targetRange;
    // }

    // public void setEffectRange(Range effectRange){
    //     this.effectRange = effectRange;
    // }

    public void setEffect(Effect eff){
        this.eff = eff;
    }

    public void setTarget(List<Actor> targets){
        this.possibleTargets.clear();
        this.possibleTargets.addAll(targets);
    }

    public void setRoom(Room where){
        this.where = where;
    }

    private void modifiesTargetList(){
        
    }

    public GameCommand build() throws ModelException{
        
        modifiesTargetList();
        
        return (actEntity, possibleTargets) -> { 
            possibleTargets.forEach(to -> {
                
                try {
                    eff.applyEffect(actEntity, to);
                } catch (ModelException e) {
                    e.printStackTrace();
                }
                
            });
        };
    }

}
